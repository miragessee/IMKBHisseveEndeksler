package com.hakan.imkbhisseveendeksler.retrofit;

import com.hakan.imkbhisseveendeksler.models.handshake.requests.HandShakeRequest;
import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HandShakeInterface {

    @POST("handshake/start")
    Call<HandShake> postHandShake(@Body HandShakeRequest handShakeRequest);
}
