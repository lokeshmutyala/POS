// Generated file do not edit, generated by io.requery.processor.EntityProcessor
package com.example.android.effectivenavigation.database;

import io.requery.Persistable;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.IntProperty;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public class ProductsEntity implements Products, Persistable {
    public static final QueryAttribute<ProductsEntity, String> H_SNNO = 
    new AttributeBuilder<ProductsEntity, String>("hSNno", String.class)
    .setProperty(new Property<ProductsEntity, String>() {
        @Override
        public String get(ProductsEntity entity) {
            return entity.hSNno;
        }

        @Override
        public void set(ProductsEntity entity, String value) {
            entity.hSNno = value;
        }
    })
    .setPropertyName("getHSNno")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$hSNno_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$hSNno_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, String> BARCODE_NO = 
    new AttributeBuilder<ProductsEntity, String>("barcode_No", String.class)
    .setProperty(new Property<ProductsEntity, String>() {
        @Override
        public String get(ProductsEntity entity) {
            return entity.barcode_No;
        }

        @Override
        public void set(ProductsEntity entity, String value) {
            entity.barcode_No = value;
        }
    })
    .setPropertyName("getBarcode_No")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$barcode_No_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$barcode_No_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, String> COMPANY_NAME = 
    new AttributeBuilder<ProductsEntity, String>("company_Name", String.class)
    .setProperty(new Property<ProductsEntity, String>() {
        @Override
        public String get(ProductsEntity entity) {
            return entity.company_Name;
        }

        @Override
        public void set(ProductsEntity entity, String value) {
            entity.company_Name = value;
        }
    })
    .setPropertyName("getCompany_Name")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$company_Name_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$company_Name_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, String> PRODUCT_NAME = 
    new AttributeBuilder<ProductsEntity, String>("product_Name", String.class)
    .setProperty(new Property<ProductsEntity, String>() {
        @Override
        public String get(ProductsEntity entity) {
            return entity.product_Name;
        }

        @Override
        public void set(ProductsEntity entity, String value) {
            entity.product_Name = value;
        }
    })
    .setPropertyName("getProduct_Name")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$product_Name_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$product_Name_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, Integer> TAX = 
    new AttributeBuilder<ProductsEntity, Integer>("tax", int.class)
    .setProperty(new IntProperty<ProductsEntity>() {
        @Override
        public Integer get(ProductsEntity entity) {
            return entity.tax;
        }

        @Override
        public void set(ProductsEntity entity, Integer value) {
            entity.tax = value;
        }

        @Override
        public int getInt(ProductsEntity entity) {
            return entity.tax;
        }

        @Override
        public void setInt(ProductsEntity entity, int value) {
            entity.tax = value;
        }
    })
    .setPropertyName("getTax")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$tax_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$tax_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(false)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, Double> MRP = 
    new AttributeBuilder<ProductsEntity, Double>("MRP", Double.class)
    .setProperty(new Property<ProductsEntity, Double>() {
        @Override
        public Double get(ProductsEntity entity) {
            return entity.mrp;
        }

        @Override
        public void set(ProductsEntity entity, Double value) {
            entity.mrp = value;
        }
    })
    .setPropertyName("getMRP")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$mrp_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$mrp_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, String> UOM = 
    new AttributeBuilder<ProductsEntity, String>("UOM", String.class)
    .setProperty(new Property<ProductsEntity, String>() {
        @Override
        public String get(ProductsEntity entity) {
            return entity.uom;
        }

        @Override
        public void set(ProductsEntity entity, String value) {
            entity.uom = value;
        }
    })
    .setPropertyName("getUOM")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$uom_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$uom_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<ProductsEntity, String> PRODUCT_ID = 
    new AttributeBuilder<ProductsEntity, String>("product_ID", String.class)
    .setProperty(new Property<ProductsEntity, String>() {
        @Override
        public String get(ProductsEntity entity) {
            return entity.product_ID;
        }

        @Override
        public void set(ProductsEntity entity, String value) {
            entity.product_ID = value;
        }
    })
    .setPropertyName("getProduct_ID")
    .setPropertyState(new Property<ProductsEntity, PropertyState>() {
        @Override
        public PropertyState get(ProductsEntity entity) {
            return entity.$product_ID_state;
        }

        @Override
        public void set(ProductsEntity entity, PropertyState value) {
            entity.$product_ID_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final Type<ProductsEntity> $TYPE = new TypeBuilder<ProductsEntity>(ProductsEntity.class, "Products")
    .setBaseType(Products.class)
    .setCacheable(true)
    .setImmutable(false)
    .setReadOnly(false)
    .setStateless(false)
    .setView(false)
    .setFactory(new Supplier<ProductsEntity>() {
        @Override
        public ProductsEntity get() {
            return new ProductsEntity();
        }
    })
    .setProxyProvider(new Function<ProductsEntity, EntityProxy<ProductsEntity>>() {
        @Override
        public EntityProxy<ProductsEntity> apply(ProductsEntity entity) {
            return entity.$proxy;
        }
    })
    .addAttribute(COMPANY_NAME)
    .addAttribute(UOM)
    .addAttribute(TAX)
    .addAttribute(MRP)
    .addAttribute(PRODUCT_NAME)
    .addAttribute(PRODUCT_ID)
    .addAttribute(H_SNNO)
    .addAttribute(BARCODE_NO)
    .build();

    private PropertyState $hSNno_state;

    private PropertyState $barcode_No_state;

    private PropertyState $company_Name_state;

    private PropertyState $product_Name_state;

    private PropertyState $tax_state;

    private PropertyState $mrp_state;

    private PropertyState $uom_state;

    private PropertyState $product_ID_state;

    private String hSNno;

    private String barcode_No;

    private String company_Name;

    private String product_Name;

    private int tax;

    private Double mrp;

    private String uom;

    private String product_ID;

    private final transient EntityProxy<ProductsEntity> $proxy = new EntityProxy<ProductsEntity>(this, $TYPE);

    public ProductsEntity() {
    }

    @Override
    public String getHSNno() {
        return $proxy.get(H_SNNO);
    }

    public void setHSNno(String hSNno) {
        $proxy.set(H_SNNO, hSNno);
    }

    @Override
    public String getBarcode_No() {
        return $proxy.get(BARCODE_NO);
    }

    public void setBarcode_No(String barcode_No) {
        $proxy.set(BARCODE_NO, barcode_No);
    }

    @Override
    public String getCompany_Name() {
        return $proxy.get(COMPANY_NAME);
    }

    public void setCompany_Name(String company_Name) {
        $proxy.set(COMPANY_NAME, company_Name);
    }

    @Override
    public String getProduct_Name() {
        return $proxy.get(PRODUCT_NAME);
    }

    public void setProduct_Name(String product_Name) {
        $proxy.set(PRODUCT_NAME, product_Name);
    }

    @Override
    public int getTax() {
        return $proxy.get(TAX);
    }

    public void setTax(int tax) {
        $proxy.set(TAX, tax);
    }

    @Override
    public Double getMRP() {
        return $proxy.get(MRP);
    }

    public void setMRP(Double mrp) {
        $proxy.set(MRP, mrp);
    }

    @Override
    public String getUOM() {
        return $proxy.get(UOM);
    }

    public void setUOM(String uom) {
        $proxy.set(UOM, uom);
    }

    @Override
    public String getProduct_ID() {
        return $proxy.get(PRODUCT_ID);
    }

    public void setProduct_ID(String product_ID) {
        $proxy.set(PRODUCT_ID, product_ID);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ProductsEntity && ((ProductsEntity)obj).$proxy.equals(this.$proxy);
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
