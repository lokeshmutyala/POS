// Generated file do not edit, generated by io.requery.processor.EntityProcessor
package com.example.android.effectivenavigation.database;

import io.requery.meta.EntityModel;
import io.requery.meta.EntityModelBuilder;

public class Models {
    public static final EntityModel DEFAULT = new EntityModelBuilder("default")
    .addType(TransactionTableEntity.$TYPE)
    .addType(CustomerTableEntity.$TYPE)
    .addType(ProductsEntity.$TYPE)
    .addType(InvoiceEntity.$TYPE)
    .addType(ManualProductsEntity.$TYPE)
    .build();

    private Models() {
    }
}