package com.raizlabs.android.databasecomparison.kripton.complex;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

import java.util.List;

/**
 * Description:
 */
@BindType
public class Contact {

    long id;

    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;

    @BindColumn(foreignKey = AddressBook.class)
    public long addressBook;

}
