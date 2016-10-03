package com.belspec.app.interfaces;

import retrofit2.Response;

public interface ResponseListener{
    void AuthorizationOK(Response response);
    void AuthorizationBad(Response response);
    void AuthorizationFail(Throwable t);
}
