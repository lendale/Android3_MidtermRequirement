package com.acg.midtermrequirement;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
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
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
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

    private EditText mEtEmail;
    private EditText mEtPass;
    private TextView mTvSignIn1;
    private TextView mTvForgotPass;
    private TextView mTvSignUp;
    private ImageView mImgSignInFb;

    private LinearLayout mLinSignInFb;

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
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    // User is signed in
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("USER_NAME", name);
                    intent.putExtra("USER_EMAIL", email);
                    intent.putExtra("USER_PHOTO_URL", photoUrl);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
//                updateUI(user);
            }
        };

        mCallbackManager = CallbackManager.Factory.create();
        mLoginManager = ReLoginManager.getInstance();
        mLoginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void findViews() {
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mEtPass = (EditText) findViewById(R.id.et_pass);
        mTvSignIn1 = (TextView) findViewById(R.id.tv_sign_in_1);
        mTvForgotPass = (TextView) findViewById(R.id.tv_forgot_pass);
        mTvSignUp = (TextView) findViewById(R.id.tv_sign_up);
        mImgSignInFb = (ImageView) findViewById(R.id.img_sign_in_fb_2);
        mLinSignInFb = (LinearLayout) findViewById(R.id.lin_sign_in_fb_1);

        mTvSignUp.setOnClickListener(this);
        mTvSignIn1.setOnClickListener(this);
        mTvForgotPass.setOnClickListener(this);
        mImgSignInFb.setOnClickListener(this);
        mLinSignInFb.setOnClickListener(this);
    }

    private void printHashkey() {

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