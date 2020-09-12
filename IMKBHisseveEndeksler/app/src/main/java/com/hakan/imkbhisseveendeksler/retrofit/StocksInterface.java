package com.hakan.imkbhisseveendeksler.retrofit;

import com.hakan.imkbhisseveendeksler.models.stocks.requests.StocksRequest;
import com.hakan.imkbhisseveendeksler.models.stocks.responses.StocksResponse;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.requests.StocksDetailRequest;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.StocksDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface StocksInterface {

    @POST("stocks/list")
    Call<StocksResponse> postStockList(@Body StocksRequest stocksRequest, @Header("X-VP-Authorization") String authHeader);

    @POST("stocks/detail")
    Call<StocksDetailResponse> postStockDetail(@Body StocksDetailRequest stocksDetailRequest, @Header("X-VP-Authorization") String authHeader);
}
