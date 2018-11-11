package com.example.kamran.bluewhite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Can on 27.03.2018.
 */

public class Ayarlar extends Fragment {

    private String ISIM_KEY="com.example.kamran.bluewhite.ISIM";
    private String EPOSTA_KEY="com.example.kamran.bluewhite.EPOSTA";
    private String MAIN_KEY="com.example.kamran.bluewhite.MAIN_DATA";
    private String PAROLA_KEY = "com.example.kamran.bluewhite.PAROLA";


    EditText isim, ePosta, parola;
    SharedPreferences sharedPreferences;
    String isimVerisi, epostaVerisi,parolaVerisi;
    TextView parolaGuncelle,guncelleButon;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View ViewAyarlar =inflater.inflate(R.layout.ayarlar_layout,container,false);

        isim = (EditText) ViewAyarlar.findViewById(R.id.fname);
        ePosta = (EditText) ViewAyarlar.findViewById(R.id.mail);
        parola = (EditText) ViewAyarlar.findViewById(R.id.password);

        guncelleButon = (TextView) ViewAyarlar.findViewById(R.id.guncelle);
        parolaGuncelle = (TextView) ViewAyarlar.findViewById(R.id.parolaDegistir);

        isimVerisi=this.getActivity().getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(ISIM_KEY, "isim bulunamadı");
        epostaVerisi=this.getActivity().getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY,"eposta bulunamadı");
        parolaVerisi = this.getActivity().getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(PAROLA_KEY, "parola bulunamadı");

        isim.setText(isimVerisi);
        ePosta.setText(epostaVerisi);

        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this.getActivity());

        parolaGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getActivity(),changePassword.class);
                startActivity(it);

            }
        });

        guncelleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (parola.getText().toString().equals(parolaVerisi.toString())) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MAIN_KEY, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(ISIM_KEY, isim.getText().toString());
                                    editor.putString(EPOSTA_KEY, ePosta.getText().toString());
                                    editor.commit();

                                    Toast.makeText(getActivity(), "Bilgiler Güncellendi..", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getActivity(), yapilacaklarListesiActivity.class);
                                    getActivity().startActivity(intent);
                                } else {
                                    AlertDialog.Builder message = new AlertDialog.Builder(getActivity());

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

                    changeInformationRequest changeInformationRequest = new changeInformationRequest(epostaVerisi.toString(), isim.getText().toString(), ePosta.getText().toString(), responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(changeInformationRequest);
                } else {
                    dlgAlert.setMessage("Parola yanlış.!");
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

        return ViewAyarlar;
    }






}
