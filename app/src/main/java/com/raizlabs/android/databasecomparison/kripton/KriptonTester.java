package com.raizlabs.android.databasecomparison.kripton;

import android.content.Context;

import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.events.LogTestDataEvent;
import com.raizlabs.android.databasecomparison.kripton.complex.AddressBook;
import com.raizlabs.android.databasecomparison.kripton.complex.AddressItem;
import com.raizlabs.android.databasecomparison.kripton.complex.BindComplexDaoFactory;
import com.raizlabs.android.databasecomparison.kripton.complex.BindComplexDataSource;
import com.raizlabs.android.databasecomparison.kripton.complex.Contact;
import com.raizlabs.android.databasecomparison.kripton.simple.BindSimpleDaoFactory;
import com.raizlabs.android.databasecomparison.kripton.simple.BindSimpleDataSource;
import com.raizlabs.android.databasecomparison.kripton.simple.SimpleAddressItem;
import com.raizlabs.android.databasecomparison.kripton.simple.TrickedSimpleAddressDaoImpl;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Runs benchmarks for OrmLite
 */
public class KriptonTester {
    public static final String FRAMEWORK_NAME = "Kripton";
    private static final String TAG = KriptonTester.class.getName();

    public static void testAddressBooks(Context context) {
        final BindComplexDataSource dataSource = BindComplexDataSource.instance();

        dataSource.execute(new BindComplexDataSource.SimpleTransaction() {
            @Override
            public boolean onExecute(BindComplexDaoFactory daoFactory) throws Throwable {
                daoFactory.getAddressBookDao().deleteAll();
                daoFactory.getAddressItemDao().deleteAll();
                daoFactory.getContactDao().deleteAll();

                List<AddressBook> books = Generator.createKriptonAddressBooks(MainActivity.ADDRESS_BOOK_COUNT);

                long startTime = System.currentTimeMillis();

                for (AddressBook addressBook : books) {
                    daoFactory.getAddressBookDao().insert(addressBook);

                    for (AddressItem address : addressBook.addresses) {
                        address.setAddressBook(addressBook.id);
                        daoFactory.getAddressItemDao().insert(address);
                    }

                    for (Contact contact : addressBook.contacts) {
                        contact.addressBook = addressBook.id;
                        daoFactory.getContactDao().insert(contact);
                    }

                }

                EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME));

                startTime = System.currentTimeMillis();

                books = daoFactory.getAddressBookDao().selectAll();

                for (AddressBook addressBook : books) {
                    daoFactory.getAddressItemDao().selectByAddressBook(addressBook.id);
                    daoFactory.getContactDao().selectByAddressBook(addressBook.id);

                }

                EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME));

                // clean out DB for next run
                daoFactory.getAddressItemDao().deleteAll();
                daoFactory.getContactDao().deleteAll();
                daoFactory.getAddressBookDao().deleteAll();

                return true;
            }
        });

//        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
//
//        try {
//            dbHelper.getAddressBookDao().deleteBuilder().delete();
//            dbHelper.getAddressItemDao().deleteBuilder().delete();
//            dbHelper.getContactDao().deleteBuilder().delete();
//        } catch (SQLException e) {
//            Log.e(TAG, "Error clearing DB", e);
//        }
//
//        Collection<AddressBook> addressBooks = Generator.createAddressBooks(
//                AddressBook.class,
//                Contact.class, AddressItem.class,
//                MainActivity.ADDRESS_BOOK_COUNT);
//        long startTime = System.currentTimeMillis();
//
//        try {
//            Dao<AddressBook, Integer> addressBookDao = dbHelper.getAddressBookDao();
//            Dao<AddressItem, Integer> addressItemDao = dbHelper.getAddressItemDao();
//            Dao<Contact, Integer> contactDao = dbHelper.getContactDao();
//
//            final SQLiteDatabase db = dbHelper.getWritableDatabase();
//            db.beginTransaction();
//            try {
//                // see http://stackoverflow.com/questions/17456321/how-to-insert-bulk-data-in-android-sqlite-database-using-ormlite-efficiently
//                for (AddressBook addressBook : addressBooks) {
//                    // OrmLite isn't that smart, so we have to insert the root object and then we can add the children
//                    addressBookDao.create(addressBook);
//                    addressBook.insertNewAddresses(addressBookDao, addressItemDao);
//                    addressBook.insertNewContacts(addressBookDao, contactDao);
//                }
//
//                db.setTransactionSuccessful();
//            } finally {
//                db.endTransaction();
//            }
//            EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME));
//
//            startTime = System.currentTimeMillis();
//            addressBooks = addressBookDao.queryForAll();
//            Loader.loadAllInnerData(addressBooks);
//            EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME));
//
//            // clean out DB for next run
//            contactDao.deleteBuilder().delete();
//            addressItemDao.deleteBuilder().delete();
//            addressBookDao.deleteBuilder().delete();
//        } catch (SQLException e) {
//            Log.e(TAG, "Error clearing DB", e);
//        }
//
//        OpenHelperManager.releaseHelper();
    }

    public static void testAddressItems(Context context) {
        final BindSimpleDataSource dataSource = BindSimpleDataSource.instance();

        dataSource.execute(new BindSimpleDataSource.SimpleTransaction() {
            @Override
            public boolean onExecute(BindSimpleDaoFactory daoFactory) throws Throwable {
                TrickedSimpleAddressDaoImpl dao = new TrickedSimpleAddressDaoImpl(dataSource);

                // delete all
                dao.deleteAll();
                List<? extends SimpleAddressItem> simpleAddressItems = Generator.getKriptonAddresses(MainActivity.LOOP_COUNT);

                long startTime = System.currentTimeMillis();

                for (SimpleAddressItem simpleAddressItem : simpleAddressItems) {
                    dao.insert(simpleAddressItem);
                }

                EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME));

                startTime = System.currentTimeMillis();
                simpleAddressItems = dao.selectAll();
                EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME));

                // clean out DB for next run
                dao.deleteAll();

                return true;
            }
        });
    }
}
