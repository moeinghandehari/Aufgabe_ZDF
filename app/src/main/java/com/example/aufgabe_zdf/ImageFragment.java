package com.example.aufgabe_zdf;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<Item> mItemList;
    private RequestQueue mRequestQueue;
    ItemAdapter itemAdapter;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("ImageFragment lifecycle", "onCreateView");

        View rootView = inflater.inflate(R.layout.feed_view, container, false);

        // Swipe refresh implementation
        mSwipeRefreshLayout = rootView.findViewById(R.id.feed_swipe_list);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mItemList.clear();
                        itemAdapter.notifyDataSetChanged();
                        mRequestQueue = Volley.newRequestQueue(getActivity());
                        parseJSON();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mItemList = new ArrayList<Item>();

        mRequestQueue = Volley.newRequestQueue(getActivity());

        parseJSON();

        return rootView;
    }

    private void parseJSON() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://zdf-cdn.live.cellular.de/mediathekV2/start-page", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("stage");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                mItemList.add(new Item(jsonArray.getJSONObject(i).getJSONObject("teaserBild").getJSONObject("1500").getString("url")));
                            }
                            itemAdapter = new ItemAdapter(getActivity(), mItemList);
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

    @Override
    public void onStart() {
        Log.d("ImageFragment lifecycle", "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d("ImageFragment lifecycle", "onStop");
        super.onStop();
        mItemList.clear();
        itemAdapter.notifyDataSetChanged();
    }

}
