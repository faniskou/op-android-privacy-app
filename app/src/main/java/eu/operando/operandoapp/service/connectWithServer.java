package eu.operando.operandoapp.service;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import eu.operando.operandoapp.database.model.UrlAppChecker;
import eu.operando.operandoapp.database.model.UrlStatistic;

/**
 * Created by fanis on 9/30/2017.
 */

public class connectWithServer {
    final private RequestQueue nQueue;
    final private Context cContext;
    final private String apiurl = "http://localhost:8000/v1/";

    public connectWithServer(Context currentContext) {
        nQueue = Volley.newRequestQueue(currentContext);
        cContext = currentContext;
    }

    public void TestSendSync(UrlStatistic currentStatistic) {
        //test call api sync
        JSONObject syncurl = new JSONObject();
        JSONObject syncdata = new JSONObject();
        String currentResponse = "";


        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                apiurl + "statistics/testapp", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        String mstring = "";
                        try {
                            mstring = response.toString(2);
                        } catch (JSONException e) {
                            mstring = "";
                            e.printStackTrace();
                        }

                        Toast.makeText(cContext,
                                "Response is: " + mstring,
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                if (error == null || error.networkResponse == null) {
                    return;
                }
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    Toast.makeText(cContext,
                            "Fail in: " + new String(error.networkResponse.data, "UTF-8"),
                            Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        // Add the request to the RequestQueue.
        stringRequest.setTag("main");
        nQueue.add(stringRequest);
    }

    public void TestGetSync() {
        //test call api sync
        JSONObject syncurl = new JSONObject();
        JSONObject syncdata = new JSONObject();
        String currentResponse = "";


        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                apiurl + "appsIdentity/groups", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        String mstring = "";
                        try {
                            mstring = response.toString(2);
                        } catch (JSONException e) {
                            mstring = "";
                            e.printStackTrace();
                        }

                        Toast.makeText(cContext,
                                "Response is: " + mstring,
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                if (error == null || error.networkResponse == null) {
                    return;
                }
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    Toast.makeText(cContext,
                            "Fail in: " + new String(error.networkResponse.data, "UTF-8"),
                            Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        // Add the request to the RequestQueue.
        stringRequest.setTag("main");
        nQueue.add(stringRequest);
    }

    public void TestSendApp(UrlAppChecker currentUrlAppChecker) {
        //test call api sync
        JSONObject syncurl = new JSONObject();
        JSONObject syncreq = new JSONObject();
        JSONObject json = new JSONObject();
        JSONObject manJson = new JSONObject();
        try {
            syncreq.put("id", 1);
            syncreq.put("name", "checkapp");
            syncreq.put("domainurl", currentUrlAppChecker.domainurl);
            syncreq.put("app_name", currentUrlAppChecker.app_name);
            syncreq.put("count", currentUrlAppChecker.count);
            syncreq.put("duration", currentUrlAppChecker.duration);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject syncdata = new JSONObject();
        String currentResponse = "";


        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                apiurl + "appsIdentity/groups", syncreq,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        String mstring = "";
                        try {
                            mstring = response.toString(2);
                        } catch (JSONException e) {
                            mstring = "";
                            e.printStackTrace();
                        }

                        Toast.makeText(cContext,
                                "Response is: " + mstring,
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                if (error == null || error.networkResponse == null) {
                    return;
                }
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    Toast.makeText(cContext,
                            "Fail in: " + new String(error.networkResponse.data, "UTF-8"),
                            Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        // Add the request to the RequestQueue.
        stringRequest.setTag("main");
        nQueue.add(stringRequest);
    }


    public void TestGetApp() {
        //test call api sync
        JSONObject syncurl = new JSONObject();
        JSONObject syncdata = new JSONObject();
        String currentResponse = "";


        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                apiurl + "appsIdentity/appgroup", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        String mstring = "";
                        try {
                            mstring = response.toString(2);
                        } catch (JSONException e) {
                            mstring = "";
                            e.printStackTrace();
                        }

                        Toast.makeText(cContext,
                                "Response is: " + mstring,
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                if (error == null || error.networkResponse == null) {
                    return;
                }
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    Toast.makeText(cContext,
                            "Fail in: " + new String(error.networkResponse.data, "UTF-8"),
                            Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        // Add the request to the RequestQueue.
        stringRequest.setTag("main");
        nQueue.add(stringRequest);
    }

    public void TestGetApps() {
        //test call api sync
        JSONObject syncurl = new JSONObject();
        JSONObject syncdata = new JSONObject();
        String currentResponse = "";


        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                apiurl + "appsIdentity/groups", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        String mstring = "";
                        try {
                            mstring = response.toString(2);
                        } catch (JSONException e) {
                            mstring = "";
                            e.printStackTrace();
                        }

                        Toast.makeText(cContext,
                                "Response is: " + mstring,
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                if (error == null || error.networkResponse == null) {
                    return;
                }
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    Toast.makeText(cContext,
                            "Fail in: " + new String(error.networkResponse.data, "UTF-8"),
                            Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        // Add the request to the RequestQueue.
        stringRequest.setTag("main");
        nQueue.add(stringRequest);
    }



    public void closeQueue() {
        if (nQueue != null) {
            nQueue.cancelAll("main");
        }
    }
}
