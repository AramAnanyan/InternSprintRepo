package com.example.internsprint;

import android.os.Bundle;
import android.provider.SyncStateContract;
//import android.telecom.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;

    private Button btnRegister;
    //private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);

        btnRegister = findViewById(R.id.btnRegister);
        //mQueue = Volley.newRequestQueue(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asdf as=new asdf();
                as.start();
                }

        });

    }
     class asdf extends Thread{
        @Override
        public void run() {
            String url = "https://localhost:7290/api/Users";
            //String url = "https://1d9b-37-252-94-167.eu.ngrok.io/api/Users";
            HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
            RequestBody requestBody = new FormBody.Builder()
                    .add("name", etFirstName.getText().toString())
                    .add("surname", etLastName.getText().toString())
                    .build();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .post(requestBody)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                String responseBody = response.body().string();
                // process the response body
            } catch (IOException e) {
                Log.e("abc", e.getMessage());
            }
            }
    }
}