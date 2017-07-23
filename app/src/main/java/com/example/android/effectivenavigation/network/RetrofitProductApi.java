package com.example.android.effectivenavigation.network;

import com.example.android.effectivenavigation.database.ProductsEntity;
import com.example.android.effectivenavigation.database.TransactionTableEntity;
import com.google.gson.JsonArray;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit2.http.Query;

/**
 * Created by lokeshmutyala on 17-07-2017.
 */

public interface RetrofitProductApi {

    @GET("getproducts.php")
    Call<JsonArray> getAllProducts(@retrofit.http.Query("lastsync") String lastsync);

    @POST("uploadtransactions.php")
    Call<String> uploadtransactions(@Body JSONArray entity);

    @POST("uploadinvoices.php")
    Call<String> uploadinvoices(@Body JSONArray jsonArray);

    @POST("uploadmanualproducts.php")
    Call<String> uploadmanualproducts(@Body JSONArray jsonArray);

    @POST("logincheck.php")
    Call<String> logincheck(@Body JSONObject entity);

    @POST("register.php")
    Call<String> register(@Body JSONObject object);

    @POST("send_email.php")
    Call<String> sendmail(@Body JSONObject object);
}
