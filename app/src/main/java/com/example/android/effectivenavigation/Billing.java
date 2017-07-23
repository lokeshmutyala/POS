package com.example.android.effectivenavigation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.effectivenavigation.database.InvoiceEntity;
import com.example.android.effectivenavigation.database.ManualProductsEntity;
import com.example.android.effectivenavigation.database.ProductsEntity;
import com.example.android.effectivenavigation.database.TransactionTableEntity;
import com.example.android.effectivenavigation.printer.printfile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.util.CloseableIterator;

public class Billing extends AppCompatActivity {

    LinearLayout root;
    ArrayList<item> itemlist = new ArrayList<>();
    FloatingActionButton fab;
    FloatingActionButton createinvoice;
    FloatingActionButton end;
    FloatingActionButton stop;
    FloatingActionButton cancel;
    ItemListAdapterRecycler adapter;
    AlertDialog alertDialog;
    double TodaySale=0;
    double Gross=0;
    double Net=0;
    double Discount=0;
    double xDiscount=0;
    double Tax=0;
    int bills=0;
    SharedPreferences sharedPreferences;
    private ReactiveEntityStore<Persistable> data;
    boolean x=true;
    String captured_barcode="";
    String previous="";
    boolean isinvoice=false;
    TextView gross;
    TextView net;
    EditText discount;
    TextView tax;
    private Boolean isFabOpen = false;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    String transactionId="";
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //char l=(char) event.getUnicodeChar();
//        if(s==discount.getText().toString()){
//            s="";
//        }
        // if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER) {
            // Log.d("Key check:",l);
            if (x) {
                char pressedKey = (char) event.getUnicodeChar();
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                    if (s.length() < 8) {
//                        s = "";
//                    }else
                    if(captured_barcode.equals(discount.getText().toString())){
                        captured_barcode="";
                    }
                    else if(captured_barcode.isEmpty()||captured_barcode.length()<7){
                        captured_barcode="";
                    }
                    else {
                        Log.d("Key Check",discount.getText().toString()+captured_barcode);
                        //Toast.makeText(getApplication(), s, Toast.LENGTH_LONG).show();
                        //s = s.substring(0, s.length() - 1);
                        if(captured_barcode.equals(previous)){

                            itemlist.get(itemlist.size()-1).setQuantity(itemlist.get(itemlist.size()-1).getQuantity()+1);
                            adapter.notifyDataSetChanged();
                            setTotal();
                            captured_barcode="";
                        }else {
                            Log.d("Previous:s",previous+":"+captured_barcode);
                            previous=captured_barcode;
                            addItem(false);
                            captured_barcode = "";
                        }
                    }
                }else {
                    if(Character.isDigit(pressedKey))
                    captured_barcode += pressedKey;
                }
                x = false;
            } else {
                x = true;
            }
            //return true;

        return super.dispatchKeyEvent(event);
    }

    void addItem(boolean ismanual){
        if(ismanual){

        }
        else {
            final List<ProductsEntity> list= data.select(ProductsEntity.class).where(ProductsEntity.BARCODE_NO.eq(captured_barcode)).get().toList();
            final ArrayList<ProductsEntity> alist=new ArrayList<ProductsEntity>(list);//.addAll(list);
            Log.d("check size",String.valueOf(list.size()));
            Log.d("check asize",String.valueOf(alist.size()));
            if(list.size()<=0){
                final List<ManualProductsEntity> mlist=data.select(ManualProductsEntity.class).where(ManualProductsEntity.BARCODE_NO.eq(captured_barcode)).get().toList();
                if(mlist.size()<=0) {
                    Toast.makeText(getApplication(), "item not in list", Toast.LENGTH_LONG).show();
                    fab.performClick();
                }
                else {
                    adapter.mFocusedView = null;
                    itemlist.add(new item(mlist.get(0).getProductDescription(),mlist.get(0).getPrice(),1,0,isinvoice,captured_barcode,0.0,mlist.get(0).getTax()));
                    adapter.notifyDataSetChanged();
                    setTotal();
                }
            }else {
                if(list.size()>1){
                    Log.d("check inside","its inside");
                    //pop up alert to select any one of avalable items
                    LayoutInflater inflater = LayoutInflater.from(getBaseContext());
                    View promptsView = inflater.inflate(R.layout.duplicate_barcodes, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Billing.this);
                    alertDialogBuilder.setView(promptsView);
                    ListView duplicatelistview=(ListView)promptsView.findViewById(R.id.duplicatelistview);
                    ItemListAdapterDuplicate itemListAdapterDuplicate=new ItemListAdapterDuplicate(getApplicationContext(),R.layout.single_duplicate,alist);
                    duplicatelistview.setAdapter(itemListAdapterDuplicate);
                    final AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    duplicatelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position>0 && position<alist.size()){
                                itemlist.add(new item(list.get(position-1).getProduct_Name(),list.get(position-1).getMRP(),1,0,isinvoice,captured_barcode,0.0,list.get(position-1).getTax()));
                                adapter.notifyDataSetChanged();
                                alertDialog.dismiss();
                                setTotal();
                            }
                        }
                    });

                    captured_barcode="";
                    return;
                }
