package com.angelsanchez.bookseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.angelsanchez.bookseeker.models.ApiToken;
import com.angelsanchez.bookseeker.services.APIService;
import com.angelsanchez.bookseeker.utils.ApiUtils;
import com.angelsanchez.bookseeker.utils.DataProcessor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;

    private String userEmail;
    private String userPassword;

    private APIService apiService;
    private DataProcessor dataProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = ApiUtils.getAPIService();
        dataProcessor = new DataProcessor(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void btnLoginPressed(View view) {
        userEmail = etEmail.getText().toString();
        userPassword = etPassword.getText().toString();

        doLoginRequest(userEmail,userPassword);
    }

    public void btnSingUpPressed(View view) {
    }

    private void doLoginRequest(String userEmail, String userPassword) {
        Log.e("doLogin","email: "+ userEmail);
        apiService.doLogin(userEmail,userPassword).enqueue(new Callback<ApiToken>() {
            @Override
            public void onResponse(Call<ApiToken> call, Response<ApiToken> response) {
                //Log.e("doLogin","HTTP Code: " + String.valueOf(response.code()));
                //Log.e("doLogin","MESSAGE: " + response.message().toString());
                //Log.e("doLogin","TOKEN Auth: "+ response.body().getToken());
                if(response.code() == 200){
                    ApiToken token = response.body();
                    initializeUserSession(token);
                }
            }

            @Override
            public void onFailure(Call<ApiToken> call, Throwable t) {

            }
        });
    }

    private void initializeUserSession(ApiToken token) {
        dataProcessor.saveBooleanValueForKey("isActiveSession",true);
        dataProcessor.saveStringValueForKey("userToken",token.getToken());
        goToMainActivity();
    }

    private void goToMainActivity(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
