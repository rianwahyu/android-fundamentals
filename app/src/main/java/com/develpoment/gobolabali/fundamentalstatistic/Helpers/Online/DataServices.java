package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataServices {
    @GET("access_android")
    Call<Base> importAndroid();

}