//                for(item i:itemlist){
//                    Log.d("For check",s+" "+i.getBarcode());
//                    if(i.getBarcode()==s ){
//                        i.setQuantity(i.getQuantity()+1);
//                        return;
//                    }
//                }
                itemlist.add(new item(list.get(0).getProduct_Name(),list.get(0).getMRP(),1,0,isinvoice,captured_barcode,0.0,list.get(0).getTax()));
                adapter.notifyDataSetChanged();

                //Toast.makeText(getApplication(),"item added",Toast.LENGTH_LONG).show();
                //total.setText(String.valueOf(adapter.getTotal()));
                setTotal();
            }
        }

    }

    public static final String MyPreferences="My prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        TodaySale=sharedPreferences.getFloat("TodaySale",0);
        java.text.SimpleDateFormat dateFormat=new java.text.SimpleDateFormat("yyyyMMdd");//(''yy, MMM d, EEE");
        final String date=dateFormat.format(new Date());
        bills=sharedPreferences.getInt("Bills",0);
        setContentView(R.layout.activity_billing);
        createinvoice = (FloatingActionButton) findViewById(R.id.create);
        cancel=(FloatingActionButton) findViewById(R.id.cancel_action);
        end = (FloatingActionButton) findViewById(R.id.end);
        stop=(FloatingActionButton)findViewById(R.id.stop);
        gross = (TextView) findViewById(R.id.gross);
        net = (TextView) findViewById(R.id.net);
        discount = (EditText) findViewById(R.id.discount);
        tax=(TextView) findViewById(R.id.tax);
        data=((ProductApplication)getApplication()).getData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        root=(LinearLayout) findViewById(R.id.linearLayout);
        setSupportActionBar(toolbar);
        transactionId=sharedPreferences.getString("StoreId","StoreId")+date;
        //setActionBar(toolbar);
        //this.getSupportActionBar().setTitle("Billing Page");
