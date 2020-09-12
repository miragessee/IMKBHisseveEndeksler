package com.hakan.imkbhisseveendeksler.controllers;

import android.util.Log;

import com.hakan.imkbhisseveendeksler.callbacks.HandShakeCallback;
import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandShakeControllerTest {

    private HandShakeController handShakeController;

    @Before
    public void setUp() throws Exception {
        handShakeController = HandShakeController.getInstance();
    }

    @Test
    public void handShakeTest() {
        handShakeController.postHandShake("fffffffe-f8bf-c8b0-acd1-db8d1717d777", "GM 5 Plus", "General Mobile", "Android", "8.0.0", new HandShakeCallback() {
            @Override
            public void onSuccess(HandShake handShake) {
                Log.d("successTagHandShakeTest", handShake.toString());

                assertTrue(true);
            }

            @Override
            public void onError() {
                Log.d("failTagHandShakeTest","failTagHandShakeTest");

                assertTrue(false);
            }
        });
    }

    @After
    public void tearDown() throws Exception {
    }
}