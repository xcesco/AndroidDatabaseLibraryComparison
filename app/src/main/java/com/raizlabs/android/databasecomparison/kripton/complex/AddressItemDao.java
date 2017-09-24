package com.raizlabs.android.databasecomparison.kripton.complex;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

/**
 * Created by xcesco on 23/09/2017.
 */
@BindDao(AddressItem.class)
public interface AddressItemDao extends BaseDao<AddressItem> {

    @BindSqlSelect(where ="addressBook=${id}")
    List<AddressItem> selectByAddressBook(long id);
}
