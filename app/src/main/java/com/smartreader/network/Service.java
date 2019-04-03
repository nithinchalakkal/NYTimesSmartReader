package com.smartreader.network;

import android.util.Log;

import com.smartreader.model.Response;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nithin Chalakkal on 03-04-2019.
 */
public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }



    public Subscription getBaseURL(final ResponseCallback<Response> responseCallback)
    {
        return networkService.getBaseURL()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Loaded ","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        responseCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Response response) {
                        responseCallback.onSuccess(response);
                    }
                });
    }



    public abstract interface ResponseCallback<T> {
        void onSuccess(T response);

        void onError(NetworkError networkError);
    }


}
