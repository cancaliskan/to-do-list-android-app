package com.example.kamran.bluewhite;

/**
 * Created by Can on 14.04.2018.
 */

public class yapilacaklarListesiClass {
    private String eposta,baslik,metin,hatirlat,tarih,saat;
    private int ID;
    public yapilacaklarListesiClass(int ID, String eposta, String baslik, String metin, String hatirlat, String tarih, String saat ) {
        this.ID=ID;
        this.eposta=eposta;
        this.baslik=baslik;
        this.metin=metin;
        this.hatirlat=hatirlat;
        this.tarih=tarih;
        this.saat=saat;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getMetin() {
        return metin;
    }

    public void setMetin(String metin) {
        this.metin = metin;
    }

    public String getHatirlat() {
        return hatirlat;
    }

    public void setHatirlat(String hatirlat) {
        this.hatirlat = hatirlat;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGun(String tarih)
    {
        String tempGun="";
        for (int i = 0; i < 3; i++)
        {
            if(tarih.charAt(i) != '/')
            {
                tempGun += tarih.charAt(i);
            }
            else
                break;
        }
        return Integer.parseInt(tempGun);
    }

    public int getAy(String tarih)
    {
        String slashCheck="";
        String tempAy="";
        for (int i = 0; i < tarih.length(); i++)
        {
            if(slashCheck.equals("/") && tarih.charAt(i) != '/')
            {
                tempAy += tarih.charAt(i);
            }
            if(tarih.charAt(i) == '/')
            {
                slashCheck += "/";
            }
        }
        return Integer.parseInt(tempAy);
    }

    public int getYil(String tarih)
    {
        String slashCheck="";
        String tempYil="";
        for (int i = 0; i < tarih.length(); i++)
        {
            if(slashCheck.equals("//"))
            {
                tempYil += tarih.charAt(i);
            }
            if(tarih.charAt(i) == '/')
            {
                slashCheck += "/";
            }
        }
        return Integer.parseInt(tempYil);
    }

    public int getSaatBolumu(String saat) {
        String slashCheck="";
        String tempSaat="";

        for (int i = 0; i < saat.length(); i++)
        {
            if(saat.charAt(i) != ':')
            {
                tempSaat += saat.charAt(i);
            }
            else
                break;
        }
        return Integer.parseInt(tempSaat);
    }

    public int getDakikaBolumu(String saat) {
        String slashCheck="";
        String tempDakika="";

        for (int i = 0; i < saat.length(); i++)
        {
            if(slashCheck.equals(":"))
            {
                tempDakika += saat.charAt(i);
            }
            if(saat.charAt(i) == ':')
            {
                slashCheck += ":";
            }
        }
        return Integer.parseInt(tempDakika);
    }
}
