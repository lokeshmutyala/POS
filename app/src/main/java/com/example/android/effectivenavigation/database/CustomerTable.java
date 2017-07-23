package com.example.android.effectivenavigation.database;

import io.requery.Entity;
import io.requery.Key;

/**
 * Created by lokeshmutyala on 14-07-2017.
 */
@Entity
public interface CustomerTable {
    @Key
    public String getCustomerMobile();

    public String getCustomerName();

    public String getCustomerGender();

    public String getCustomerAge();

    public String getCreatedTime();
}
