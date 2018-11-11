package com.example.kamran.bluewhite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class changePassword extends AppCompatActivity {

    private String ISIM_KEY="com.example.kamran.bluewhite.ISIM";
    private String PAROLA_KEY="com.example.kamran.bluewhite.PAROLA";
    private String EPOSTA_KEY="com.example.kamran.bluewhite.EPOSTA";
    private String MAIN_KEY="com.example.kamran.bluewhite.MAIN_DATA";

    EditText eskiParola, yeniParola,yeniParolaTekrar;
    TextView parolaGuncelle;

    String epostaVerisi,parolaVerisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parola_degistir_layout);

        eskiParola = (EditText) findViewById(R.id.eskiParola);
        yeniParola = (EditText) findViewById(R.id.yeniParola);
        yeniParolaTekrar = (EditText) findViewById(R.id.yeniParolaTekrar);
        parolaGuncelle = (TextView) findViewById(R.id.guncelle);

        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(changePassword.this);

        parolaGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parolaVerisi=getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(PAROLA_KEY,"eposta bulunamadı");

                if(yeniParola.getText().toString().equals(yeniParolaTekrar.getText().toString()))
                {
                    if ( eskiParola.getText().toString().equals(parolaVerisi.toString())) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        SharedPreferences sharedPreferences = getSharedPreferences(MAIN_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                        editor.putString(PAROLA_KEY, yeniParola.getText().toString());
                                        editor.commit();

                                        Toast.makeText(changePassword.this, "Parola Değiştirildi..", Toast.LENGTH_SHORT).show();


                                        Intent intent = new Intent(changePassword.this, yapilacaklarListesiActivity.class);
                                        changePassword.this.startActivity(intent);
                                    } else {
                                        AlertDialog.Builder message = new AlertDialog.Builder(changePassword.this);

                                        message.setMessage("İşlem Başarısız.!")
                                                .setNegativeButton("Tekrar Deneyin", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        epostaVerisi = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY, "eposta bulunamadı");

                        changePasswordRequest changePasswordRequest = new changePasswordRequest(epostaVerisi, yeniParola.getText().toString(), responseListener);
                        RequestQueue queue = Volley.newRequestQueue(changePassword.this);
                        queue.add(changePasswordRequest);
                    }
                    else
                    {
                        dlgAlert.setMessage("Eski parolanızı yanlış.!");
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
                else
                {
                    dlgAlert.setMessage("Yeni parola eşleşmiyor.!");
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
        });

    }
}
