package com.raizlabs.android.databasecomparison.kripton.complex;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

import java.util.Collection;
import java.util.List;

/**
 * Description:
 */
//@Table(tableName = "AddressBook", databaseName = DBFlowDatabase.NAME)
//@ModelContainer
@BindTable
public class AddressBook  {

//    @PrimaryKey(autoincrement = true)
//    @Column
    public long id;

//    @Column(name = "name")
    public String name;

//    @Column(name = "author")
    public String author;

    @BindColumn()
    public List<AddressItem> addresses;

    @BindColumn
    public List<Contact> contacts;

//    @Override
//    public void setId(long id) {
//        // not needed because we have autoincrementing keys
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public void setAddresses(Collection<AddressItem> addresses) {
//        this.addresses = addresses;
//    }
//
//    public Collection<AddressItem> getAddresses() {
//        if (addresses == null) {
//            addresses = new Select().from(AddressItem.class).where(Condition.column(AddressItem$Table.ADDRESSBOOK_ADDRESSBOOK).is(id)).queryList();
//        }
//        return addresses;
//    }
//
//    public Collection<Contact> getContacts() {
//        if (contacts == null) {
//            contacts = new Select().from(Contact.class).where(Condition.column(Contact$Table.ADDRESSBOOK_ADDRESSBOOK).is(id)).queryList();
//        }
//        return contacts;
//    }
//
//    public void setContacts(Collection<Contact> contacts) {
//        this.contacts = contacts;
//    }
//
//    @Override
//    public void saveAll() {
//        super.insert();
//        for (AddressItem addressItem : addresses) {
//            addressItem.saveAll();
//        }
//        for (Contact contact : contacts) {
//            contact.saveAll();
//        }
//    }
//
//    @Override
//    public int getCacheSize() {
//        return MainActivity.ADDRESS_BOOK_COUNT;
//    }

}