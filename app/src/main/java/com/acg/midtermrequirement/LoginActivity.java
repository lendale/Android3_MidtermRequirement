package com.acg.midtermrequirement;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.this.getClass().getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager mCallbackManager;

    private EditText etEmail;
    private EditText etPass;
    private TextView tvSignIn1;
    private ImageView imgSignInFb;

    private LinearLayout linSignInFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
    }

    private void findViews() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_pass);
        tvSignIn1 = (TextView) findViewById(R.id.tv_sign_in_1);
        imgSignInFb = (ImageView) findViewById(R.id.img_sign_in_fb_2);
        linSignInFb = (LinearLayout) findViewById(R.id.lin_sign_in_fb_1);
    }

    private void initOnclickListeners() {
        tvSignIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgSignInFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        linSignInFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
}