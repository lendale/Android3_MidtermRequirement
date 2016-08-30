package com.acg.midtermrequirement;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = LoginActivity.this.getClass().getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager mCallbackManager;
    private LoginManager mLoginManager;

    private EditText etEmail;
    private EditText etPass;
    private TextView tvSignIn1;
    private TextView tvForgotPass;
    private TextView tvSignUp;
    private ImageView imgSignInFb;

    private LinearLayout linSignInFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);
        findViews();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
//                updateUI(user);
                // [END_EXCLUDE]
            }
        };

        mCallbackManager = CallbackManager.Factory.create();
        mLoginManager = LoginManager.getInstance();
        mLoginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void findViews() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_pass);
        tvSignIn1 = (TextView) findViewById(R.id.tv_sign_in_1);
        tvForgotPass = (TextView) findViewById(R.id.tv_forgot_pass);
        tvSignUp = (TextView) findViewById(R.id.tv_sign_up);
        imgSignInFb = (ImageView) findViewById(R.id.img_sign_in_fb_2);
        linSignInFb = (LinearLayout) findViewById(R.id.lin_sign_in_fb_1);

        tvSignUp.setOnClickListener(this);
        tvSignIn1.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);
        imgSignInFb.setOnClickListener(this);
        linSignInFb.setOnClickListener(this);
    }

    private void printHashkey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.acg.midtermrequirement",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(TAG, "Hash Key: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;

        if (id == R.id.tv_forgot_pass) {
            Snackbar.make(view, "Forgot password", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.tv_sign_in_1) {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_sign_up) {
            Snackbar.make(view, "Sign up", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.img_sign_in_fb_2) {
            mLoginManager.logInWithReadPermissions(LoginActivity.this,
                    Arrays.asList("email", "public_profile"));
        } else if (id == R.id.lin_sign_in_fb_1) {
            mLoginManager.logInWithReadPermissions(LoginActivity.this,
                    Arrays.asList("email", "public_profile"));
        }
    }
}