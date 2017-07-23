// Generated file do not edit, generated by io.requery.processor.EntityProcessor
package com.example.android.effectivenavigation.database;

import io.requery.Persistable;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public class CustomerTableEntity implements CustomerTable, Persistable {
    public static final QueryAttribute<CustomerTableEntity, String> CUSTOMER_MOBILE = 
    new AttributeBuilder<CustomerTableEntity, String>("customerMobile", String.class)
    .setProperty(new Property<CustomerTableEntity, String>() {
        @Override
        public String get(CustomerTableEntity entity) {
            return entity.customerMobile;
        }

        @Override
        public void set(CustomerTableEntity entity, String value) {
            entity.customerMobile = value;
        }
    })
    .setPropertyName("getCustomerMobile")
    .setPropertyState(new Property<CustomerTableEntity, PropertyState>() {
        @Override
        public PropertyState get(CustomerTableEntity entity) {
            return entity.$customerMobile_state;
        }

        @Override
        public void set(CustomerTableEntity entity, PropertyState value) {
            entity.$customerMobile_state = value;
        }
    })
    .setKey(true)
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<CustomerTableEntity, String> CUSTOMER_NAME = 
    new AttributeBuilder<CustomerTableEntity, String>("customerName", String.class)
    .setProperty(new Property<CustomerTableEntity, String>() {
        @Override
        public String get(CustomerTableEntity entity) {
            return entity.customerName;
        }

        @Override
        public void set(CustomerTableEntity entity, String value) {
            entity.customerName = value;
        }
    })
    .setPropertyName("getCustomerName")
    .setPropertyState(new Property<CustomerTableEntity, PropertyState>() {
        @Override
        public PropertyState get(CustomerTableEntity entity) {
            return entity.$customerName_state;
        }

        @Override
        public void set(CustomerTableEntity entity, PropertyState value) {
            entity.$customerName_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<CustomerTableEntity, String> CUSTOMER_GENDER = 
    new AttributeBuilder<CustomerTableEntity, String>("customerGender", String.class)
    .setProperty(new Property<CustomerTableEntity, String>() {
        @Override
        public String get(CustomerTableEntity entity) {
            return entity.customerGender;
        }

        @Override
        public void set(CustomerTableEntity entity, String value) {
            entity.customerGender = value;
        }
    })
    .setPropertyName("getCustomerGender")
    .setPropertyState(new Property<CustomerTableEntity, PropertyState>() {
        @Override
        public PropertyState get(CustomerTableEntity entity) {
            return entity.$customerGender_state;
        }

        @Override
        public void set(CustomerTableEntity entity, PropertyState value) {
            entity.$customerGender_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<CustomerTableEntity, String> CUSTOMER_AGE = 
    new AttributeBuilder<CustomerTableEntity, String>("customerAge", String.class)
    .setProperty(new Property<CustomerTableEntity, String>() {
        @Override
        public String get(CustomerTableEntity entity) {
            return entity.customerAge;
        }

        @Override
        public void set(CustomerTableEntity entity, String value) {
            entity.customerAge = value;
        }
    })
    .setPropertyName("getCustomerAge")
    .setPropertyState(new Property<CustomerTableEntity, PropertyState>() {
        @Override
        public PropertyState get(CustomerTableEntity entity) {
            return entity.$customerAge_state;
        }

        @Override
        public void set(CustomerTableEntity entity, PropertyState value) {
            entity.$customerAge_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final QueryAttribute<CustomerTableEntity, String> CREATED_TIME = 
    new AttributeBuilder<CustomerTableEntity, String>("createdTime", String.class)
    .setProperty(new Property<CustomerTableEntity, String>() {
        @Override
        public String get(CustomerTableEntity entity) {
            return entity.createdTime;
        }

        @Override
        public void set(CustomerTableEntity entity, String value) {
            entity.createdTime = value;
        }
    })
    .setPropertyName("getCreatedTime")
    .setPropertyState(new Property<CustomerTableEntity, PropertyState>() {
        @Override
        public PropertyState get(CustomerTableEntity entity) {
            return entity.$createdTime_state;
        }

        @Override
        public void set(CustomerTableEntity entity, PropertyState value) {
            entity.$createdTime_state = value;
        }
    })
    .setGenerated(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build();

    public static final Type<CustomerTableEntity> $TYPE = new TypeBuilder<CustomerTableEntity>(CustomerTableEntity.class, "CustomerTable")
    .setBaseType(CustomerTable.class)
    .setCacheable(true)
    .setImmutable(false)
    .setReadOnly(false)
    .setStateless(false)
    .setView(false)
    .setFactory(new Supplier<CustomerTableEntity>() {
        @Override
        public CustomerTableEntity get() {
            return new CustomerTableEntity();
        }
    })
    .setProxyProvider(new Function<CustomerTableEntity, EntityProxy<CustomerTableEntity>>() {
        @Override
        public EntityProxy<CustomerTableEntity> apply(CustomerTableEntity entity) {
            return entity.$proxy;
        }
    })
    .addAttribute(CUSTOMER_AGE)
    .addAttribute(CUSTOMER_MOBILE)
    .addAttribute(CREATED_TIME)
    .addAttribute(CUSTOMER_NAME)
    .addAttribute(CUSTOMER_GENDER)
    .build();

    private PropertyState $customerMobile_state;

    private PropertyState $customerName_state;

    private PropertyState $customerGender_state;

    private PropertyState $customerAge_state;

    private PropertyState $createdTime_state;

    private String customerMobile;

    private String customerName;

    private String customerGender;

    private String customerAge;

    private String createdTime;

    private final transient EntityProxy<CustomerTableEntity> $proxy = new EntityProxy<CustomerTableEntity>(this, $TYPE);

    public CustomerTableEntity() {
    }

    @Override
    public String getCustomerMobile() {
        return $proxy.get(CUSTOMER_MOBILE);
    }

    public void setCustomerMobile(String customerMobile) {
        $proxy.set(CUSTOMER_MOBILE, customerMobile);
    }

    @Override
    public String getCustomerName() {
        return $proxy.get(CUSTOMER_NAME);
    }

    public void setCustomerName(String customerName) {
        $proxy.set(CUSTOMER_NAME, customerName);
    }

    @Override
    public String getCustomerGender() {
        return $proxy.get(CUSTOMER_GENDER);
    }

    public void setCustomerGender(String customerGender) {
        $proxy.set(CUSTOMER_GENDER, customerGender);
    }

    @Override
    public String getCustomerAge() {
        return $proxy.get(CUSTOMER_AGE);
    }

    public void setCustomerAge(String customerAge) {
        $proxy.set(CUSTOMER_AGE, customerAge);
    }

    @Override
    public String getCreatedTime() {
        return $proxy.get(CREATED_TIME);
    }

    public void setCreatedTime(String createdTime) {
        $proxy.set(CREATED_TIME, createdTime);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CustomerTableEntity && ((CustomerTableEntity)obj).$proxy.equals(this.$proxy);
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