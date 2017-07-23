package com.example.android.effectivenavigation.database;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Nullable;

/**
 * Created by lokeshmutyala on 29-06-2017.
 */
@Entity
public interface Invoice {
    @Key
    @Generated
    public int getId();

    public String getPrice();

    @Nullable
    public String getDiscount();

    public String getTransactionId();

    public String getBarcode();

    public String getName();

    public int getQuantity();

    public boolean getSyncStatus();

    public String getProductId();

//    @Nullable
//    public String getIgst();
//
//    @Nullable
//    public String getCgst();
//
//    @Nullable
//    public String getSgst();

    @Nullable
    public String getProductTax();

    public String getNetValue();

}
