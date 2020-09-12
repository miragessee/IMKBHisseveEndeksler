package com.hakan.imkbhisseveendeksler.models.handshake.requests;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class HandShakeRequest implements Parcelable
{

    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("systemVersion")
    @Expose
    private String systemVersion;
    @SerializedName("platformName")
    @Expose
    private String platformName;
    @SerializedName("deviceModel")
    @Expose
    private String deviceModel;
    @SerializedName("manifacturer")
    @Expose
    private String manifacturer;
    public final static Parcelable.Creator<HandShakeRequest> CREATOR = new Creator<HandShakeRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HandShakeRequest createFromParcel(Parcel in) {
            return new HandShakeRequest(in);
        }

        public HandShakeRequest[] newArray(int size) {
            return (new HandShakeRequest[size]);
        }

    }
            ;

    protected HandShakeRequest(Parcel in) {
        this.deviceId = ((String) in.readValue((String.class.getClassLoader())));
        this.systemVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.platformName = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceModel = ((String) in.readValue((String.class.getClassLoader())));
        this.manifacturer = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public HandShakeRequest() {
    }

    /**
     *
     * @param deviceModel
     * @param platformName
     * @param deviceId
     * @param systemVersion
     * @param manifacturer
     */
    public HandShakeRequest(String deviceId, String systemVersion, String platformName, String deviceModel, String manifacturer) {
        super();
        this.deviceId = deviceId;
        this.systemVersion = systemVersion;
        this.platformName = platformName;
        this.deviceModel = deviceModel;
        this.manifacturer = manifacturer;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getManifacturer() {
        return manifacturer;
    }

    public void setManifacturer(String manifacturer) {
        this.manifacturer = manifacturer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("deviceId", deviceId).append("systemVersion", systemVersion).append("platformName", platformName).append("deviceModel", deviceModel).append("manifacturer", manifacturer).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(deviceId);
        dest.writeValue(systemVersion);
        dest.writeValue(platformName);
        dest.writeValue(deviceModel);
        dest.writeValue(manifacturer);
    }

    public int describeContents() {
        return 0;
    }

}
