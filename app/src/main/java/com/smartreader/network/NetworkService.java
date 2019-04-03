package com.smartreader.network;

import com.smartreader.model.Response;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nithin Chalakkal on 03-04-2019.
 */
public interface NetworkService {


    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=hfGMrblK9rHSa2YzbTUH0OjqATxMze9C")
    Observable<Response> getBaseURL();
}
