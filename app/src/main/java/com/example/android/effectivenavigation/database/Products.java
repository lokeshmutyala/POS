package com.example.android.effectivenavigation.database;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Nullable;

/**
 * Created by lokeshmutyala on 07-07-2017.
 */
@Entity
public interface Products {

    public String getBarcode_No();

    public String getCompany_Name();

    public String getProduct_Name();

    public int getTax();

    @Nullable
    public String getHSNno();

    public Double getMRP();

    public String getUOM();

    public String getProduct_ID();

}
