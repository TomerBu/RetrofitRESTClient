package org.college.android.itomer.retrofitrestclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import entities.GoogleAccessToken;
import rest.GoogleRestClient;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_response);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Handle the response from the API OAUTH2 Response
        //(from the web site to localhost)

        Intent intent;
        Uri uri = null;
        String error;
        String code;

        if ((intent = getIntent()) != null
                && (uri = intent.getData()) != null
                && uri.toString().startsWith("http://localhost")
                && (code = uri.getQueryParameter("code")) != null) {
            //use the parameter for the code from your API. usually it's named code
            Log.e("Tomer", "Code: " + code);
            getAccessToken(code);
        } else if (uri != null) {
            error = uri.getQueryParameter("error");
            Log.e("Tomer", "Error: " + error);
        }
    }

    private void getAccessToken(final String code) {
        String shoo = "https://accounts.google.com/o/oauth2/token?client_id=" +
                MainActivity.CLIENT_ID + "&client_secret=" +
                MainActivity.CLIENT_SECRET + "&redirect_uri=" + MainActivity.REDIRECT_URI +
                "&code=" + code + "&grant_type=" + MainActivity.GRANT_TYPE;
        Log.e("Tomer", shoo);
        GoogleRestClient.createService(
                GoogleRestClient.GoogleRESTAPI.class)
                .getAccessToken(MainActivity.CLIENT_ID,
                        MainActivity.CLIENT_SECRET,
                        MainActivity.REDIRECT_URI,
                        code,
                        MainActivity.GRANT_TYPE).
                enqueue(new Callback<GoogleAccessToken>() {
                    @Override
                    public void onResponse(Response<GoogleAccessToken> response, Retrofit retrofit) {
                        GoogleAccessToken token = response.body();
                        Log.e("Tomer", token != null ? token.toString() : "No Token!");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                });

    }
}

