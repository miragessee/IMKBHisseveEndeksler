package com.hakan.imkbhisseveendeksler.callbacks;

import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;

public interface HandShakeCallback{
    void onSuccess(HandShake handShake);
    void onError();
}