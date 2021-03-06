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

import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import eu.operando.operandoapp.database.DatabaseHelper;
import eu.operando.operandoapp.database.model.UrlAppChecker;
import eu.operando.operandoapp.database.model.UrlStatistic;

/**
 * Created by fanis on 9/30/2017.
 */

public class connectWithServer {

    final private RequestQueue nQueue;
    final private Context cContext;
    final private String apiurl = "https://server-ptyx-mpsp14040.herokuapp.com/";
    public List<String> remoteapplist =  new ArrayList<String>();

    //    final private String apiurl = "https://server-ptyx-mpsp14040.herokuapp.com/";

    //final private String initialcloudcatalog = "ioanna";

    public connectWithServer(Context currentContext) {
        nQueue = Volley.newRequestQueue(currentContext);
        cContext = currentContext;
    }

    public void syncStatistics(List<UrlStatistic> urlStatistics, final String key) {
        Toast.makeText(cContext,
                "start Uploading Screen Statistics ",
                Toast.LENGTH_SHORT).show();
        for (final UrlStatistic curr : urlStatistics) {
            JSONObject syncreq = new JSONObject();
            try {
                syncreq.put("id", 1);
                syncreq.put("name", key);
                syncreq.put("domainurl", curr.domainurl);
                syncreq.put("count", curr.count);
                syncreq.put("modified", curr.modified);
                syncreq.put("sourceactivity", "operando");
                if (curr.hidden < 1) {
                    syncreq.put("hidden", "no");
                } else {
                    syncreq.put("hidden", "yes");
                }
                syncreq.put("category", curr.category);
                syncreq.put("extrainfo", "{}");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final String toasting = curr.domainurl;
            // Request a string response from the provided URL.
            JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.PUT,
                    apiurl + "statistics/" + key, syncreq,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Display the first 500 characters of the response string.
                            String mstring = "";
                            try {
                                mstring = "upload " + toasting + "-->" + response.toString(2);
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
                        Toast.makeText(cContext,
                                "Upload finished: " + toasting,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //get status code here
                    final String statusCode = String.valueOf(error.networkResponse.statusCode);
                    if (statusCode.equals("409")) {
                        updateStatistic(curr, key);
                    } else {
                        //get response body and parse with appropriate encoding
                        try {
                            Toast.makeText(cContext,
                                    "Fail in: " + new String(error.networkResponse.data, "UTF-8"),
                                    Toast.LENGTH_SHORT).show();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            // Add the request to the RequestQueue.
            jRequest.setTag("uploadstatistics");
            nQueue.add(jRequest);
        }

    }

    public void updateStatistic(final UrlStatistic curr, String key) {
        JSONObject syncreq = new JSONObject();
        try {
            syncreq.put("id", 1);
            syncreq.put("name", key);
            syncreq.put("domainurl", curr.domainurl);
            syncreq.put("count", curr.count);
            syncreq.put("modified", curr.modified);
            syncreq.put("sourceactivity", "operando");
            if (curr.hidden < 1) {
                syncreq.put("hidden", "no");
            } else {
                syncreq.put("hidden", "yes");
            }
            syncreq.put("category", curr.category);
            syncreq.put("extrainfo", "{}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String toasting = curr.domainurl;
        // Request a string response from the provided URL.
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.POST,
                apiurl + "statistics/" + key + "/" + curr.domainurl, syncreq,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(cContext,
                                "Refresh Upload finished: " + toasting,
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                if (error == null || error.networkResponse == null) {
                    Toast.makeText(cContext,
                            "Refresh Upload finished: " + toasting,
                            Toast.LENGTH_SHORT).show();
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
        jRequest.setTag("uploadstatistics");
        nQueue.add(jRequest);
    }

    public void getSyncStatistics(final DatabaseHelper db, String key) {
        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                apiurl + "statistics/" + key + "/", null,
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
                        try {
                            JSONArray dataArray = response.getJSONArray("Statistics");
                            for (int n = 0; n < dataArray.length(); n++) {
                                int curr_hidden = 1;
                                JSONObject object = dataArray.getJSONObject(n);
                                if (object.getString("hidden").equals("no")) {
                                    curr_hidden = 0;
                                }
                                Toast.makeText(cContext,
                                        "Loading : " +  object.getString("domainurl"),
                                        Toast.LENGTH_SHORT).show();

                                String curr_domainurl = object.getString("domainurl");
                                int curr_count = object.getInt("count");
                                String curr_modified = object.getString("modified");
                                String curr_sourceactivity = object.getString("sourceactivity");
                                String curr_category = null;

                                UrlStatistic input = new UrlStatistic();
                                input =
                                        new UrlStatistic(
                                                curr_domainurl,
                                                curr_count,
                                                curr_modified,
                                                curr_hidden,
                                                curr_sourceactivity,
                                                curr_category);

                                try {
                                    if (db.updateUrlStatistic(input) < 1) {
                                        db.createUrlStatistic(input);
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(cContext,
                                            "Fail to download url statistic ",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        } catch (JSONException e) {
                            Toast.makeText(cContext,
                                    "Fail to loop on the response data ",
                                    Toast.LENGTH_SHORT).show();
                        }

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
        stringRequest.setTag("downloadstatistics");
        nQueue.add(stringRequest);
    }


    public void SendApp(UrlAppChecker currentUrlAppChecker) {
        //test call api sync
        JSONObject syncreq = new JSONObject();
        try {
            syncreq.put("id", 1);
            syncreq.put("name", currentUrlAppChecker.app_name);
            syncreq.put("domainurl", currentUrlAppChecker.domainurl);
            syncreq.put("app_name", currentUrlAppChecker.app_name);
            syncreq.put("count", currentUrlAppChecker.count);
            syncreq.put("duration", currentUrlAppChecker.duration);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject syncdata = new JSONObject();
        String currentResponse = "";


        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT,
                apiurl + "appsIdentity/" + currentUrlAppChecker.app_name , syncreq,
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

    public void getRemoteApp(final DatabaseHelper db, String key) {
        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                apiurl + "appsIdentity/" + key + "/"+ key + "/", null,
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
                        try {
                            JSONArray dataArray = response.getJSONArray("AppIdentities");
                            for (int n = 0; n < dataArray.length(); n++) {
                                JSONObject object = dataArray.getJSONObject(n);

                                Toast.makeText(cContext,
                                        "Loading : " +  object.getString("domainurl"),
                                        Toast.LENGTH_SHORT).show();

                                String curr_app_name = object.getString("app_name");
                                int curr_count = object.getInt("count");
                                String curr_domainurl = object.getString("domainurl");
                                int curr_secs = object.getInt("duration");

                                UrlAppChecker input = new UrlAppChecker();
                                input =
                                        new UrlAppChecker(curr_app_name,curr_domainurl,curr_count,curr_secs);
                                try {
                                        db.createUrlAppChecker(input);
                                } catch (Exception e) {
                                    Toast.makeText(cContext,
                                            "Fail to download app checker statistic ",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        } catch (JSONException e) {
                            Toast.makeText(cContext,
                                    "Fail to loop on the response data ",
                                    Toast.LENGTH_SHORT).show();
                        }

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
        stringRequest.setTag("downloadapps");
        nQueue.add(stringRequest);
    }

    public void GetRemoteApps() {

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
                            JSONArray dataArray = response.getJSONArray("groups");
                            remoteapplist =  new ArrayList<String>();
                            for (int n = 0; n < dataArray.length(); n++) {
                                String object = dataArray.getString(n);

                                remoteapplist.add(object);

                            }
                            } catch (JSONException e) {
                            mstring = "";
                            e.printStackTrace();
                        }

                        Toast.makeText(cContext,
                                "Loaded remote apps: " + mstring,
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
        stringRequest.setTag("urlapps");
        nQueue.add(stringRequest);
    }

    public void closeQueue() {
        if (nQueue != null) {
            nQueue.cancelAll("main");
        }
    }
}
