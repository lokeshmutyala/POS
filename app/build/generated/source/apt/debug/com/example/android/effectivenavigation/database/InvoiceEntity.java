// Generated file do not edit, generated by io.requery.processor.EntityProcessor
package com.example.android.effectivenavigation.database;

import io.requery.Persistable;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.BooleanProperty;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.IntProperty;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public class InvoiceEntity implements Invoice, Persistable {
    public static final QueryAttribute<InvoiceEntity, Integer> ID = 
    new AttributeBuilder<InvoiceEntity, Integer>("id", int.class)
    .setProperty(new IntProperty<InvoiceEntity>() {
        @Override
        public Integer get(InvoiceEntity entity) {
            return entity.id;
        }

        @Override
        public void set(InvoiceEntity entity, Integer value) {
            entity.id = value;
        }

        @Override
        public int getInt(InvoiceEntity entity) {
            return entity.id;
        }

        @Override
        public void setInt(InvoiceEntity entity, int value) {
            entity.id = value;
        }
    })
    .setPropertyName("getId")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$id_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$id_state = value;
        }
    })
    .setKey(true)
    .setGenerated(true)
    .setLazy(false)
    .setNullable(false)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> DISCOUNT = 
    new AttributeBuilder<InvoiceEntity, String>("discount", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.discount;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.discount = value;
        }
    })
    .setPropertyName("getDiscount")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$discount_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$discount_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> PRODUCT_TAX = 
    new AttributeBuilder<InvoiceEntity, String>("productTax", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.productTax;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.productTax = value;
        }
    })
    .setPropertyName("getProductTax")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$productTax_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$productTax_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> PRICE = 
    new AttributeBuilder<InvoiceEntity, String>("price", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.price;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.price = value;
        }
    })
    .setPropertyName("getPrice")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$price_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$price_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> TRANSACTION_ID = 
    new AttributeBuilder<InvoiceEntity, String>("transactionId", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.transactionId;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.transactionId = value;
        }
    })
    .setPropertyName("getTransactionId")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$transactionId_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$transactionId_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> BARCODE = 
    new AttributeBuilder<InvoiceEntity, String>("barcode", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.barcode;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.barcode = value;
        }
    })
    .setPropertyName("getBarcode")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$barcode_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$barcode_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> NAME = 
    new AttributeBuilder<InvoiceEntity, String>("name", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.name;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.name = value;
        }
    })
    .setPropertyName("getName")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$name_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$name_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, Integer> QUANTITY = 
    new AttributeBuilder<InvoiceEntity, Integer>("quantity", int.class)
    .setProperty(new IntProperty<InvoiceEntity>() {
        @Override
        public Integer get(InvoiceEntity entity) {
            return entity.quantity;
        }

        @Override
        public void set(InvoiceEntity entity, Integer value) {
            entity.quantity = value;
        }

        @Override
        public int getInt(InvoiceEntity entity) {
            return entity.quantity;
        }

        @Override
        public void setInt(InvoiceEntity entity, int value) {
            entity.quantity = value;
        }
    })
    .setPropertyName("getQuantity")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$quantity_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$quantity_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(false)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, Boolean> SYNC_STATUS = 
    new AttributeBuilder<InvoiceEntity, Boolean>("syncStatus", boolean.class)
    .setProperty(new BooleanProperty<InvoiceEntity>() {
        @Override
        public Boolean get(InvoiceEntity entity) {
            return entity.syncStatus;
        }

        @Override
        public void set(InvoiceEntity entity, Boolean value) {
            entity.syncStatus = value;
        }

        @Override
        public boolean getBoolean(InvoiceEntity entity) {
            return entity.syncStatus;
        }

        @Override
        public void setBoolean(InvoiceEntity entity, boolean value) {
            entity.syncStatus = value;
        }
    })
    .setPropertyName("getSyncStatus")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$syncStatus_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$syncStatus_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(false)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> PRODUCT_ID = 
    new AttributeBuilder<InvoiceEntity, String>("productId", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.productId;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.productId = value;
        }
    })
    .setPropertyName("getProductId")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$productId_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$productId_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<InvoiceEntity, String> NET_VALUE = 
    new AttributeBuilder<InvoiceEntity, String>("netValue", String.class)
    .setProperty(new Property<InvoiceEntity, String>() {
        @Override
        public String get(InvoiceEntity entity) {
            return entity.netValue;
        }

        @Override
        public void set(InvoiceEntity entity, String value) {
            entity.netValue = value;
        }
    })
    .setPropertyName("getNetValue")
    .setPropertyState(new Property<InvoiceEntity, PropertyState>() {
        @Override
        public PropertyState get(InvoiceEntity entity) {
            return entity.$netValue_state;
        }

        @Override
        public void set(InvoiceEntity entity, PropertyState value) {
            entity.$netValue_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final Type<InvoiceEntity> $TYPE = new TypeBuilder<InvoiceEntity>(InvoiceEntity.class, "Invoice")
    .setBaseType(Invoice.class)
    .setCacheable(true)
    .setImmutable(false)
    .setReadOnly(false)
    .setStateless(false)
    .setView(false)
    .setFactory(new Supplier<InvoiceEntity>() {
        @Override
        public InvoiceEntity get() {
            return new InvoiceEntity();
        }
    })
    .setProxyProvider(new Function<InvoiceEntity, EntityProxy<InvoiceEntity>>() {
        @Override
        public EntityProxy<InvoiceEntity> apply(InvoiceEntity entity) {
            return entity.$proxy;
        }
    })
    .addAttribute(SYNC_STATUS)
    .addAttribute(NET_VALUE)
    .addAttribute(PRICE)
    .addAttribute(PRODUCT_TAX)
    .addAttribute(QUANTITY)
    .addAttribute(BARCODE)
    .addAttribute(ID)
    .addAttribute(PRODUCT_ID)
    .addAttribute(TRANSACTION_ID)
    .addAttribute(DISCOUNT)
    .addAttribute(NAME)
    .build();

    private PropertyState $id_state;

    private PropertyState $discount_state;

    private PropertyState $productTax_state;

    private PropertyState $price_state;

    private PropertyState $transactionId_state;

    private PropertyState $barcode_state;

    private PropertyState $name_state;

    private PropertyState $quantity_state;

    private PropertyState $syncStatus_state;

    private PropertyState $productId_state;

    private PropertyState $netValue_state;

    private int id;

    private String discount;

    private String productTax;

    private String price;

    private String transactionId;

    private String barcode;

    private String name;

    private int quantity;

    private boolean syncStatus;

    private String productId;

    private String netValue;

    private final transient EntityProxy<InvoiceEntity> $proxy = new EntityProxy<InvoiceEntity>(this, $TYPE);

    public InvoiceEntity() {
    }

    @Override
    public int getId() {
        return $proxy.get(ID);
    }

    @Override
    public String getDiscount() {
        return $proxy.get(DISCOUNT);
    }

    public void setDiscount(String discount) {
        $proxy.set(DISCOUNT, discount);
    }

    @Override
    public String getProductTax() {
        return $proxy.get(PRODUCT_TAX);
    }

    public void setProductTax(String productTax) {
        $proxy.set(PRODUCT_TAX, productTax);
    }

    @Override
    public String getPrice() {
        return $proxy.get(PRICE);
    }

    public void setPrice(String price) {
        $proxy.set(PRICE, price);
    }

    @Override
    public String getTransactionId() {
        return $proxy.get(TRANSACTION_ID);
    }

    public void setTransactionId(String transactionId) {
        $proxy.set(TRANSACTION_ID, transactionId);
    }

    @Override
    public String getBarcode() {
        return $proxy.get(BARCODE);
    }

    public void setBarcode(String barcode) {
        $proxy.set(BARCODE, barcode);
    }

    @Override
    public String getName() {
        return $proxy.get(NAME);
    }

    public void setName(String name) {
        $proxy.set(NAME, name);
    }

    @Override
    public int getQuantity() {
        return $proxy.get(QUANTITY);
    }

    public void setQuantity(int quantity) {
        $proxy.set(QUANTITY, quantity);
    }

    @Override
    public boolean getSyncStatus() {
        return $proxy.get(SYNC_STATUS);
    }

    public void setSyncStatus(boolean syncStatus) {
        $proxy.set(SYNC_STATUS, syncStatus);
    }

    @Override
    public String getProductId() {
        return $proxy.get(PRODUCT_ID);
    }

    public void setProductId(String productId) {
        $proxy.set(PRODUCT_ID, productId);
    }

    @Override
    public String getNetValue() {
        return $proxy.get(NET_VALUE);
    }

    public void setNetValue(String netValue) {
        $proxy.set(NET_VALUE, netValue);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvoiceEntity && ((InvoiceEntity)obj).$proxy.equals(this.$proxy);
    }

    @Override
    public int hashCode() {
        return $proxy.hashCode();
    }

    @Override
    public String toString() {
        return $proxy.toString();
    }
}
