package com.example.mentalhealthcounselling1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET("chat/{message}")
    Call<MsgModal> getMessage(@Path("message") String message);

}
