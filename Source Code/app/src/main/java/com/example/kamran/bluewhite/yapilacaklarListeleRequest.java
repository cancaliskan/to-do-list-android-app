package com.example.kamran.bluewhite;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Can on 14.04.2018.
 */

public class yapilacaklarListeleRequest extends StringRequest {
    private static final String YAPILACAK_Listele_REQUEST_URL = "http://todolist.cancaliskan.tk/yapilacaklarListesi.php";
    private Map<String, String> params;

    public yapilacaklarListeleRequest(String ePosta, Response.Listener<String> listener) {
        super(Request.Method.POST, YAPILACAK_Listele_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eposta", ePosta);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}