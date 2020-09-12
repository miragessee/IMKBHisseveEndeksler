package com.hakan.imkbhisseveendeksler.controllers;

import android.util.Log;

import com.hakan.imkbhisseveendeksler.base.Globals;
import com.hakan.imkbhisseveendeksler.base.StringEncrypt;
import com.hakan.imkbhisseveendeksler.callbacks.HandShakeCallback;
import com.hakan.imkbhisseveendeksler.models.handshake.requests.HandShakeRequest;
import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;
import com.hakan.imkbhisseveendeksler.retrofit.ApiClient;
import com.hakan.imkbhisseveendeksler.retrofit.HandShakeInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HandShakeController {

    private volatile static HandShakeController instance;

    private HandShakeInterface handShakeInterface = ApiClient.getClient().create(HandShakeInterface.class);

    private HandShake handShake;

    public HandShakeController() {
    }

    public static HandShakeController getInstance() {
        if (instance == null)
        {
            synchronized (HandShakeController.class) {
                if (instance == null) {
                    instance = new HandShakeController();
                }
            }
        }
        return instance;
    }

    public void postHandShake(String deviceId, String deviceModel, String manifacturer, String platformName, String systemVersion, HandShakeCallback handShakeCallback) {
        HandShakeRequest handShakeRequest = new HandShakeRequest();
        handShakeRequest.setDeviceId(deviceId);
        handShakeRequest.setDeviceModel(deviceModel);
        handShakeRequest.setManifacturer(manifacturer);
        handShakeRequest.setPlatformName(platformName);
        handShakeRequest.setSystemVersion(systemVersion);

        Call<HandShake> call = handShakeInterface.postHandShake(handShakeRequest);

        call.enqueue(new Callback<HandShake>() {
            @Override
            public void onResponse(Call<HandShake> call, Response<HandShake> response) {
                if (response.body() != null) {
                    Log.d("successTagHandShake", response.body().toString());

                    handShake = response.body();

                    handShakeCallback.onSuccess(handShake);

                    Globals.AES_KEY = handShake.getAesKey();
                    Globals.AES_IVX = handShake.getAesIV();
                    Globals.AUTH_HEADER = handShake.getAuthorization();
                }
                else
                {
                    try {
                        Log.d("errorTagHandShake", response.errorBody().string());

                        handShakeCallback.onError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HandShake> call, Throwable t) {
                Log.d("failTagHandShake", t.getMessage());
            }
        });
    }
}
