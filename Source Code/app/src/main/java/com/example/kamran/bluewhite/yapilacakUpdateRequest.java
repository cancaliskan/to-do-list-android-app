package com.example.kamran.bluewhite;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Can on 15.04.2018.
 */

public class yapilacakUpdateRequest  extends StringRequest {
    private static final String YAPILACAK_Update_REQUEST_URL = "http://todolist.cancaliskan.tk/yapilacakGuncelle.php";
    private Map<String, String> params;

    public yapilacakUpdateRequest(String ID, String baslik,
                                String metin, String hatirlat, String tarih,String saat, Response.Listener<String> listener) {
        super(Request.Method.POST, YAPILACAK_Update_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("ID", ID);
        params.put("baslik", baslik);
        params.put("metin", metin);
        params.put("hatirlat", hatirlat);
        params.put("tarih", tarih);
        params.put("saat", saat);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}