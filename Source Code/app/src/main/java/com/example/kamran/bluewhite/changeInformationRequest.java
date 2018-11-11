package com.example.kamran.bluewhite;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Can on 13.04.2018.
 */

public class changeInformationRequest extends StringRequest {
    private static final String CHANGE_INFORMATION_REQUEST_URL = "http://todolist.cancaliskan.tk/changeInformation.php";
    private Map<String, String> params;

    public changeInformationRequest(String eski_ePosta, String isim, String eposta, Response.Listener<String> listener) {
        super(Request.Method.POST, CHANGE_INFORMATION_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eski_ePosta", eski_ePosta);
        params.put("isim", isim);
        params.put("eposta", eposta);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}