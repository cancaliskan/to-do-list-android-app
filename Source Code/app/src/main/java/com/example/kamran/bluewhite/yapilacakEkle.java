package com.example.kamran.bluewhite;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;

public class yapilacakEkle extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private String ISIM_KEY = "com.example.kamran.bluewhite.ISIM";
    private String EPOSTA_KEY = "com.example.kamran.bluewhite.EPOSTA";
    private String MAIN_KEY = "com.example.kamran.bluewhite.MAIN_DATA";
    private String PAROLA_KEY = "com.example.kamran.bluewhite.PAROLA";

    EditText baslik, metin;
    TextView tarihSec, saatSec, yapilacakEkle;
    Switch hatirlat;

    String epostaVerisi;
    public String ıntent_ID,ıntent_eposta,ıntent_baslik,ıntent_metin,ıntent_hatirlat,ıntent_tarih,ıntent_saat;


    public int yil,ay,gun,saat,dakika;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacak_ekle);

        tarihSec = (TextView) findViewById(R.id.tarihSecButon);
        saatSec = (TextView) findViewById(R.id.saatSecButon);
        hatirlat = (Switch) findViewById(R.id.yapilacakHatirlat);
        yapilacakEkle = (TextView) findViewById(R.id.yapilacakEkleButon);

        baslik = (EditText) findViewById(R.id.yapilacakBaslik);
        metin = (EditText) findViewById(R.id.yapilacakAciklama);

        Intent ıntent = this.getIntent();
        Bundle extras = ıntent.getExtras();
        if(extras != null) {
            ıntent_ID = ıntent.getExtras().getString("ID");
            ıntent_eposta = ıntent.getExtras().getString("eposta");
            ıntent_baslik = ıntent.getExtras().getString("baslik");
            ıntent_metin = ıntent.getExtras().getString("metin");
            ıntent_hatirlat = ıntent.getExtras().getString("hatirlat");
            ıntent_tarih = ıntent.getExtras().getString("tarih");
            ıntent_saat = ıntent.getExtras().getString("saat");
        }
        if (ıntent_ID != null) {
            baslik.setText(ıntent_baslik);
            metin.setText(ıntent_metin);
            if (ıntent_hatirlat.equals("Evet")) {
                hatirlat.setChecked(true);
            } else {
                hatirlat.setChecked(false);
            }
            if (!ıntent_tarih.equals(""))
                tarihSec.setText(ıntent_tarih);
            else
                tarihSec.setText("Tarih Seç");
            if (!ıntent_saat.equals(""))
                saatSec.setText(ıntent_saat);
            else
                saatSec.setText("Saat Seç");
        }

        if (!hatirlat.isChecked()) {
            tarihSec.setVisibility(View.GONE);
            saatSec.setVisibility(View.GONE);
        } else {
            tarihSec.setVisibility(View.VISIBLE);
            saatSec.setVisibility(View.VISIBLE);
        }

        hatirlat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!hatirlat.isChecked()) {
                    tarihSec.setVisibility(View.GONE);
                    saatSec.setVisibility(View.GONE);
                } else {
                    tarihSec.setVisibility(View.VISIBLE);
                    saatSec.setVisibility(View.VISIBLE);
                }
            }
        });

        tarihSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        saatSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        yapilacakEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ıntent_ID != null) {
                    /// güncelleme işlemi başlangıcı ////
                    if (!baslik.getText().toString().equals("")) {
                        if (hatirlat.isChecked()
                                && (!tarihSec.getText().toString().equals("Tarih Seç")
                                && !saatSec.getText().toString().equals("Saat Seç"))) {
                            if (Calendar.getInstance().get(Calendar.YEAR) <= yil)
                            {
                                if(Calendar.getInstance().get(Calendar.YEAR) < yil)
                                {
                                    kayitGuncelleHatirlatmali();
                                }
                                else {
                                    if ((Calendar.getInstance().get(Calendar.MONTH)) <= ay) {
                                        if((Calendar.getInstance().get(Calendar.MONTH)) < ay)
                                        {
                                            kayitGuncelleHatirlatmali();
                                        }
                                        else {
                                            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) <= gun) {
                                                if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < gun)
                                                {
                                                    kayitGuncelleHatirlatmali();
                                                }
                                                else {
                                                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) <= saat) {
                                                        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < saat)
                                                        {
                                                            kayitGuncelleHatirlatmali();
                                                        }
                                                        else {
                                                            if (Calendar.getInstance().get(Calendar.MINUTE) < dakika) {
                                                                kayitGuncelleHatirlatmali();
                                                            }
                                                            else
                                                                alertDialog("Şimdiki zamandan ileri bir tarih seçmelisiniz.!");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                alertDialog("Şimdiki zamandan ileri bir tarih seçmelisiniz.!");

                            }
                        } else if (hatirlat.isChecked()
                                && (tarihSec.getText().toString().equals("Tarih Seç")
                                || saatSec.getText().toString().equals("Saat Seç"))) {
                            alertDialog("Hatırlatma istiyorsanız tarih ve saat seçmelisiniz.!");

                        } else if (!hatirlat.isChecked()) {
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");

                                        if (success) {
                                            Toast.makeText(yapilacakEkle.this, "İşlem Başarılı", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(yapilacakEkle.this, yapilacaklarListesiActivity.class);
                                            yapilacakEkle.this.startActivity(intent);
                                        } else {
                                            alertDialog("İşlem Başarısız.!");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            yapilacakUpdateRequest yapilacakUpdateRequest = new yapilacakUpdateRequest(ıntent_ID, baslik.getText().toString(), metin.getText().toString(),
                                    "Hayır", "", "", responseListener);
                            RequestQueue queue = Volley.newRequestQueue(yapilacakEkle.this);
                            queue.add(yapilacakUpdateRequest);
                        }
                    } else {
                        alertDialog("Başlık boş geçilemez.!");
                    }
                }
                /// güncelleme işlemi bitti ////
                else {
                    /// ekleme işlemi başladı ////
                    if (!baslik.getText().toString().equals("")) {
                        if (hatirlat.isChecked()
                                && (!tarihSec.getText().toString().equals("Tarih Seç")
                                && !saatSec.getText().toString().equals("Saat Seç"))) {
                            if (Calendar.getInstance().get(Calendar.YEAR) <= yil)
                            {
                                if(Calendar.getInstance().get(Calendar.YEAR) < yil)
                                {
                                    kayitEklemeHatirlatmali();
                                }
                                else {
                                    if ((Calendar.getInstance().get(Calendar.MONTH)) <= ay) {
                                        if((Calendar.getInstance().get(Calendar.MONTH)) < ay)
                                        {
                                            kayitEklemeHatirlatmali();
                                        }
                                        else {
                                            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) <= gun) {
                                                if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < gun)
                                                {
                                                    kayitEklemeHatirlatmali();
                                                }
                                                else {
                                                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) <= saat) {
                                                        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < saat)
                                                        {
                                                            kayitEklemeHatirlatmali();
                                                        }
                                                        else {
                                                            if (Calendar.getInstance().get(Calendar.MINUTE) < dakika) {
                                                                kayitEklemeHatirlatmali();
                                                            }
                                                            else
                                                                alertDialog("Şimdiki zamandan ileri bir tarih seçmelisiniz.!");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                alertDialog("Şimdiki zamandan ileri bir tarih seçmelisiniz.!");

                            }
                        } else if (hatirlat.isChecked()
                                && (tarihSec.getText().toString().equals("Tarih Seç")
                                || saatSec.getText().toString().equals("Saat Seç"))) {
                            alertDialog("Hatırlatma istiyorsanız tarih ve saat seçmelisiniz.!");

                        } else if (!hatirlat.isChecked()) {
                            kayitEklemeHatirlatmasiz();
                        }
                    } else {
                        alertDialog("Başlık boş geçilemez.!");
                    }
                }
                /// ekleme işlemi bitti ////
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        yil=year;
        gun=dayOfMonth;
        ay=monthOfYear;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        tarihSec.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);//ay sıfırdan başladığı için +1
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        saat=hourOfDay;
        dakika=minute;

        saatSec.setText(hourOfDay + ":" + minute);
    }

    public void alertDialog(String mesaj)
    {
        AlertDialog.Builder message = new AlertDialog.Builder(yapilacakEkle.this);

        message.setMessage(mesaj)
                .setNegativeButton("Tekrar Deneyin", null)
                .create()
                .show();
    }

    public void kayitGuncelleHatirlatmali()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Toast.makeText(yapilacakEkle.this, "İşlem Başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(yapilacakEkle.this, yapilacaklarListesiActivity.class);
                        yapilacakEkle.this.startActivity(intent);
                    } else {
                        alertDialog("İşlem Başarısız.!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        yapilacakUpdateRequest yapilacakUpdateRequest = new yapilacakUpdateRequest(ıntent_ID, baslik.getText().toString(), metin.getText().toString(),
                "Evet", tarihSec.getText().toString(), saatSec.getText().toString(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(yapilacakEkle.this);
        queue.add(yapilacakUpdateRequest);
    }

    public void kayitEklemeHatirlatmali()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Toast.makeText(yapilacakEkle.this, "İşlem Başarılı", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(yapilacakEkle.this, yapilacaklarListesiActivity.class);
                        yapilacakEkle.this.startActivity(intent);
                    } else {
                        alertDialog("İşlem Başarısız.!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        epostaVerisi=getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY,"eposta bulunamadı");
        yapilacakEkleRequest yapilacakEkleRequest = new yapilacakEkleRequest(epostaVerisi, baslik.getText().toString(), metin.getText().toString(),
                "Evet", tarihSec.getText().toString(), saatSec.getText().toString(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(yapilacakEkle.this);
        queue.add(yapilacakEkleRequest);
    }

    public void kayitEklemeHatirlatmasiz()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Toast.makeText(yapilacakEkle.this, "İşlem Başarılı", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(yapilacakEkle.this, yapilacaklarListesiActivity.class);
                        yapilacakEkle.this.startActivity(intent);
                    } else {
                        alertDialog("İşlem Başarısız.!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        epostaVerisi=getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY,"eposta bulunamadı");
        yapilacakEkleRequest yapilacakEkleRequest = new yapilacakEkleRequest(epostaVerisi, baslik.getText().toString(), metin.getText().toString(),
                "Hayır", "", "", responseListener);
        RequestQueue queue = Volley.newRequestQueue(yapilacakEkle.this);
        queue.add(yapilacakEkleRequest);
    }
}
