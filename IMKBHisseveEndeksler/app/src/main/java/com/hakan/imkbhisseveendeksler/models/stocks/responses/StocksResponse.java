package com.hakan.imkbhisseveendeksler.models.stocks.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hakan.imkbhisseveendeksler.models.Status;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class StocksResponse implements Parcelable
{

    @SerializedName("stocks")
    @Expose
    private List<Stock> stocks = null;
    @SerializedName("status")
    @Expose
    private Status status;
    public final static Parcelable.Creator<StocksResponse> CREATOR = new Creator<StocksResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StocksResponse createFromParcel(Parcel in) {
            return new StocksResponse(in);
        }

        public StocksResponse[] newArray(int size) {
            return (new StocksResponse[size]);
        }

    };

    protected StocksResponse(Parcel in) {
        in.readList(this.stocks, (com.hakan.imkbhisseveendeksler.models.stocks.responses.Stock.class.getClassLoader()));
        this.status = ((Status) in.readValue((Status.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public StocksResponse() {
    }

    /**
     *
     * @param stocks
     * @param status
     */
    public StocksResponse(List<Stock> stocks, Status status) {
        super();
        this.stocks = stocks;
        this.status = status;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("stocks", stocks).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(stocks);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}