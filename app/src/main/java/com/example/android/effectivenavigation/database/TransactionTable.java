package com.example.android.effectivenavigation.database;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Nullable;

/**
 * Created by lokeshmutyala on 11-07-2017.
 */
@Entity
public interface TransactionTable {
    @Key
    @Generated
    public int getId();

    public String getTransactionId();

    public String getStoreId();

    @Nullable
    public String getCustomerMobile();

    public String getTransactionValue();

    public String getTransactionTime();

    @Nullable
    public int getLoyalityPlus();


    public boolean getSyncStatus();

    @Nullable
    public int getLoyalityMinus();

    public String getTaxCollected();
//
//    @Nullable
//    public String getIgst();
//
//    @Nullable
//    public String getCgst();
//
//    @Nullable
//    public String getSgst();

    public String getProductId();

    public String getDiscount();

    public String getGross();

}
