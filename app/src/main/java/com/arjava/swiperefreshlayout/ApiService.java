package com.arjava.swiperefreshlayout;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(BuildConfig.BASE_URL+"api/v1/json/"+BuildConfig.API_KEY+"/all_sports.php")
    Call<SportResponse> getListSport();

}
