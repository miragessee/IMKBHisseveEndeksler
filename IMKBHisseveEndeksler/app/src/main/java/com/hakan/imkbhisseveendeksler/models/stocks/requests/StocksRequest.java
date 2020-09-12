package com.hakan.imkbhisseveendeksler.models.stocks.requests;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StocksRequest implements Parcelable
{

    @SerializedName("period")
    @Expose
    private String period;
    public final static Parcelable.Creator<StocksRequest> CREATOR = new Creator<StocksRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StocksRequest createFromParcel(Parcel in) {
            return new StocksRequest(in);
        }

        public StocksRequest[] newArray(int size) {
            return (new StocksRequest[size]);
        }

    }
            ;

    protected StocksRequest(Parcel in) {
        this.period = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public StocksRequest() {
    }

    /**
     *
     * @param period
     */
    public StocksRequest(String period) {
        super();
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("period", period).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(period);
    }

    public int describeContents() {
        return 0;
    }

}
