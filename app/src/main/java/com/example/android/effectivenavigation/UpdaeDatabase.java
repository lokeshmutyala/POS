package com.example.android.effectivenavigation;

import android.util.Log;

import com.example.android.effectivenavigation.database.ProductsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

/**
 * Created by lokeshmutyala on 04-07-2017.
 */

public class UpdaeDatabase {
InputStream inputStream;
int i;
    private ReactiveEntityStore<Persistable> data;

    public UpdaeDatabase(InputStream inputStream, ReactiveEntityStore<Persistable> data) {
        this.inputStream = inputStream;
        this.data=data;
        i=0;
    }
    public void read(){
        i=0;
        //List<String> resultlist=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        try {
            //i++;

            String csvline;

            while((csvline=reader.readLine())!=null ){
                i++;
                Log.d("Reading and inserting:","Line number "+String.valueOf(i)+csvline);
                String[] row=csvline.split(",");
                //resultlist.add(row);
                ProductsEntity productEntity=new ProductsEntity();
                productEntity.setBarcode_No(row[0]);
                productEntity.setProduct_Name(row[1]);
                productEntity.setCompany_Name(row[2]);
                productEntity.setUOM(row[3]);
                productEntity.setMRP(Double.parseDouble(row[4].isEmpty()||row[4]==null?"0":row[4]));
                if(row.length>5) {
                    productEntity.setHSNno(row[5]);
                }else {
                    productEntity.setHSNno("");
                }
                if(row.length>6) {
                    productEntity.setTax(Integer.parseInt(row[6].isEmpty() || row[6] == null ? "0" : row[6]));
                }
                else{
                    productEntity.setTax(0);
                }
                data.insert(productEntity).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<ProductsEntity>() {
                    @Override
                    public void accept(@NonNull ProductsEntity productsEntity) throws Exception {
                        Log.d("Reading and inserting","inserted successfully:"+String.valueOf(i));
                    }
                });
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{

                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return ;
    }
}
