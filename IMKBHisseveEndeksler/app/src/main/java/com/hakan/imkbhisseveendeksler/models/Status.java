package com.hakan.imkbhisseveendeksler.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Status implements Parcelable
{

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("error")
    @Expose
    private Error error;
    public final static Parcelable.Creator<Status> CREATOR = new Creator<Status>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        public Status[] newArray(int size) {
            return (new Status[size]);
        }

    }
            ;

    protected Status(Parcel in) {
        this.isSuccess = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.error = ((Error) in.readValue((Error.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Status() {
    }

    /**
     *
     * @param error
     * @param isSuccess
     */
    public Status(Boolean isSuccess, Error error) {
        super();
        this.isSuccess = isSuccess;
        this.error = error;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("isSuccess", isSuccess).append("error", error).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isSuccess);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}
