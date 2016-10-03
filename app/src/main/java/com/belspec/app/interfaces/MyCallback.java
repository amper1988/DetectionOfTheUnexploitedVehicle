package com.belspec.app.interfaces;



import retrofit2.Call;
import retrofit2.Response;

public class MyCallback<T> implements retrofit2.Callback {
    ResponseListener listener;

    public void addResponseListener(ResponseListener lstnr){
        listener = lstnr;
    }

    @Override
    public void onResponse(Call call, Response response) {
         if(response.code() == 200) {
            listener.AuthorizationOK( response);
        }else {
            listener.AuthorizationBad(response);
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        listener.AuthorizationFail(t);
    }


}
