package com.raizlabs.android.databasecomparison.kripton.complex;


import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.raizlabs.android.databasecomparison.kripton.simple.SimpleAddressItem;

/**
 * Description:
 */
@BindType
public class AddressItem extends SimpleAddressItem {

    public long id;

    public long getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(long addressBook) {
        this.addressBook = addressBook;
    }

    @BindColumn(foreignKey = AddressBook.class)
    long addressBook;
}
