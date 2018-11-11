package com.example.kamran.bluewhite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class signin extends AppCompatActivity {

    private String ISIM_KEY="com.example.kamran.bluewhite.ISIM";
    private String EPOSTA_KEY="com.example.kamran.bluewhite.EPOSTA";
    private String PAROLA_KEY="com.example.kamran.bluewhite.PAROLA";
    private String MAIN_KEY="com.example.kamran.bluewhite.MAIN_DATA";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    EditText mail,password;
    TextView sin;
    ImageView sback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sback = (ImageView)findViewById(R.id.sinb);
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signin.this,main.class);
                startActivity(it);
            }
        });

        sin = (TextView)findViewById(R.id.sin);
        mail = (EditText) findViewById(R.id.usrusr);
        password = (EditText) findViewById(R.id.pswrd);

        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(signin.this);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mailText = mail.getText().toString();
                final String passwordText = password.getText().toString();

                if(mailText.equals("") || passwordText.equals(""))
                {
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
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if(success){
                                    String isim = jsonResponse.getString("isim");
                                    String eposta = jsonResponse.getString("eposta");
                                    String parola = jsonResponse.getString("parola");

                                    sharedPreferences=getSharedPreferences(MAIN_KEY, MODE_PRIVATE);
                                    editor=sharedPreferences.edit();
                                    editor.putString(ISIM_KEY, isim);
                                    editor.putString(EPOSTA_KEY, eposta);
                                    editor.putString(PAROLA_KEY, parola);
                                    editor.commit();
                                    Toast.makeText(signin.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();


                                    Intent intent = new Intent(signin.this, yapilacaklarListesiActivity.class);
                                    intent.putExtra("isim", isim);
                                    intent.putExtra("eposta", eposta);
                                    signin.this.startActivity(intent);
                                }
                                else
                                {
                                    AlertDialog.Builder message  = new AlertDialog.Builder(signin.this);

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

                    LoginRequest loginRequest = new LoginRequest(mailText,passwordText,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(signin.this);
                    queue.add(loginRequest);



                }
            }
        });
    }
}
