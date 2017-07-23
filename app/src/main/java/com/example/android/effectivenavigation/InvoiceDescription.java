package com.example.android.effectivenavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.effectivenavigation.database.InvoiceEntity;
import com.example.android.effectivenavigation.database.TransactionTableEntity;

import java.util.ArrayList;
import java.util.List;

import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.util.CloseableIterator;

public class InvoiceDescription extends AppCompatActivity {

    final ArrayList<item> items=new ArrayList<>();
    ItemListAdapterInvoice adapter;
    private ReactiveEntityStore<Persistable> data;
    TextView invno;
    TextView mobile;
    TextView total;
    TextView taxes;
    TextView discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=((ProductApplication)getApplication()).getData();
        setContentView(R.layout.activity_invoice_description);
        invno=(TextView)findViewById(R.id.invoiceid);
        mobile=(TextView)findViewById(R.id.invmobile);
        total=(TextView)findViewById(R.id.invtotal);
        taxes=(TextView)findViewById(R.id.invtaxes);
        discount=(TextView)findViewById(R.id.invdiscount);
        final ListView listView=(ListView)findViewById(R.id.listviewinvoice);
        listView.setDividerHeight(5);
        String transactionid=getIntent().getStringExtra("INV");
        CloseableIterator<InvoiceEntity> iterator = data.select(InvoiceEntity.class).where(InvoiceEntity.TRANSACTION_ID.eq(transactionid)).get().iterator();
        while (iterator.hasNext()){
            InvoiceEntity invoiceEntity=iterator.next();
            items.add(new item(invoiceEntity.getName(),Double.parseDouble(invoiceEntity.getPrice()),invoiceEntity.getQuantity(),1,false,invoiceEntity.getBarcode(),0.0,Double.parseDouble(invoiceEntity.getProductTax()==null?"0":invoiceEntity.getProductTax())));
        }
        invno.setText(transactionid);
        TransactionTableEntity entity=new TransactionTableEntity();
        entity=data.select(TransactionTableEntity.class).where(TransactionTableEntity.TRANSACTION_ID.eq(transactionid)).get().firstOrNull();

            mobile.setText(entity.getCustomerMobile());
            total.setText(entity.getTransactionValue());
        String temp=entity.getTaxCollected();
        temp=temp.substring(0,temp.indexOf('.')+2);
        taxes.setText(temp);
            discount.setText(entity.getDiscount()==null?"0.0":entity.getDiscount());
            adapter = new ItemListAdapterInvoice(this, R.layout.single_invoice, items);
            listView.setAdapter(adapter);
    }
}
