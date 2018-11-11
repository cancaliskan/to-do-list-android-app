package com.example.kamran.bluewhite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Can on 27.03.2018.
 */

public class yapilacaklarListele extends Fragment {
    private String ISIM_KEY="com.example.kamran.bluewhite.ISIM";
    private String EPOSTA_KEY="com.example.kamran.bluewhite.EPOSTA";
    private String MAIN_KEY="com.example.kamran.bluewhite.MAIN_DATA";
    private String PAROLA_KEY = "com.example.kamran.bluewhite.PAROLA";

    String epostaVerisi;

    SharedPreferences sharedPreferences;

    RecyclerView recyclerView;
    KayitAdapter[] adapter = new KayitAdapter[1];
    ArrayList<yapilacaklarListesiClass> yapilacalarListesiArray = new ArrayList<yapilacaklarListesiClass>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View yapilacaklarListele =inflater.inflate(R.layout.activity_yapilacaklar,container,false);

        recyclerView = (RecyclerView) yapilacaklarListele.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        yapilacaklarListesi();

        return yapilacaklarListele;
    }

    private void yapilacaklarListesi()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Kayıtlar yükleniyor..");
                    progressDialog.show();
                    JSONObject jsonResponse = new JSONObject(response);

                        JSONArray array = jsonResponse.getJSONArray("yapilacaklarListesi");
                        for (int i=0; i< array.length(); i++)
                        {
                            JSONObject jo = array.getJSONObject(i);
                            yapilacaklarListesiClass yapilacakKayit = new yapilacaklarListesiClass(jo.getInt("ID"), jo.getString("eposta"),jo.getString("baslik")
                                    ,jo.getString("metin"),jo.getString("hatirlat"),jo.getString("tarih"),jo.getString("saat"));
                            yapilacalarListesiArray.add(yapilacakKayit);
                        }
                        progressDialog.dismiss();

                        adapter[0] = new KayitAdapter(getActivity(), yapilacalarListesiArray);
                        recyclerView.setAdapter(adapter[0]);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        epostaVerisi=this.getActivity().getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY,"eposta bulunamadı");

        yapilacaklarListeleRequest yapilacaklarListeleRequest = new yapilacaklarListeleRequest(epostaVerisi,responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(yapilacaklarListeleRequest);

    }






}
