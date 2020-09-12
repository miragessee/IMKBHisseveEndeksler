package com.hakan.imkbhisseveendeksler.callbacks;

import com.hakan.imkbhisseveendeksler.models.stocks.responses.StocksResponse;

public interface StocksCallback {
    void onSuccess(StocksResponse stocksResponse);
    void onError();
}
