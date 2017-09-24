package com.raizlabs.android.databasecomparison.kripton.complex;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.ArrayList;

/**
 * Created by xcesco on 24/09/2017.
 */

public interface BaseDao<E> {
    @BindSqlSelect(where="id=${id}")
    E selectById(long id);

    @BindSqlDelete
    void deleteAll();

    @BindSqlSelect
    ArrayList<E> selectAll();

    @BindSqlInsert
    void insert(E bean);
}
