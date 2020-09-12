package com.hakan.imkbhisseveendeksler.models.handshake.responses;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hakan.imkbhisseveendeksler.models.Status;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class HandShake implements Parcelable
{

    @SerializedName("aesKey")
    @Expose
    private String aesKey;
    @SerializedName("aesIV")
    @Expose
    private String aesIV;
    @SerializedName("authorization")
    @Expose
    private String authorization;
    @SerializedName("lifeTime")
    @Expose
    private String lifeTime;
    @SerializedName("status")
    @Expose
    private Status status;
    public final static Parcelable.Creator<HandShake> CREATOR = new Creator<HandShake>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HandShake createFromParcel(Parcel in) {
            return new HandShake(in);
        }

        public HandShake[] newArray(int size) {
            return (new HandShake[size]);
        }

    }
            ;

    protected HandShake(Parcel in) {
        this.aesKey = ((String) in.readValue((String.class.getClassLoader())));
        this.aesIV = ((String) in.readValue((String.class.getClassLoader())));
        this.authorization = ((String) in.readValue((String.class.getClassLoader())));
        this.lifeTime = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Status) in.readValue((Status.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public HandShake() {
    }

    /**
     *
     * @param authorization
     * @param aesKey
     * @param aesIV
     * @param lifeTime
     * @param status
     */
    public HandShake(String aesKey, String aesIV, String authorization, String lifeTime, Status status) {
        super();
        this.aesKey = aesKey;
        this.aesIV = aesIV;
        this.authorization = authorization;
        this.lifeTime = lifeTime;
        this.status = status;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAesIV() {
        return aesIV;
    }

    public void setAesIV(String aesIV) {
        this.aesIV = aesIV;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("aesKey", aesKey).append("aesIV", aesIV).append("authorization", authorization).append("lifeTime", lifeTime).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aesKey);
        dest.writeValue(aesIV);
        dest.writeValue(authorization);
        dest.writeValue(lifeTime);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
