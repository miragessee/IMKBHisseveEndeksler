package com.hakan.imkbhisseveendeksler.models.stocksdetail.requests;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StocksDetailRequest implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    public final static Parcelable.Creator<StocksDetailRequest> CREATOR = new Creator<StocksDetailRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StocksDetailRequest createFromParcel(Parcel in) {
            return new StocksDetailRequest(in);
        }

        public StocksDetailRequest[] newArray(int size) {
            return (new StocksDetailRequest[size]);
        }

    }
            ;

    protected StocksDetailRequest(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public StocksDetailRequest() {
    }

    /**
     *
     * @param id
     */
    public StocksDetailRequest(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}