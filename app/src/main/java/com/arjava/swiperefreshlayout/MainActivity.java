package com.arjava.swiperefreshlayout;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeContent);
        recyclerView = findViewById(R.id.rvList);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        getListSport();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListSport();
            }
        });

    }

    private void getListSport() {
        swipeRefreshLayout.setRefreshing(true);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        retrofit2.Call<SportResponse> call = apiService.getListSport();
        call.enqueue(new Callback<SportResponse>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<SportResponse> call, @NonNull Response<SportResponse> response) {
                swipeRefreshLayout.setRefreshing(false);
                SportResponse data = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                assert data != null;
                recyclerView.setAdapter(new Adapter(data.sports, getApplicationContext()));
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<SportResponse> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
