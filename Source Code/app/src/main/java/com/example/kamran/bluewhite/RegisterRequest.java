package com.example.kamran.bluewhite;

/**
 * Created by Can on 25.03.2018.
 */
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends  StringRequest {
    private  static final String REGISTER_REQUEST_URL="http://todolist.cancaliskan.tk/register.php";
    private Map<String, String> params;

    public RegisterRequest(String isim, String eposta, String parola, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("isim", isim);
        params.put("eposta", eposta);
        params.put("parola", parola);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
