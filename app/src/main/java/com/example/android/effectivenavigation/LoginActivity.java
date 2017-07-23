package com.example.android.effectivenavigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.effectivenavigation.network.UpdateAllProducts;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Retrofit;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "lokeshmutyalachowdary@gmail.com","arnavneil@gmail.com","vamshimangu@gmail.com"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View mRegisterFormView;

    private EditText storename;
    private EditText ownername;
    private EditText mobile;
    private EditText email;
    private EditText age;
    private EditText gstno;
    private EditText owneraadar;
    private EditText storepan;
    private EditText locality;
    private EditText address;
    private EditText pincode;
    private EditText password;
    private RadioGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Handler mHandler=new Handler(Looper.getMainLooper());
        final Handler h =new Handler(Looper.getMainLooper());
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        storename=(EditText) findViewById(R.id.store_name);
        ownername=(EditText) findViewById(R.id.owner_name);
        mobile=(EditText) findViewById(R.id.owner_mobile);
        email=(EditText) findViewById(R.id.owner_email);
        age=(EditText) findViewById(R.id.owner_age);
        gstno=(EditText) findViewById(R.id.gstno);
        owneraadar=(EditText) findViewById(R.id.owner_adar);
        storepan=(EditText) findViewById(R.id.store_pan);
        locality=(EditText) findViewById(R.id.store_locality);
        address=(EditText) findViewById(R.id.store_address);
        pincode=(EditText) findViewById(R.id.pincode);
        password=(EditText) findViewById(R.id.owner_password);
        gender=(RadioGroup)findViewById(R.id.gender);


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button register=(Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoginFormView.setVisibility(View.GONE);
                mRegisterFormView.setVisibility(View.VISIBLE);
            }
        });
        Button registerstore=(Button) findViewById(R.id.registerstore);
        registerstore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("check registration","entering");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("check registration","entered");
                        if(mobile.getText().toString().isEmpty() || mobile.getText().toString().length()!=10 || Integer.parseInt(mobile.getText().toString().substring(0,1))<7){
                            Toast.makeText(getApplicationContext(),"please fill mobile feild",Toast.LENGTH_LONG).show();
                        }
                        else if(storename.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"please enter a store name",Toast.LENGTH_LONG).show();
                        }
                        else if(ownername.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"please enter owner name",Toast.LENGTH_LONG).show();
                        }
                        else if(email.getText().toString().isEmpty() || !email.getText().toString().contains("@")){
                            Toast.makeText(getApplicationContext(),"please enter a valid email",Toast.LENGTH_LONG).show();
                        }
                        else if(gstno.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"please enter GST number",Toast.LENGTH_LONG).show();
                        }
                        else if(password.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"please enter a password",Toast.LENGTH_LONG).show();
                        }else{
                            JSONObject jsonObject=new JSONObject();
                            try {
                                jsonObject.put("store_name",storename.getText().toString());
                                jsonObject.put("owner_name",ownername.getText().toString());
                                jsonObject.put("mobile",mobile.getText().toString());
                                jsonObject.put("email",email.getText().toString());
                                jsonObject.put("age",age.getText().toString());
                                jsonObject.put("gstno",gstno.getText().toString());
                                jsonObject.put("aadhar",owneraadar.getText().toString());
                                jsonObject.put("store_pan",storepan.getText().toString());
                                jsonObject.put("locality",locality.getText().toString());
                                jsonObject.put("address",address.getText().toString());
                                jsonObject.put("pincode",pincode.getText().toString());
                                jsonObject.put("pwd",password.getText().toString());

                                boolean ch=UpdateAllProducts.register(jsonObject);
                                mRegisterFormView.setVisibility(View.GONE);
                                mProgressView.setVisibility(View.VISIBLE);

                                Log.d("checked ","result="+ch+","+UpdateAllProducts.check);
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        while(!UpdateAllProducts.check){
                                            Log.d("in check","while looping");
                                        }
                                        //mRegisterFormView.setVisibility(View.GONE);
                                        mProgressView.setVisibility(View.GONE);
                                        //mRegisterFormView.setVisibility(View.GONE);
                                        mLoginFormView.setVisibility(View.VISIBLE);
                                        if(UpdateAllProducts.check){
                                            Log.d("tsk check","completed");
                                        }
                                        if(UpdateAllProducts.mAuth){
                                            SharedPreferences sharedPreferences=getSharedPreferences("My prefs", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor=sharedPreferences.edit();
                                            editor.putString("StoreId",UpdateAllProducts.storeid);
                                            editor.commit();
                                            Toast.makeText(getApplicationContext(),"regestered successfully",Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"regestration unsuccessful",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                },5000);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mRegisterFormView=findViewById(R.id.registerform);
        mRegisterFormView.setVisibility(View.GONE);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //if(Arrays.asList(DUMMY_CREDENTIALS).contains(email))
        return true;
        //return false;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return true;// password.contentEquals("adjoint");
        //return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
          boolean check=  UpdateAllProducts.logincheck(mEmail,mPassword);
            try {
                // Simulate network access.
                Thread.sleep(5000);
                while(!UpdateAllProducts.check){
                    Log.d("check loop","in loop");
                }
            } catch (InterruptedException e) {
                return false;
            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }

            // TODO: register the new account here.

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if (UpdateAllProducts.mAuth) {
                SharedPreferences sharedPreferences=getSharedPreferences("My prefs",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("UID",mEmail);
                editor.putString("PWD",mPassword);
                //SharedPreferences sharedPreferences=getSharedPreferences("My prefs", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("StoreId",UpdateAllProducts.storeid);
                editor.commit();
                editor.putLong("lastlogintime",System.currentTimeMillis());
                editor.commit();
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

