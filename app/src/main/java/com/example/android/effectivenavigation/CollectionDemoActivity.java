/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.effectivenavigation;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.effectivenavigation.database.InvoiceEntity;
import com.example.android.effectivenavigation.database.TransactionTableEntity;
import com.example.android.effectivenavigation.database.webviews.Reports;
import com.example.android.effectivenavigation.database.webviews.webviews;
import com.example.android.effectivenavigation.network.UpdateAllProducts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import io.requery.Persistable;
import io.requery.query.Where;
import io.requery.reactivex.ReactiveEntityStore;

public class CollectionDemoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

    //static int count=0;
    static List<TransactionTableEntity> tlist;
    static ArrayList<List<InvoiceEntity>> ilist=new ArrayList<List<InvoiceEntity>>();
    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;
    SharedPreferences sharedPreferences;
    public static final String MyPreferences="My prefs";
    private ReactiveEntityStore<Persistable> data;
    Button StartBilling;
    TextView TodaySale;
    TextView Bills;
    private boolean mAuth=false;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        java.text.SimpleDateFormat dateFormat=new java.text.SimpleDateFormat("yyyyMMdd");//(''yy, MMM d, EEE");
        final String date=dateFormat.format(new Date());
        data=((ProductApplication)getApplication()).getData();
        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        if(sharedPreferences.contains("UID")){
            if(sharedPreferences.getString("UID","")=="" || sharedPreferences.getString("PWD","")=="" || sharedPreferences.getLong("lastlogintime",00)-System.currentTimeMillis()>2.592e+8){
                mAuth=false;
            }
            else {
                mAuth=true;
            }
        }
        if(!mAuth){

            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        if(!sharedPreferences.contains("First")) {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("First","opened");
            editor.putString("StoreId","");
            editor.commit();
            InputStream inputStream = getResources().openRawResource(R.raw.products);
            UpdaeDatabase csv = new UpdaeDatabase(inputStream, data);
            csv.read();
////        List<String> productlist=csv.read();
            Log.d("Updating: ", "completed");
        }
        setContentView(R.layout.activity_collection_demo);
        TodaySale=(TextView)findViewById(R.id.today);
        Bills=(TextView)findViewById(R.id.bills);
        if(sharedPreferences.contains("TodaySale")){
            Log.d("check today","inside TodaySale");
            if(sharedPreferences.contains("today")){
                Log.d("check today","inside today");
                if(! sharedPreferences.getString("today","").contentEquals(date)){
                    Log.d("check today","inside today !=date where date="+date+"&"+sharedPreferences.getString("today",""));
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("today",date);
                    editor.putFloat("TodaySale",0);
                    editor.putInt("Bills",0);
                    editor.commit();
                }
//                else{
//                    TodaySale.setText(String.valueOf(sharedPreferences.getFloat("TodaySale",0)));
//                    Bills.setText(sharedPreferences.getInt("Bills",0));
//                }

            }else{
                Log.d("check today","inside else today");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("today",date);
                editor.putFloat("TodaySale",0);
                editor.putInt("Bills",0);
                editor.commit();
            }

        }else {
            Log.d("check today","inside else TodaySale");
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("today",date);
            editor.putFloat("TodaySale",00);
            editor.putInt("Bills",00);
            editor.commit();
        }
        String tmp=String.valueOf(sharedPreferences.getFloat("TodaySale",0));
        tmp=tmp.substring(0,tmp.indexOf('.'));
        Log.d("check tmp",tmp);
        TodaySale.setText("₹    "+tmp);
        Bills.setText(String.valueOf(sharedPreferences.getInt("Bills",0)));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //ActionBar actionBardummy=getActionBar();
        //actionBardummy.hide();
        StartBilling=(Button)findViewById(R.id.start);
        StartBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Billing.class));
            }
        });
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        // 
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

        // Set up action bar.
        final ActionBar actionBar =   getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        //actionBar.setDisplayHomeAsUpEnabled(false);

        //actionBar.hide();
        // Set up the ViewPager, attaching the adapter.

        //count=data.count(TransactionTableEntity.class).get().value();
        tlist=data.select(TransactionTableEntity.class).orderBy(TransactionTableEntity.ID.desc()).get().toList();
        for(int i=0;i<5 && i<tlist.size();i++){
            ilist.add(data.select(InvoiceEntity.class).where(InvoiceEntity.TRANSACTION_ID.eq(tlist.get(i).getTransactionId())).get().toList());
        }
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setClipToPadding(false);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
    public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void notifyDataSetChanged() {

            for(int i=0;i<getCount();i++){

            }
            super.notifyDataSetChanged();
        }


        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            if(tlist.size()>5)
            return 6;
            return tlist.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;// super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Customer " + (position + 1);
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DemoObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final Bundle args = getArguments();

            final View rootView;
            if(args.getInt(ARG_OBJECT)==6){
                rootView=inflater.inflate(R.layout.view_more,container,false);
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(getActivity(),Invoice_Viewr.class);
                        startActivity(i);
                    }
                });

                return rootView;
            }
            rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);

            ((TextView) rootView.findViewById(R.id.tid)).setText(tlist.get(args.getInt(ARG_OBJECT)-1).getTransactionId());    //"may be here");
                    //"#INV"+Integer.toString(args.getInt(ARG_OBJECT)));
            ((TextView)rootView.findViewById(R.id.mobileno)).setText(tlist.get(args.getInt(ARG_OBJECT)-1).getCustomerMobile());
            String temp=tlist.get(args.getInt(ARG_OBJECT)-1).getTaxCollected();
            temp=temp.substring(0,temp.indexOf('.')+2);
            ((TextView)rootView.findViewById(R.id.taxes)).setText(temp);
            ((TextView)rootView.findViewById(R.id.total)).setText(tlist.get(args.getInt(ARG_OBJECT)-1).getTransactionValue());
               Log.d("Ilist Size:",String.valueOf(ilist.get(args.getInt(ARG_OBJECT)-1).size()));
                if(ilist.get(args.getInt(ARG_OBJECT)-1).size()>0){
                    ListView mlListView=(ListView)rootView.findViewById(R.id.invlist);
                    ArrayList<item> items=new ArrayList<>();
                    for(int i=0;i<ilist.get(args.getInt(ARG_OBJECT)-1).size();i++){
                        InvoiceEntity invoiceEntity=ilist.get(args.getInt(ARG_OBJECT)-1).get(i);
                        items.add(new item(invoiceEntity.getName(),Double.parseDouble(invoiceEntity.getPrice()),invoiceEntity.getQuantity(),1,false,invoiceEntity.getBarcode(),Double.parseDouble(invoiceEntity.getDiscount()),0));
                    }
                    ItemListAdapterInvoice listAdapterInvoice=new ItemListAdapterInvoice(getActivity(),R.layout.single_invoice,items);
                    mlListView.setAdapter(listAdapterInvoice);
                    mlListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            rootView.performClick();
                        }
                    });

                }

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),InvoiceDescription.class);
                    intent.putExtra("INV",((TextView)rootView.findViewById(R.id.tid)).getText().toString());
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        final Handler mHandler=new Handler(Looper.getMainLooper());
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);
        //final CoordinatorLayout layout=(CoordinatorLayout)findViewById(R.id.mainlayout);
        final DrawerLayout layout=(DrawerLayout)findViewById(R.id.drawer_layout);
        if(id==R.id.report){
           final Intent in=new Intent(getApplicationContext(),webviews.class);// Reports.class);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    in.putExtra("page","reports");
                    startActivity(in);
                }
            });
