package com.hakan.imkbhisseveendeksler.callbacks;

import com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.StocksDetailResponse;

public interface StocksDetailCallback {
    void onSuccess(StocksDetailResponse stocksDetailResponse);
    void onError();
}
