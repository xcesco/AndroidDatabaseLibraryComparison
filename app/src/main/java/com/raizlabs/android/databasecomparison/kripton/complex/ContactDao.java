package com.raizlabs.android.databasecomparison.kripton.complex;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

/**
 * Created by xcesco on 23/09/2017.
 */
@BindDao(Contact.class)
public interface ContactDao extends BaseDao<Contact> {
    @BindSqlSelect(where = "addressBook=${id}")
    List<Contact> selectByAddressBook(long id);
}
