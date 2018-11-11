package com.example.kamran.bluewhite;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Can on 26.03.2018.
 */

public class LoginRequest extends StringRequest {
    private  static final String LOGIN_REQUEST_URL="http://todolist.cancaliskan.tk/login.php";
    private Map<String, String> params;

    public LoginRequest(String eposta, String parola, Response.Listener<String> listener)
    {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eposta", eposta);
        params.put("parola", parola);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
