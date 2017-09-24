package com.raizlabs.android.databasecomparison.kripton.complex;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 23/09/2017.
 */
@BindDataSource(daoSet = {AddressBookDao.class, ContactDao.class, AddressItemDao.class}, fileName = "kripton-complex.db", generateLog = false)
public interface ComplexDataSource {
}
