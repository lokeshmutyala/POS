package com.example.android.effectivenavigation;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.effectivenavigation.database.TransactionTableEntity;

import java.util.ArrayList;

import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.util.CloseableIterator;

public class Invoice_Viewr extends AppCompatActivity implements SearchView.OnQueryTextListener{
 ListView listView;
    private ReactiveEntityStore<Persistable> data;
    android.support.v7.widget.SearchView searchView;
    ArrayAdapter<String> adapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice__viewr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data=((ProductApplication)getApplication()).getData();
        listView=(ListView)findViewById(R.id.listtransactions);
        final ArrayList<String> tids=new ArrayList<>();
        CloseableIterator<TransactionTableEntity> iterator = data.select(TransactionTableEntity.class).orderBy(TransactionTableEntity.ID.desc()).get().iterator();
        while(iterator.hasNext()){
            tids.add(iterator.next().getTransactionId());
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,tids);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>0 && position< tids.size()){
                    Intent intent=new Intent(getApplicationContext(),InvoiceDescription.class);
                    intent.putExtra("INV",adapter.getItem(position));
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        searchView = (android.support.v7.widget.SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        if(TextUtils.isEmpty(newText)){
            listView.clearTextFilter();
        }else {
            listView.setFilterText(newText);
        }
        return true;

    }
}
