package com.example.kamran.bluewhite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Can on 15.04.2018.
 */

public class KayitAdapter extends RecyclerView.Adapter<KayitAdapter.KayitViewHolder> {

    private Context ctx;
    private ArrayList<yapilacaklarListesiClass> kayitList;

    public KayitAdapter(Context ctx, ArrayList<yapilacaklarListesiClass> kayitList) {
        this.ctx = ctx;
        this.kayitList = kayitList;
    }

    @Override
    public KayitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater ınflater = LayoutInflater.from(ctx);
        View view = ınflater.inflate(R.layout.list_yapilacaklar, null);
        return new KayitViewHolder(view);
    }

    public yapilacaklarListesiClass kayitDetay;

    @Override
    public void onBindViewHolder(KayitViewHolder holder, int position) {
        yapilacaklarListesiClass yapilacaklarListesiClass = kayitList.get(position);
        holder.baslik.setText(yapilacaklarListesiClass.getBaslik());
        holder.aciklama.setText(yapilacaklarListesiClass.getMetin());

        if (yapilacaklarListesiClass.getHatirlat().equals("Evet")) {
            if (Calendar.getInstance().get(Calendar.YEAR) >= yapilacaklarListesiClass.getYil(yapilacaklarListesiClass.getTarih()))
            {
                if(Calendar.getInstance().get(Calendar.YEAR) > yapilacaklarListesiClass.getYil(yapilacaklarListesiClass.getTarih()))
                {
                    holder.cardView.setCardBackgroundColor(Color.GREEN);
                }
                else {
                    if ((Calendar.getInstance().get(Calendar.MONTH)+1) >= yapilacaklarListesiClass.getAy(yapilacaklarListesiClass.getTarih())) {
                        if((Calendar.getInstance().get(Calendar.MONTH)+1) > yapilacaklarListesiClass.getAy(yapilacaklarListesiClass.getTarih()))
                        {
                            holder.cardView.setCardBackgroundColor(Color.GREEN);
                        }
                        else {
                            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) >= yapilacaklarListesiClass.getGun(yapilacaklarListesiClass.getTarih())) {
                                if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) > yapilacaklarListesiClass.getGun(yapilacaklarListesiClass.getTarih()))
                                {
                                    holder.cardView.setCardBackgroundColor(Color.GREEN);
                                }
                                else {
                                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= yapilacaklarListesiClass.getSaatBolumu(yapilacaklarListesiClass.getSaat())) {
                                        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > yapilacaklarListesiClass.getSaatBolumu(yapilacaklarListesiClass.getSaat()))
                                        {
                                            holder.cardView.setCardBackgroundColor(Color.GREEN);
                                        }
                                        else {
                                            if (Calendar.getInstance().get(Calendar.MINUTE) >= yapilacaklarListesiClass.getDakikaBolumu(yapilacaklarListesiClass.getSaat())) {
                                                holder.cardView.setCardBackgroundColor(Color.GREEN);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        holder.setItemClickListener(new itemClickListener() {
            @Override
            public void onItemClick(int pos) {
                kayitDetay = kayitList.get(pos);
                openDetailActivity(kayitDetay);
            }
        });
    }

    private void openDetailActivity(yapilacaklarListesiClass kayitDetay) {
        Intent intent = new Intent(ctx, yapilacakEkle.class);
        intent.putExtra("ID", (kayitDetay.getID() + ""));
        intent.putExtra("eposta", kayitDetay.getEposta());
        intent.putExtra("baslik", kayitDetay.getBaslik());
        intent.putExtra("metin", kayitDetay.getMetin());
        intent.putExtra("hatirlat", kayitDetay.getHatirlat());
        intent.putExtra("tarih", kayitDetay.getTarih());
        intent.putExtra("saat", kayitDetay.getSaat());
        ctx.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return kayitList.size();
    }


    class KayitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        itemClickListener itemClickListener;
        TextView baslik, aciklama;
        CardView cardView;

        public KayitViewHolder(View itemView) {
            super(itemView);

            int yil, ay, gun, saat, dakika;
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            baslik = (TextView) itemView.findViewById(R.id.baslik);
            aciklama = (TextView) itemView.findViewById(R.id.aciklama);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

        public void setItemClickListener(itemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
}
