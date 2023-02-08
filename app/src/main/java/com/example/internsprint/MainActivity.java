package com.example.internsprint;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword;
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
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString().trim();
                String surName = etLastName.getText().toString().trim();


                if (firstName.isEmpty() || surName.isEmpty() ) {
                    Toast.makeText(MainActivity.this, "Please fill out all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(firstName, surName);
                }
            }
        });
    }

    private void registerUser(final String name, final String surName) {
        // Showing progress dialog at user registration time.
        /*progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
        progressDialog.show();*/

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://localhost:7290/api/Users",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                // Adding All values to Params.
                params.put("name", name);
                params.put("surName", surName);


                return params;
            }

        };

        /*// Adding the StringRequest to the queue.
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);*/
    }
}


