package com.angelsanchez.bookseeker.utils;

import com.angelsanchez.bookseeker.services.APIService;

public class ApiUtils {

    public ApiUtils(){}

    public static final String BASE_URL = "https://bookseeker-app.herokuapp.com/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
