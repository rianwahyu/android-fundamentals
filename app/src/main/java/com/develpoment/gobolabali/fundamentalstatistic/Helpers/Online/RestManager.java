package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {
    private DataServices mDataService;

    public DataServices getDataService() {
        if (mDataService == null) {
            mDataService = (DataServices) new Retrofit.Builder()
                    .baseUrl("http://register.gobolabali.com/demo/adminarea/json?key=jsonbwdgbb2018")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DataServices.class);
        }
        return mDataService;
    }
}
