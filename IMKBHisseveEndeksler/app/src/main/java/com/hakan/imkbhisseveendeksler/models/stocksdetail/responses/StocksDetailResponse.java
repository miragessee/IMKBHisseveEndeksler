package com.hakan.imkbhisseveendeksler.models.stocksdetail.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hakan.imkbhisseveendeksler.models.Status;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class StocksDetailResponse implements Parcelable
{

    @SerializedName("isDown")
    @Expose
    private Boolean isDown;
    @SerializedName("isUp")
    @Expose
    private Boolean isUp;
    @SerializedName("bid")
    @Expose
    private Double bid;
    @SerializedName("channge")
    @Expose
    private Double channge;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("difference")
    @Expose
    private Double difference;
    @SerializedName("offer")
    @Expose
    private Double offer;
    @SerializedName("highest")
    @Expose
    private Double highest;
    @SerializedName("lowest")
    @Expose
    private Double lowest;
    @SerializedName("maximum")
    @Expose
    private Double maximum;
    @SerializedName("minimum")
    @Expose
    private Double minimum;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("volume")
    @Expose
    private Double volume;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("graphicData")
    @Expose
    private List<GraphicDatum> graphicData = null;
    @SerializedName("status")
    @Expose
    private Status status;
    public final static Parcelable.Creator<StocksDetailResponse> CREATOR = new Creator<StocksDetailResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StocksDetailResponse createFromParcel(Parcel in) {
            return new StocksDetailResponse(in);
        }

        public StocksDetailResponse[] newArray(int size) {
            return (new StocksDetailResponse[size]);
        }

    }
            ;

    protected StocksDetailResponse(Parcel in) {
        this.isDown = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isUp = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.bid = ((Double) in.readValue((Double.class.getClassLoader())));
        this.channge = ((Double) in.readValue((Double.class.getClassLoader())));
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.difference = ((Double) in.readValue((Double.class.getClassLoader())));
        this.offer = ((Double) in.readValue((Double.class.getClassLoader())));
        this.highest = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lowest = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maximum = ((Double) in.readValue((Double.class.getClassLoader())));
        this.minimum = ((Double) in.readValue((Double.class.getClassLoader())));
        this.price = ((Double) in.readValue((Double.class.getClassLoader())));
        this.volume = ((Double) in.readValue((Double.class.getClassLoader())));
        this.symbol = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.graphicData, (com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.GraphicDatum.class.getClassLoader()));
        this.status = ((Status) in.readValue((Status.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public StocksDetailResponse() {
    }

    /**
     *
     * @param graphicData
     * @param symbol
     * @param count
     * @param lowest
     * @param isDown
     * @param offer
     * @param volume
     * @param channge
     * @param isUp
     * @param highest
     * @param price
     * @param difference
     * @param maximum
     * @param bid
     * @param minimum
     * @param status
     */
    public StocksDetailResponse(Boolean isDown, Boolean isUp, Double bid, Double channge, Integer count, Double difference, Double offer, Double highest, Double lowest, Double maximum, Double minimum, Double price, Double volume, String symbol, List<GraphicDatum> graphicData, Status status) {
        super();
        this.isDown = isDown;
        this.isUp = isUp;
        this.bid = bid;
        this.channge = channge;
        this.count = count;
        this.difference = difference;
        this.offer = offer;
        this.highest = highest;
        this.lowest = lowest;
        this.maximum = maximum;
        this.minimum = minimum;
        this.price = price;
        this.volume = volume;
        this.symbol = symbol;
        this.graphicData = graphicData;
        this.status = status;
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

    public Double getChannge() {
        return channge;
    }

    public void setChannge(Double channge) {
        this.channge = channge;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Double getHighest() {
        return highest;
    }

    public void setHighest(Double highest) {
        this.highest = highest;
    }

    public Double getLowest() {
        return lowest;
    }

    public void setLowest(Double lowest) {
        this.lowest = lowest;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
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

    public List<GraphicDatum> getGraphicData() {
        return graphicData;
    }

    public void setGraphicData(List<GraphicDatum> graphicData) {
        this.graphicData = graphicData;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("isDown", isDown).append("isUp", isUp).append("bid", bid).append("channge", channge).append("count", count).append("difference", difference).append("offer", offer).append("highest", highest).append("lowest", lowest).append("maximum", maximum).append("minimum", minimum).append("price", price).append("volume", volume).append("symbol", symbol).append("graphicData", graphicData).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isDown);
        dest.writeValue(isUp);
        dest.writeValue(bid);
        dest.writeValue(channge);
        dest.writeValue(count);
        dest.writeValue(difference);
        dest.writeValue(offer);
        dest.writeValue(highest);
        dest.writeValue(lowest);
        dest.writeValue(maximum);
        dest.writeValue(minimum);
        dest.writeValue(price);
        dest.writeValue(volume);
        dest.writeValue(symbol);
        dest.writeList(graphicData);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
