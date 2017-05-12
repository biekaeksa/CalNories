package com.spiderman.calnories.signInGoogle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.spiderman.calnories.R;
import com.spiderman.calnories.calories.CaloriesActivity;
import com.spiderman.calnories.data.UserModel;
import com.spiderman.calnories.main.MainActivity;
import com.spiderman.calnories.util.SessionUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener ,
                                                                LoginContract.View{
    @BindView(R.id.sign_in)
    SignInButton signInButton;
    private GoogleSignInOptions signInOptions;
    private GoogleApiClient googleApiClient;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9006;
    String photoUrl;
    private LoginPresenter presenter;
    public ProgressDialog progressDialog;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(SessionUtils.isLoggedIn(this)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        initPresenter();
        loginInit();

        initDialog();
        onAttachView();
        signInButton.setOnClickListener(this);


    }

    private void initDialog() {
        progressDialog = new ProgressDialog(LoginActivity.this);
    }

    private void initPresenter() {
        presenter = new LoginPresenter();
    }


    private void loginInit() {
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
        signInButton.setSize(SignInButton.SIZE_WIDE);

    }

    private void signIn(){
        Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(i, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleSignInResult(result);

            }
    }

    private void handleSignInResult(GoogleSignInResult result){
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            if (account.getPhotoUrl() == null){
                photoUrl = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQqCobKqBNr2-yL2pyUsjkH1yEQ-RNPnzd0iNqsXMzCfKn2u3tJ";
            }else {
                photoUrl = account.getPhotoUrl().toString();
            }

            idUser = account.getId();
            presenter.signInUp(idUser, account.getDisplayName(), account.getEmail(), photoUrl);
        }
    }

    @OnClick
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.sign_in:
                signIn();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


   @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Login...", true, false);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }


    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showMainView(List<UserModel> userDataModel) {
        if (userDataModel.get(0).getCalories_target() == 0){
            Intent intent = new Intent(LoginActivity.this, CaloriesActivity.class);
            intent.putExtra("id", idUser);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }




}
