package com.example.internsprint;

import android.os.Bundle;
import android.provider.SyncStateContract;
//import android.telecom.Call;
import retrofit2.Call;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;



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
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://7244-37-252-94-167.eu.ngrok.io")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RegistrationApi api = retrofit.create(RegistrationApi.class);

                Call<ResponseBody> call = api.registerUser(new RegisterModel(etFirstName.getText().toString(), etLastName.getText().toString()));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.i("asd",response.toString()+" , ");
                            Toast t=Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            Log.i("asd",response.toString()+" , ");
                            Toast t=Toast.makeText(getApplicationContext(), "noresp", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast k=Toast.makeText(getApplicationContext(), "fail:", Toast.LENGTH_SHORT);
                        Log.i("asd",call.toString()+" , "+t.toString());
                        k.show();
                        // Handle failure
                    }
                });
            }
        });


    }
}


