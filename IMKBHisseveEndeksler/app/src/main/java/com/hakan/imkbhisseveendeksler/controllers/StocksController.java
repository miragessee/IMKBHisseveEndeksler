package com.hakan.imkbhisseveendeksler.controllers;

import android.util.Log;

import com.hakan.imkbhisseveendeksler.base.Globals;
import com.hakan.imkbhisseveendeksler.callbacks.HandShakeCallback;
import com.hakan.imkbhisseveendeksler.callbacks.StocksCallback;
import com.hakan.imkbhisseveendeksler.callbacks.StocksDetailCallback;
import com.hakan.imkbhisseveendeksler.models.handshake.requests.HandShakeRequest;
import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;
import com.hakan.imkbhisseveendeksler.models.stocks.requests.StocksRequest;
import com.hakan.imkbhisseveendeksler.models.stocks.responses.Stock;
import com.hakan.imkbhisseveendeksler.models.stocks.responses.StocksResponse;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.requests.StocksDetailRequest;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.StocksDetailResponse;
import com.hakan.imkbhisseveendeksler.retrofit.ApiClient;
import com.hakan.imkbhisseveendeksler.retrofit.HandShakeInterface;
import com.hakan.imkbhisseveendeksler.retrofit.StocksInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StocksController {

    private volatile static StocksController instance;

    private StocksInterface stocksInterface = ApiClient.getClient().create(StocksInterface.class);

    private StocksResponse stocksResponse;
    private StocksDetailResponse stocksDetailResponse;

    public StocksController() {
    }

    public static StocksController getInstance() {
        if (instance == null)
        {
            synchronized (StocksController.class) {
                if (instance == null) {
                    instance = new StocksController();
                }
            }
        }
        return instance;
    }

    public void postStockList(String period, StocksCallback stocksCallback) {
        StocksRequest stocksRequest = new StocksRequest();
        stocksRequest.setPeriod(period);

        Call<StocksResponse> call = stocksInterface.postStockList(stocksRequest, Globals.AUTH_HEADER);

        call.enqueue(new Callback<StocksResponse>() {
            @Override
            public void onResponse(Call<StocksResponse> call, Response<StocksResponse> response) {
                if (response.body() != null) {
                    Log.d("successTagStocksList", response.body().toString());

                    stocksResponse = response.body();

                    stocksCallback.onSuccess(stocksResponse);
                }
                else
                {
                    try {
                        Log.d("errorTagStocksList", response.errorBody().string());

                        stocksCallback.onError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<StocksResponse> call, Throwable t) {
                Log.d("failTagStockList", t.getMessage());
            }
        });
    }

    public void postStockDetail(String id, StocksDetailCallback stocksDetailCallback) {
        StocksDetailRequest stocksDetailRequest = new StocksDetailRequest();
        stocksDetailRequest.setId(id);

        Call<StocksDetailResponse> call = stocksInterface.postStockDetail(stocksDetailRequest, Globals.AUTH_HEADER);

        call.enqueue(new Callback<StocksDetailResponse>() {
            @Override
            public void onResponse(Call<StocksDetailResponse> call, Response<StocksDetailResponse> response) {
                if (response.body() != null) {
                    Log.d("successTagStocksDetail", response.body().toString());

                    stocksDetailResponse = response.body();

                    stocksDetailCallback.onSuccess(stocksDetailResponse);
                }
                else
                {
                    try {
                        Log.d("errorTagStocksDetail", response.errorBody().string());

                        stocksDetailCallback.onError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<StocksDetailResponse> call, Throwable t) {
                Log.d("failTagStockDetail", t.getMessage());
            }
        });
    }
}
