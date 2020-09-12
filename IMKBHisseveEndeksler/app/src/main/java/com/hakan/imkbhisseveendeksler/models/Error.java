package com.hakan.imkbhisseveendeksler.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error implements Parcelable
{

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<Error> CREATOR = new Creator<Error>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        public Error[] newArray(int size) {
            return (new Error[size]);
        }

    }
            ;

    protected Error(Parcel in) {
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Error() {
    }

    /**
     *
     * @param code
     * @param message
     */
    public Error(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code).append("message", message).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}