//            layout.setVisibility(View.GONE);
//            progressBar.setVisibility(View.VISIBLE);
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    progressBar.setVisibility(View.GONE);
//                    layout.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplication(),"request recieved and will contact you soon",Toast.LENGTH_LONG).show();
//                }
//            },3000);
        }
        else if(id==R.id.update){
//            Intent in=new Intent(getApplicationContext(), UpdateAllProducts.class);
//            startActivity(in);
            layout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            UpdateAllProducts.getRetrofitArray(data,sharedPreferences,getBaseContext());
            progressBar.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
        else if(id==R.id.file){
            layout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplication(),"Tax filing is completed, will contact you shortly",Toast.LENGTH_LONG).show();
                }
            },3000);

        }else if(id==R.id.support){
            Intent in=new Intent(getApplicationContext(),webviews.class);
            in.putExtra("page","support");
            startActivity(in);
            //Toast.makeText(getApplication(),"you can contact to ********** no 24*7",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostResume() {

        super.onPostResume();

        ilist.clear();
        tlist=data.select(TransactionTableEntity.class).orderBy(TransactionTableEntity.ID.desc()).get().toList();
        for(int i=0;i<5 && i<tlist.size();i++){
            ilist.add(data.select(InvoiceEntity.class).where(InvoiceEntity.TRANSACTION_ID.eq(tlist.get(i).getTransactionId())).get().toList());
        }
        mDemoCollectionPagerAdapter.notifyDataSetChanged();
        mViewPager.destroyDrawingCache();
        Log.d("Check Pager","datacleared");
        if(sharedPreferences.contains("TodaySale")){
            String tmp=String.valueOf(sharedPreferences.getFloat("TodaySale",0));
            tmp=tmp.substring(0,tmp.indexOf('.'));
            Log.d("check tmp",tmp);
            TodaySale.setText("₹ "+tmp);
            Bills.setText(String.valueOf(sharedPreferences.getInt("Bills",0)));
        }
    }
}
