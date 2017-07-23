package com.example.android.effectivenavigation.database.webviews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.MailTo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.effectivenavigation.CollectionDemoActivity;
import com.example.android.effectivenavigation.ProductApplication;
import com.example.android.effectivenavigation.R;
import com.example.android.effectivenavigation.network.UpdateAllProducts;

import org.json.JSONException;
import org.json.JSONObject;

import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit.Retrofit;

public class webviews extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    public static final String MyPreferences="My prefs";
    private ReactiveEntityStore<Persistable> data;
    TextView bartitle;

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webviews);

        webView=(WebView)findViewById(R.id.webviews);
        data=((ProductApplication)getApplication()).getData();
        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        bartitle=(TextView)findViewById(R.id.bartitle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("check",url);
                if(url.startsWith("mailto:")){
                    MailTo mt = MailTo.parse(url);
                    JSONObject object=new JSONObject();
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
                    i.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
                    i.putExtra(Intent.EXTRA_CC, mt.getCc());
                    i.putExtra(Intent.EXTRA_TEXT, mt.getBody());
                    try {
                        object.put("body",mt.getBody());
                        object.put("subject",mt.getSubject());
                        UpdateAllProducts.sendMail(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //getApplicationContext().
                    Log.d("check",mt.getBody());
                    //startActivity(i);
                    view.reload();
                    return true;
                }
                return false;

            }
        });

        WebSettings webSettings=webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setHorizontalScrollBarEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheMaxSize(10*1024*1024);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
    //            progressBar.setProgress(newProgress);
                if(newProgress==100){
//                    progressBar.setVisibility(View.GONE);
                }else{
  //                  progressBar.setVisibility(View.VISIBLE);
                }

                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.cancel();
                return true;//super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                result.cancel();
                return true;// super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                result.cancel();
                return true;//super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        //webView.setWebViewClient(new WebViewClient());
        String folderPath = "file:///android_asset/";
        String page=getIntent().getStringExtra("page");
        if(page.contentEquals("reports")){
            //Toast.makeText(getApplication(),"entered",Toast.LENGTH_LONG).show();
            bartitle.setText("REPORT");
            webView.loadUrl("file:///android_asset/reports.html");
        }else if(page.contentEquals("support")){
            bartitle.setText("SUPPORT");
            webView.loadUrl("file:///android_asset/support.html");
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            //Intent intent=new Intent(getApplicationContext(), CollectionDemoActivity.class);
            //startActivity(intent);
            //super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id=item.getItemId();
        final Handler mHandler=new Handler(Looper.getMainLooper());
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);
        //final CoordinatorLayout layout=(CoordinatorLayout)findViewById(R.id.mainlayout);
        final DrawerLayout layout=(DrawerLayout)findViewById(R.id.drawer_layout);
        if(id==R.id.report){
//            Intent in=new Intent(getApplicationContext(),webviews.class);// Reports.class);
//            in.putExtra("page","reports");
            bartitle.setText("REPORT");
            webView.loadUrl("file:///android_asset/reports.html");
            //startActivity(in);
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
        else if(id==R.id.home){
            finish();
        }
        else if(id==R.id.update){
//            Intent in=new Intent(getApplicationContext(), UpdateAllProducts.class);
//            startActivity(in);
            layout.setVisibility(View.GONE);
            //progressBar.setVisibility(View.VISIBLE);
            UpdateAllProducts.getRetrofitArray(data,sharedPreferences,getBaseContext());
//            progressBar.setVisibility(View.GONE);
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
            finish();

        }else if(id==R.id.support){
            bartitle.setText("SUPPORT");
            webView.loadUrl("file:///android_asset/support.html");
//            Intent in=new Intent(getApplicationContext(),Reports.class);
//            in.putExtra("page","support");
//            startActivity(in);
//            //Toast.makeText(getApplication(),"you can contact to ********** no 24*7",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
