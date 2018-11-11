package com.example.kamran.bluewhite;

import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Can on 11.04.2018.
 */

public class changePasswordRequest extends StringRequest {
    private static final String CHANGE_PASSWORD_REQUEST_URL = "http://todolist.cancaliskan.tk/changePassword.php";
    private Map<String, String> params;

    public changePasswordRequest(String ePosta, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, CHANGE_PASSWORD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eposta", ePosta);
        params.put("parola", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
