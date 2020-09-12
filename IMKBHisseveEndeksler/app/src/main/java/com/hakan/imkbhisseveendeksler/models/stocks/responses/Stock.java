package com.hakan.imkbhisseveendeksler.models.stocks.responses;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Stock implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isDown")
    @Expose
    private Boolean isDown;
    @SerializedName("isUp")
    @Expose
    private Boolean isUp;
    @SerializedName("bid")
    @Expose
    private Double bid;
    @SerializedName("difference")
    @Expose
    private Double difference;
    @SerializedName("offer")
    @Expose
    private Double offer;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("volume")
    @Expose
    private Double volume;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    public final static Parcelable.Creator<Stock> CREATOR = new Creator<Stock>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Stock createFromParcel(Parcel in) {
            return new Stock(in);
        }

        public Stock[] newArray(int size) {
            return (new Stock[size]);
        }

    };

    protected Stock(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isDown = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isUp = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.bid = ((Double) in.readValue((Double.class.getClassLoader())));
        this.difference = ((Double) in.readValue((Double.class.getClassLoader())));
        this.offer = ((Double) in.readValue((Double.class.getClassLoader())));
        this.price = ((Double) in.readValue((Double.class.getClassLoader())));
        this.volume = ((Double) in.readValue((Double.class.getClassLoader())));
        this.symbol = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Stock() {
    }

    /**
     *
     * @param offer
     * @param volume
     * @param symbol
     * @param isUp
     * @param price
     * @param difference
     * @param id
     * @param bid
     * @param isDown
     */
    public Stock(Integer id, Boolean isDown, Boolean isUp, Double bid, Double difference, Double offer, Double price, Double volume, String symbol) {
        super();
        this.id = id;
        this.isDown = isDown;
        this.isUp = isUp;
        this.bid = bid;
        this.difference = difference;
        this.offer = offer;
        this.price = price;
        this.volume = volume;
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDown() {
        return isDown;
    }

    public void setIsDown(Boolean isDown) {
        this.isDown = isDown;
    }

    public Boolean getIsUp() {
        return isUp;
    }

    public void setIsUp(Boolean isUp) {
        this.isUp = isUp;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("isDown", isDown).append("isUp", isUp).append("bid", bid).append("difference", difference).append("offer", offer).append("price", price).append("volume", volume).append("symbol", symbol).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(isDown);
        dest.writeValue(isUp);
        dest.writeValue(bid);
        dest.writeValue(difference);
        dest.writeValue(offer);
        dest.writeValue(price);
        dest.writeValue(volume);
        dest.writeValue(symbol);
    }

    public int describeContents() {
        return 0;
    }

}