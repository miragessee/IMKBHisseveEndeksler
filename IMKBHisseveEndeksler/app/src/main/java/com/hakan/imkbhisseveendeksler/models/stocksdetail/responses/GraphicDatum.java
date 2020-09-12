package com.hakan.imkbhisseveendeksler.models.stocksdetail.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GraphicDatum implements Parcelable
{

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("value")
    @Expose
    private Double value;
    public final static Parcelable.Creator<GraphicDatum> CREATOR = new Creator<GraphicDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GraphicDatum createFromParcel(Parcel in) {
            return new GraphicDatum(in);
        }

        public GraphicDatum[] newArray(int size) {
            return (new GraphicDatum[size]);
        }

    }
            ;

    protected GraphicDatum(Parcel in) {
        this.day = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.value = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public GraphicDatum() {
    }

    /**
     *
     * @param day
     * @param value
     */
    public GraphicDatum(Integer day, Double value) {
        super();
        this.day = day;
        this.value = value;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("day", day).append("value", value).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(day);
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}
