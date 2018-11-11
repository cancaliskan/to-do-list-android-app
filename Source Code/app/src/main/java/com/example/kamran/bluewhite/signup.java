package com.example.kamran.bluewhite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity
{
    EditText fname,mail,password,password2;
    TextView sin;
    ImageView sback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sback = (ImageView)findViewById(R.id.sback);
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    Intent it = new Intent(signup.this, main.class);
                    startActivity(it);
            }
        });
        sin = (TextView)findViewById(R.id.sin);
        mail = (EditText) findViewById(R.id.mail);
        fname = (EditText) findViewById(R.id.fname);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String isim = fname.getText().toString();
                final String eposta = mail.getText().toString();
                final String parola = password.getText().toString();

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(signup.this);

                if (fname.getText().toString().equals("")
                        || mail.getText().toString().equals("")
                        || password.getText().toString().equals("")
                        || password2.getText().toString().equals("")) {


                    dlgAlert.setMessage("Lütfen tüm alanları doldurunuz.!");
                    dlgAlert.setTitle("Uyarı");
                    dlgAlert.setPositiveButton("Tamam", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Tamam",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                }
                else
                {
                    if(password.getText().toString().equals(password2.getText().toString()))
                    {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success){
                                        Intent intent = new Intent(signup.this, signin.class);
                                        signup.this.startActivity(intent);
                                    }
                                    else
                                    {
                                        AlertDialog.Builder message  = new AlertDialog.Builder(signup.this);

                                        message.setMessage("İşlem Başarısız.!")
                                                .setNegativeButton("Tekrar Deneyin",null)
                                                .create()
                                                .show();
                                    }
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        RegisterRequest registerRequest = new RegisterRequest(isim, eposta, parola,responseListener);

                        RequestQueue queue = Volley.newRequestQueue(signup.this);
                        queue.add(registerRequest);
                    }
                    else
                    {
                        dlgAlert.setMessage("Parola eşleşmiyor.!");
                        dlgAlert.setTitle("Uyarı");
                        dlgAlert.setPositiveButton("Tamam", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Tamam",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                    }
                }
            }
        });
    }
}
