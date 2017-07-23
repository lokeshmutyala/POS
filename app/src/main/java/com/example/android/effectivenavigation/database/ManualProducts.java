package com.example.android.effectivenavigation.database;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;

/**
 * Created by lokeshmutyala on 16-07-2017.
 */
@Entity
public interface ManualProducts {
    @Key
    @Generated
    public int getId();

    public String getBarcode_No();

    public Double getPrice();

    public String getProductDescription();

    public boolean getSyncStatus();

    public int getTax();

}
