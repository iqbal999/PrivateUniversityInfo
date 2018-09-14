package com.example.iqbal.privateuniversityinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UniversityList extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<String> arrayAdapter;

    String[] university_list;
    String div;
    String fac, fees, admission, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_list);
        div = getIntent().getStringExtra("div");
        listView = findViewById(R.id.listView);

        switch (div) {
            case "dhk":
                university_list = getResources().getStringArray(R.array.dhk_uni_list);
                break;

            case "ctg":
                university_list = getResources().getStringArray(R.array.ctg_uni_list);
                break;

            case "raj":
                university_list = getResources().getStringArray(R.array.raj_uni_list);
                break;

            case "khu":
                university_list = getResources().getStringArray(R.array.khu_uni_list);
                break;

            case "bar":
                university_list = getResources().getStringArray(R.array.bar_uni_list);
                break;

            case "syl":
                university_list = getResources().getStringArray(R.array.syl_uni_list);
                break;

            case "rang":
                university_list = getResources().getStringArray(R.array.rang_uni_list);
                break;


        }


        arrayAdapter = new ArrayAdapter<String>(UniversityList.this, R.layout.itemview, R.id.textView, university_list);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jsonParse(div, position);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in,R.anim.activity_back_out);
    }

    public void jsonParse(final String div, final int pos) {

        RequestQueue mqueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        String url = "";
        switch (div) {
            case "dhk":
                url = "https://api.myjson.com/bins/10er28";
                break;

            case "ctg":
                url = "https://api.myjson.com/bins/14in4w";
                break;

            case "raj":
                url = "https://api.myjson.com/bins/j56io";
                break;

            case "khu":
                url = "https://api.myjson.com/bins/j7bog";
                break;

            case "bar":
                url = "https://api.myjson.com/bins/jqm4g";
                break;

            case "syl":
                url = "https://api.myjson.com/bins/w8pts";
                break;

            case "rang":
                url = "https://api.myjson.com/bins/so474";
                break;


        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);


                        if (i == pos) {
                            fac = jsonObject.getString("faculty_and_sub");
                            fees = jsonObject.getString("tution_fees");
                            admission = jsonObject.getString("admission_info");
                            contact = jsonObject.getString("contact");

                            startActivity(new Intent(UniversityList.this, Main2Activity.class)
                                    .putExtra("fac", fac)
                                    .putExtra("tution_fees", fees)
                                    .putExtra("adm", admission)
                                    .putExtra("contact", contact));
                            overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }


        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UniversityList.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).

                addToRequestQueue(jsonArrayRequest);
    }
}