//
//        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setTitle("Billing Page");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.startAnimation(rotate_backward);
                end.startAnimation(fab_close);
                createinvoice.startAnimation(fab_close);
                cancel.startAnimation(fab_close);
                end.setClickable(false);
                createinvoice.setClickable(false);
                cancel.setClickable(false);
                isFabOpen = false;

                if(!itemlist.isEmpty()){
                AlertDialog.Builder alert = new AlertDialog.Builder(Billing.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete all items?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemlist.clear();
                        adapter.notifyDataSetChanged();
                        setTotal();
                        captured_barcode="";
                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                }
            }
        });
        //final ListView mListView = (ListView) findViewById(R.id.listview);
        final RecyclerView mListView=(RecyclerView) findViewById(R.id.listview);
        mListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mListView.setClipToPadding(false);

        //mListView.setDividerHeight(5);
        //sample items
       /* item smaple1 = new item("Ashirvad atta", 120.00, 2, 1, false, "12121212");
        item smaple2 = new item("Ashirvad atta", 50.00, 2, 2, false, "12121212");
        item smaple3 = new item("Ashirvad atta", 120.00, 2, 3, false, "1212121");
        item smaple4 = new item("Ashirvad atta", 120.00, 2, 4, false, "12121212");
        item smaple5 = new item("Ashirvad atta", 120.00, 2, 5, false, "121212");

        itemlist.add(smaple1);
        itemlist.add(smaple2);
        itemlist.add(smaple3);
        itemlist.add(smaple4);
        itemlist.add(smaple5);
        itemlist.add(smaple1);
        itemlist.add(smaple2);
        itemlist.add(smaple3);
        itemlist.add(smaple4);
        itemlist.add(smaple5);
*/
        //adapter = new ItemListAdapter(this, R.layout.single_item, itemlist);
        adapter=new ItemListAdapterRecycler(itemlist,this);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                mListView.scrollToPosition(itemlist.size()-1);
            }
        });
        mListView.setAdapter(adapter);
        /*adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mListView.setSelection(adapter.getCount()-1);
            }
        });
*/
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

         fab = (FloatingActionButton) findViewById(R.id.fab);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFabOpen) {
                    stop.performClick();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!itemlist.isEmpty()) {
                    if (isFabOpen) {

                        stop.startAnimation(rotate_backward);
                        cancel.startAnimation(fab_close);
                        end.startAnimation(fab_close);
                        createinvoice.startAnimation(fab_close);
                        end.setClickable(false);
                        cancel.setClickable(false);
                        createinvoice.setClickable(false);
                        isFabOpen = false;
                        Log.d("Lokesh", "cl.ose");

                    } else {

                        stop.startAnimation(rotate_forward);
                        end.startAnimation(fab_open);
                        cancel.startAnimation(fab_open);
                        createinvoice.startAnimation(fab_open);
                        end.setClickable(true);
                        cancel.setClickable(true);
                        createinvoice.setClickable(true);
                        isFabOpen = true;
                        Log.d("Lokesh", "open");

                    }
                }

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                LayoutInflater inflater = LayoutInflater.from(getBaseContext());
                View promptsView = inflater.inflate(R.layout.manual, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Billing.this);
                alertDialogBuilder.setView(promptsView);
                final Spinner spinner=(Spinner)promptsView.findViewById(R.id.spinner);
                final List<String> taxslabs=new ArrayList<String>();
                taxslabs.add("0");
                taxslabs.add("5");
                taxslabs.add("12");
                taxslabs.add("18");
                taxslabs.add("28");
                ArrayAdapter<String> dataadapter =new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,taxslabs);
                dataadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner.setAdapter(dataadapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Tax=Double.parseDouble(parent.getItemAtPosition(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        Tax=0;
                    }
                });
                final AutoCompleteTextView barcode_no=(AutoCompleteTextView)promptsView.findViewById(R.id.barcode_no);
                barcode_no.setDropDownBackgroundResource(R.drawable.laoutbgfff);
                barcode_no.setText(captured_barcode);
                final CloseableIterator<ProductsEntity> closeableIterator = data.select(ProductsEntity.class).get().iterator();
                ArrayList<String> barcodedata=new ArrayList<String>();
                while(closeableIterator.hasNext()){
                    barcodedata.add(closeableIterator.next().getBarcode_No());
                }

                final ArrayAdapter<String> madapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,barcodedata);
                final EditText description = (EditText) promptsView.findViewById(R.id.description);
                final EditText quantity_no = (EditText) promptsView.findViewById(R.id.quantity_no);
                final EditText price = (EditText) promptsView.findViewById(R.id.price);
                barcode_no.setAdapter(madapter);
                barcode_no.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedbarcode=barcode_no.getAdapter().getItem(position).toString();
                        ProductsEntity entity=data.select(ProductsEntity.class).where(ProductsEntity.BARCODE_NO.eq(selectedbarcode)).get().first();
                        description.setText(entity.getProduct_Name());
                        price.setText(String.valueOf(entity.getMRP()));
                        int selection=entity.getTax();
                        switch(selection){
                            case 5:
                                spinner.setSelection(1);
                                break;
                            case 12:
                                spinner.setSelection(2);
                                break;
                            case 18:
                                spinner.setSelection(3);
                                break;
                            case 28:
                                spinner.setSelection(4);
                                break;
                            default:
                                spinner.setSelection(0);
                                break;
                        }


                    }
                });

                alertDialogBuilder.setCancelable(true).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!(quantity_no.getText().toString().isEmpty()||price.getText().toString().isEmpty())) {

                            Log.d("Lokesh:"," entered into if");
                            adapter.mFocusedView = null;
                            if(Tax==0){
                               List<ProductsEntity> tlist= data.select(ProductsEntity.class).where(ProductsEntity.BARCODE_NO.eq(barcode_no.getText().toString())).get().toList();
                                if(tlist.size()>0){
                                    Tax=tlist.get(0).getTax();
                                }
                            }
                            itemlist.add(new item(description.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(quantity_no.getText().toString()), 1, false, barcode_no.getText().toString(),0.0,Tax));
                            adapter.notifyDataSetChanged();
                            setTotal();
                            ManualProductsEntity mEntity=new ManualProductsEntity();
                            mEntity.setBarcode_No(barcode_no.getText().toString());
                            mEntity.setPrice(Double.parseDouble(price.getText().toString()));
                            mEntity.setProductDescription(description.getText().toString());
                            data.insert(mEntity).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<ManualProductsEntity>() {
                                @Override
                                public void accept(@io.reactivex.annotations.NonNull ManualProductsEntity manualProductsEntity) throws Exception {
                               Toast.makeText(getApplicationContext(),"manually inserted",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                //alertDialog.getWindow().setLayout(600,300);
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });
        createinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.startAnimation(rotate_backward);
                end.startAnimation(fab_close);
                createinvoice.startAnimation(fab_close);
                cancel.startAnimation(fab_close);
                end.setClickable(false);
                createinvoice.setClickable(false);
                cancel.setClickable(false);
                isFabOpen = false;

                if(!itemlist.isEmpty()) {

                    if(bills<10){
                        transactionId+="000"+String.valueOf(bills+1);
                    }else if(bills<100){
                        transactionId+="00"+String.valueOf(bills+1);
                    }else if(bills<1000){
                        transactionId+="0"+String.valueOf(bills+1);
                    }else {
                        transactionId+="0"+String.valueOf(bills+1);
                    }

                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Billing.this);
                    final View parentview = getLayoutInflater().inflate(R.layout.bottom_pager, null);
                    TextView invno=(TextView)parentview.findViewById(R.id.invno);
                    invno.setText(transactionId);
                    bottomSheetDialog.setContentView(parentview);
                    final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parentview.getParent());
                    //bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics()));

                    bottomSheetBehavior.setPeekHeight(500);
                    bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    bottomSheetDialog.show();
                    final ImageButton invoice = (ImageButton) parentview.findViewById(R.id.save);

                    invoice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText mobie = (EditText) parentview.findViewById(R.id.mobile);
                            previous="";
                            if (mobie.getText().length() < 10 || Character.getNumericValue(mobie.getText().toString().charAt(0)) < 7) {
                                Toast.makeText(getBaseContext(), "Please enter a valid mobile no", Toast.LENGTH_LONG).show();
                            } else {
                                printfile.mContext=getApplicationContext();
                                try {
                                    Log.d("check print","trying to print");
                                    printfile.prints();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.d("error","error proint"+e.toString());
                                }
                                TodaySale += Double.parseDouble(net.getText().toString());
                                bills++;
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putFloat("TodaySale",(float) TodaySale);
                                editor.putInt("Bills",bills);
                                editor.commit();

                                TransactionTableEntity transactionTable=new TransactionTableEntity();
                                transactionTable.setTransactionId(transactionId);
                                transactionTable.setStoreId(sharedPreferences.getString("StoreId","STORE_ID"));
                                transactionTable.setCustomerMobile(mobie.getText().toString());
                                transactionTable.setTransactionValue(String.valueOf(Net));
                                transactionTable.setTransactionTime(""+new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date()));//(""+System.currentTimeMillis());
                                transactionTable.setSyncStatus(false);
                                transactionTable.setDiscount(String.valueOf(Discount));
                                transactionTable.setTaxCollected(String.valueOf(Tax));
                                data.insert(transactionTable).observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionTableEntity>() {
                                    @Override
                                    public void accept(@io.reactivex.annotations.NonNull TransactionTableEntity transactionTableEntity) throws Exception {
                                        //Toast.makeText(getApplication(),"Bill Completed",Toast.LENGTH_LONG).show();
                                    }
                                });
                                setTotal();
                                for (item i : itemlist) {
                                    InvoiceEntity invoiceEntity = new InvoiceEntity();
                                    invoiceEntity.setTransactionId(transactionId);
                                    invoiceEntity.setBarcode(i.getBarcode());
                                    invoiceEntity.setQuantity(i.getQuantity());
                                    invoiceEntity.setDiscount(String.valueOf(i.getaDouble()));
                                    invoiceEntity.setPrice(String.valueOf(i.getPrice()));
                                    invoiceEntity.setName(i.getName());
                                    invoiceEntity.setSyncStatus(false);
                                    data.insert(invoiceEntity).observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io()).subscribe(new Consumer<InvoiceEntity>() {
                                        @Override
                                        public void accept(@io.reactivex.annotations.NonNull InvoiceEntity invoiceEntity) throws Exception {
                                            Toast.makeText(getApplicationContext(),"invoice inserted",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }


                                bottomSheetDialog.dismiss();
                                if (!itemlist.isEmpty()) {
                                    itemlist.clear();
                                    adapter.notifyDataSetChanged();
                                    gross.setText("000");
                                    net.setText("000");
//                                 itemlist.clear();
//                                    adapter.notifyDataSetChanged();
//                                    gross.setText("000");
//                                    net.setText("000");
//                                    discount.setText("00");
                                    captured_barcode="";
                                    Intent intentsms = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" +mobie.getText().toString() ));
                                    intentsms.putExtra("sms_body", "invoice no: "+  transactionId+" for the value of "+String.valueOf(Gross));
                                    Log.d("Gross Check",String.valueOf(Gross));
                                    startActivity(intentsms);

                                }
                                setTotal();
                            }
                        }
                    });

                }
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous="";
                stop.startAnimation(rotate_backward);
                end.startAnimation(fab_close);
                createinvoice.startAnimation(fab_close);
                cancel.startAnimation(fab_close);
                end.setClickable(false);
                createinvoice.setClickable(false);
                cancel.setClickable(false);
                isFabOpen = false;

                if(!itemlist.isEmpty()){
                    setTotal();
                    TodaySale+=Double.parseDouble(net.getText().toString());
                    Log.d("TodaySale=",String.valueOf(TodaySale));
                    bills++;
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putFloat("TodaySale",(float) TodaySale);
                    editor.putInt("Bills",bills);
                    editor.commit();
                    String transactionId=sharedPreferences.getString("StoreId","StoreId")+date;
                    if(bills<10){
                        transactionId+="000"+String.valueOf(bills);
                    }else if(bills<100){
                        transactionId+="00"+String.valueOf(bills);
                    }else if(bills<1000){
                        transactionId+="0"+String.valueOf(bills);
                    }else {
                        transactionId+="0"+String.valueOf(bills);
                    }
                    TransactionTableEntity transactionTable=new TransactionTableEntity();
                    transactionTable.setTransactionId(transactionId);
                    transactionTable.setStoreId(sharedPreferences.getString("StoreId","STORE_ID"));
                    //transactionTable.setCustomerMobile(mobie.getText().toString());
                    transactionTable.setTransactionValue(String.valueOf(Net));
                    transactionTable.setSyncStatus(false);
                    transactionTable.setTaxCollected(String.valueOf(Tax));
                    transactionTable.setTransactionTime(""+new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date()));//(""+System.currentTimeMillis());
                    data.insert(transactionTable).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionTableEntity>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull TransactionTableEntity transactionTableEntity) throws Exception {
                            //Toast.makeText(getApplication(),"Bill Completed",Toast.LENGTH_LONG).show();
                        }
                    });
                    for (item i : itemlist) {
                        InvoiceEntity invoiceEntity = new InvoiceEntity();
                        invoiceEntity.setTransactionId(transactionId);
                        invoiceEntity.setBarcode(i.getBarcode());
                        invoiceEntity.setQuantity(i.getQuantity());
                        invoiceEntity.setDiscount(String.valueOf(i.getaDouble()));
                        Log.d("IteratorCheck",String.valueOf(i.getQuantity()));
                        invoiceEntity.setPrice(String.valueOf(i.getPrice()));
                        invoiceEntity.setName(i.getName());
                        invoiceEntity.setSyncStatus(false);
                        data.insert(invoiceEntity).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<InvoiceEntity>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull InvoiceEntity invoiceEntity) throws Exception {
                                Toast.makeText(getApplication(),"Invoice inserted successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                    }


                    itemlist.clear();
                    adapter.notifyDataSetChanged();
                    gross.setText("000");
                    net.setText("000");
                    discount.setText("00");
                    tax.setText("000");
                    captured_barcode="";
                }
            }
        });
