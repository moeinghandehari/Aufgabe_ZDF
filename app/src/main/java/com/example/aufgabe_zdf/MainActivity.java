package com.example.aufgabe_zdf;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private ArrayList<Item> mItemList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Swipe refresh implementation
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
                        mItemList.clear();
                        parseJSON();
                    }
                }, 1000);
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemList = new ArrayList<Item>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://zdf-cdn.live.cellular.de/mediathekV2/start-page", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("stage");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject stage_item = jsonArray.getJSONObject(i);
                                mItemList.add(new Item(stage_item.getString("headline"), stage_item.getString("titel"), stage_item.getString("beschreibung")));
                            }
                            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, mItemList);
                            mRecyclerView.setAdapter(itemAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}