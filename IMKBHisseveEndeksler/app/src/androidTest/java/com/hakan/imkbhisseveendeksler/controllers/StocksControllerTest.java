package com.hakan.imkbhisseveendeksler.controllers;

import android.util.Log;

import com.hakan.imkbhisseveendeksler.base.Globals;
import com.hakan.imkbhisseveendeksler.base.StringEncrypt;
import com.hakan.imkbhisseveendeksler.callbacks.HandShakeCallback;
import com.hakan.imkbhisseveendeksler.callbacks.StocksCallback;
import com.hakan.imkbhisseveendeksler.callbacks.StocksDetailCallback;
import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;
import com.hakan.imkbhisseveendeksler.models.stocks.responses.StocksResponse;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.StocksDetailResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StocksControllerTest {

    private StocksController stocksController;
    private String period;
    private String aes_key;
    private String aes_iv;
    private HandShakeController handShakeController;

    @Before
    public void setUp() throws Exception {
        stocksController = StocksController.getInstance();
        handShakeController = HandShakeController.getInstance();

        handShakeController.postHandShake("fffffffe-f8bf-c8b0-acd1-db8d1717d777", "GM 5 Plus", "General Mobile", "Android", "8.0.0", new HandShakeCallback() {
            @Override
            public void onSuccess(HandShake handShake) {
                Log.d("successTagHandShakeTestBefore", handShake.toString());

                aes_key = handShake.getAesKey();
                aes_iv = handShake.getAesIV();

                try {
                    period = StringEncrypt.encrypt(Globals.AES_KEY, Globals.AES_IVX, "all");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError() {
                Log.d("failTagHandShakeTestBefore","failTagHandShakeTestBefore");
            }
        });
    }

    @Test
    public void postStockListTest() {
        stocksController.postStockList(period, new StocksCallback() {
            @Override
            public void onSuccess(StocksResponse stocksResponse) {
                Log.d("successTagPostStockListTest", stocksResponse.toString());

                assertTrue(true);
            }

            @Override
            public void onError() {
                Log.d("failTagPostStockListTest","failTagHandShakeTest");

                assertTrue(false);
            }
        });
    }

    @Test
    public void postStockDetailTest() {
        //1190
        String stockId_AES = "";
        try {
            stockId_AES = StringEncrypt.encrypt(Globals.AES_KEY,Globals.AES_IVX, "1190");
        } catch (Exception e) {
            e.printStackTrace();
        }
        stocksController.postStockDetail(stockId_AES, new StocksDetailCallback() {
            @Override
            public void onSuccess(StocksDetailResponse stocksDetailResponse) {
                Log.d("successTagPostStockDetailTest", stocksDetailResponse.toString());

                assertTrue(true);
            }

            @Override
            public void onError() {
                Log.d("failTagPostStockDetailTest","failTagHandShakeTest");

                assertTrue(false);
            }
        });
    }

    @After
    public void tearDown() throws Exception {
    }
}