discount.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        captured_barcode="";
    }

    @Override
    public void afterTextChanged(Editable s) {
        captured_barcode="";
        Net=0;
        Gross=0;
        Discount=0;
//        if(!discount.getText().toString().isEmpty()){
//            Discount=Double.parseDouble(discount.getText().toString());
//        }
        for (item i : itemlist) {
            Gross += i.getPrice() * i.getQuantity();
            Discount+=i.getaDouble()*i.getQuantity();
        }
        if(! discount.getText().toString().isEmpty()) {
            xDiscount= Double.parseDouble(discount.getText().toString());
        }
        gross.setText(String.valueOf(Gross));
       // discount.setText(String.valueOf(Discount));
        Net=Gross-Discount-xDiscount;
        if(Net<0){
            Snackbar.make(discount,"Discount can not be applied on 0 bills",2000).setAction("Action",null).show();
            //Toast.makeText(getApplicationContext(),"Discount can not be applied on 0 bills",Toast.LENGTH_LONG).show();
            net.setText("000");
            discount.setText("0");
            return;
        }
        net.setText(String.valueOf(Net));

    }
});
    }

    @Override
        public void onBackPressed () {
            if (alertDialog != null) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }else if(discount.isFocused()){
                discount.clearFocus();

            }
            else {
                InputMethodManager imm=(InputMethodManager)this.getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(discount.getApplicationWindowToken(),0);
                super.onBackPressed();
            }
        }

    public void setTotal() {

        captured_barcode="";
        Net=0;
        Gross=0;
        Discount=0;
        Tax=0;
        try {
            xDiscount = Double.parseDouble(discount.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
//        if(!discount.getText().toString().isEmpty()){
//            Discount=Double.parseDouble(discount.getText().toString());
//        }
        for (item i : itemlist) {
            Gross += i.getPrice() * i.getQuantity();
            Discount+=i.getaDouble()*i.getQuantity();
            Tax+=(i.getPrice()-i.getaDouble())*(i.getTax()/(100+i.getTax()))*i.getQuantity();
        }
        gross.setText(String.valueOf(Gross));
        //discount.setText(String.valueOf(Discount));
        Net=Gross-Discount-xDiscount;
        if(Net<0){
            Snackbar.make(discount,"Discount can not be applied on 0 bills",2000).setAction("Action",null).show();
            //Toast.makeText(getApplicationContext(),"Discount can not be applied on 0 bills",Toast.LENGTH_LONG).show();
            net.setText("000");
            discount.setText("0");
            return;
        }
        net.setText(String.valueOf(Net));
        String temp=String.valueOf(Tax);
        temp=temp.substring(0,temp.indexOf('.')+2);
        tax.setText(temp);
        //discount.setText("00");
    }

}