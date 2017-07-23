package com.example.android.effectivenavigation.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.android.effectivenavigation.ProductApplication;
import com.example.android.effectivenavigation.database.InvoiceEntity;
import com.example.android.effectivenavigation.database.ManualProductsEntity;
import com.example.android.effectivenavigation.database.ProductsEntity;
import com.example.android.effectivenavigation.database.TransactionTableEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lokeshmutyala on 17-07-2017.
 */

public class UpdateAllProducts {
    final static String baseUrl="http://54.245.52.20/";
    static String lastsync="00";
    public static String storeid="";
    static boolean productsyncstatus=false;
    static boolean transactionsyncstatus=false;
    static boolean invoicesyncstatus=false;
   public static boolean mAuth=false;
   public static boolean check=false;
    static String result="";
    public static String sendMail(JSONObject object){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitProductApi email=retrofit.create(RetrofitProductApi.class);
        Call<String> sendemail=email.sendmail(object);
        sendemail.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Log.d("check responce",response.body().toString());
                if(response.body().toString().contains("Message has been sent")){
                    result="success";
                }
            }

            @Override
            public void onFailure(Throwable t) {

                result="fail";
            }
        });
        return result;
    }
    public static boolean register(JSONObject object){
    mAuth=false;
        check=false;
        Log.d("entered service","yes");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
RetrofitProductApi registerservice=retrofit.create(RetrofitProductApi.class);
        Call<String> register=registerservice.register(object);
        register.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                check=true;
                Log.d("check responce","reg responce"+response.body().toString());
                if(response.body().contains("success")){
                    mAuth=true;
                    storeid=""+response.body().substring(7,20);
                    Log.d("storeid=",storeid);
                    if(storeid==""){
                        mAuth=false;
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

                check=true;
                mAuth=false;
                Log.d("error here","yes "+t.toString());
            }
        });
        return mAuth;
    }
    public static boolean logincheck(String mEmail,String mPassword){

        mAuth=false;
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("email", mEmail);
            jsonObject.put("pwd", mPassword);
        }catch (JSONException e){
            e.printStackTrace();
        }
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitProductApi loginservice=retrofit.create(RetrofitProductApi.class);
        Call<String> login= loginservice.logincheck(jsonObject);
        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if(response.body().contains("success")){
                    Log.d("check true","success");
                    mAuth=true;
                    storeid=""+response.body().substring(7,20);
                    Log.d("storeid=",storeid);
                    if(storeid==""){
                        mAuth=false;
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mAuth=false;
                Log.d("check error","here t"+t.toString());
            }
        });

        return mAuth;
    }
    public static void getRetrofitArray(final ReactiveEntityStore<Persistable> data, final SharedPreferences sharedPreferences, final Context context){
        if(sharedPreferences.contains("lastsync")){
            lastsync=sharedPreferences.getString("lastsync","00");
        }
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitProductApi service=retrofit.create(RetrofitProductApi.class);

        Call<JsonArray> call=service.getAllProducts(lastsync);
        Log.d("check lastsync",""+lastsync);
        /*call.enqueue(new Callback<List<ProductsEntity>>() {
            @Override
            public void onResponse(Response<List<ProductsEntity>> response, Retrofit retrofit) {
                Log.d("check responce","showing"+response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Response<JsonArray> response, Retrofit retrofit) {

                //Log.d("check products",""+response.body().toString());
                if(response.body().toString().contentEquals("[{\"success\":\"success\"}]")){
                    productsyncstatus=true;
                    Log.d("check products","success");
                }else {
                    for (int i = 0; i < response.body().size(); i++) {
                        try {
                            JSONObject j = new JSONObject(response.body().get(i).toString());
                            Log.d("check responce of api", j.toString());
                            ProductsEntity p = new ProductsEntity();
                            p.setBarcode_No(j.getString("Barcode_No"));
                            p.setCompany_Name(j.getString("Company_Name"));
                            p.setProduct_Name(j.getString("Product_Name"));
                            p.setTax(j.getInt("Tax"));
                            p.setMRP(j.getDouble("MRP"));
                            p.setUOM(j.getString("UOM"));
                            p.setProduct_ID(j.getString("Product_ID"));
                            data.insert(p).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                                    .subscribe(new Consumer<ProductsEntity>() {
                                        @Override
                                        public void accept(@NonNull ProductsEntity productsEntity) throws Exception {
                                            Log.d("check Update", "inserted successfully");
                                            lastsync = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("lastsync", lastsync);
                                            editor.commit();
                                        }
                                    });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //Log.d("check json","here "+response.body().get(1).toString());
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("error error",t.toString());
            }
        });

        final List<TransactionTableEntity> transactionTableEntities = data.select(TransactionTableEntity.class).where(TransactionTableEntity.SYNC_STATUS.eq(false)).get().toList();
        final List<InvoiceEntity> invoiceEntities=data.select(InvoiceEntity.class).where(InvoiceEntity.SYNC_STATUS.eq(false)).get().toList();
        final List<ManualProductsEntity> manualProductsEntities=data.select(ManualProductsEntity.class).where(ManualProductsEntity.SYNC_STATUS.eq(false)).get().toList();
        if(invoiceEntities.size()<=0){
            invoicesyncstatus=true;
        }
        if(transactionTableEntities.size()<=0){
            transactionsyncstatus=true;
        }

        JSONArray transactionarray=new JSONArray();
        JSONArray invoicearray=new JSONArray();
        JSONArray manualarray=new JSONArray();

        for (TransactionTableEntity t:transactionTableEntities
             ) {
            JSONObject object=new JSONObject();
            try {
                object.put("tid", t.getTransactionId());
                object.put("tvalue",t.getTransactionValue());
                object.put("mobile",t.getCustomerMobile()==null?"00":t.getCustomerMobile());
                object.put("storeid",t.getStoreId());
                object.put("time",t.getTransactionTime()==null?"00":t.getTransactionTime());
                object.put("loyalityplus",String.valueOf(t.getLoyalityPlus()));
                object.put("loyalityminus",String.valueOf(t.getLoyalityMinus()));
                object.put("tax",t.getTaxCollected()==null?"00":t.getTaxCollected());
                object.put("discount",t.getDiscount()==null?"00":t.getDiscount());
                object.put("gross",t.getGross()==null?"00":t.getGross());
                Log.d("check kjson",object.toString());
                transactionarray.put(object);

            }catch (JSONException e){
                e.printStackTrace();
                Log.d("May be error here",""+e.toString());
            }
        }
        for (InvoiceEntity i:invoiceEntities
             ) {
            JSONObject object=new JSONObject();
            try {
                object.put("tid", i.getTransactionId());
                object.put("price",i.getPrice());
                object.put("name",i.getName()==null?"00":i.getName());
                object.put("barcode",i.getBarcode());
                object.put("quantity",String.valueOf(i.getQuantity()));
                object.put("productid",i.getProductId()==null?"":i.getProductId());
                object.put("tax",i.getProductTax()==null?"00":i.getProductTax());
                object.put("discount",i.getDiscount()==null?"00":i.getDiscount());
                object.put("net",i.getNetValue()==null?"00":i.getNetValue());
                Log.d("check kjson",object.toString());
                invoicearray.put(object);

            }catch (JSONException e){
                e.printStackTrace();
                Log.d("May be error here",""+e.toString());
            }
        }
        for (ManualProductsEntity m:manualProductsEntities
             ) {
            JSONObject object=new JSONObject();
            try{
                object.put("barcode",m.getBarcode_No());
                object.put("price",m.getPrice());
                object.put("description",m.getProductDescription());
                object.put("storeid","store_id");
                manualarray.put(object);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        //Log.d("check gson",""+j.toString());
        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitProductApi service1=retrofit1.create(RetrofitProductApi.class);

//        try {
//            String json = service1.getAllProducts().execute().body().toString();
//            Log.d("check responce1"," "+json);
//        }catch (IOException e){
//            Log.d("error io error",""+e.toString());
//        }

        Call<String> uploadinvoices=service1.uploadinvoices(invoicearray);
        uploadinvoices.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if(response.body().toString().contains("success")){
                    invoicesyncstatus=true;
                    Log.d("check invoices","success");
                    for (InvoiceEntity i:invoiceEntities
                         ) {
                        i.setSyncStatus(true);
                        data.update(i).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io())
                        .subscribe(new Consumer<InvoiceEntity>() {
                            @Override
                            public void accept(@NonNull InvoiceEntity invoiceEntity) throws Exception {
                                Log.d("check update"," here updated "+invoiceEntity.getSyncStatus());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
       Call<String> uploadtransaction=service1.uploadtransactions(transactionarray);
        uploadtransaction.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Log.d("check responce","and "+response.body().toString());
                if(response.body().toString().contains("success")){
                    transactionsyncstatus=true;
                    for (TransactionTableEntity t:transactionTableEntities
                         ) {
                        t.setSyncStatus(true);
                        data.update(t).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io())
                        .subscribe(new Consumer<TransactionTableEntity>() {
                            @Override
                            public void accept(@NonNull TransactionTableEntity entity) throws Exception {
                                Log.d("check update"," here updated "+entity.getSyncStatus());
                            }
                        });
                        Log.d("check status","inside"+t.getSyncStatus());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("check error","here error t "+t.toString());

            }
        });

        Call<String> uploadmanualproducts=service1.uploadmanualproducts(manualarray);
        uploadmanualproducts.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if(response.body().toString().contains("success")){
                    for(ManualProductsEntity m:manualProductsEntities){
                        m.setSyncStatus(true);
                        data.update(m).subscribeOn(AndroidSchedulers.mainThread())
                                .observeOn(Schedulers.io()).subscribe(new Consumer<ManualProductsEntity>() {
                            @Override
                            public void accept(@NonNull ManualProductsEntity manualProductsEntity) throws Exception {
                                Log.d("check update"," here updated "+manualProductsEntity.getSyncStatus());

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        if(transactionsyncstatus && invoicesyncstatus && productsyncstatus){
            Log.d("check status outside","completed successfully");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
               Toast.makeText(context,"Sync completed successfully",Toast.LENGTH_LONG).show();
                    Log.d("check update status","completed successfully");
                }
            });
        }
    }

}
