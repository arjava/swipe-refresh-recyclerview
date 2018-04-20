package com.arjava.swiperefreshlayout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeContent);
        recyclerView = findViewById(R.id.rvList);

        adapter = new Adapter(model, R.layout.item_rv, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getListSport();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListSport();
            }
        });

    }

    private void getListSport() {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        retrofit2.Call<SportResponse> call = apiService.getListSport();
        call.enqueue(new Callback<SportResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SportResponse> call, Response<SportResponse> response) {
                swipeRefreshLayout.setRefreshing(false);
                SportResponse data = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new Adapter(data.sports, R.layout.item_rv, getApplicationContext()));
            }

            @Override
            public void onFailure(retrofit2.Call<SportResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
