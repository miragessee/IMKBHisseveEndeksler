package com.hakan.imkbhisseveendeksler.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hakan.imkbhisseveendeksler.base.BaseIntent;
import com.hakan.imkbhisseveendeksler.base.BaseUtils;
import com.hakan.imkbhisseveendeksler.callbacks.HandShakeCallback;
import com.hakan.imkbhisseveendeksler.controllers.HandShakeController;
import com.hakan.imkbhisseveendeksler.databinding.ActivityMainBinding;
import com.hakan.imkbhisseveendeksler.models.handshake.responses.HandShake;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager tm;
    private ActivityMainBinding binding;
    private String manifacturer;
    private String model;
    private String systemVersion;
    private String deviceId;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener((View v) -> {
            if (isPermissionGranted()) {
                tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = getDeviceId();
                getDeviceName();
                systemVersion = android.os.Build.VERSION.RELEASE;

                hud = KProgressHUD.create(MainActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();

                HandShakeController handShakeController = HandShakeController.getInstance();
                handShakeController.postHandShake(deviceId, model, manifacturer, "Android", systemVersion, new HandShakeCallback() {
                    @Override
                    public void onSuccess(HandShake handShake) {
                        Log.d("handShakeResponse", handShake.toString());

                        hud.dismiss();
                        BaseIntent.baseIntent(getApplicationContext(), NavigationDrawerActivity.class);
                    }

                    @Override
                    public void onError() {
                        hud.dismiss();
                        BaseUtils.showToast(getApplicationContext(), "HandShake başarısız.");
                    }
                });
            }
        });
    }

    public String getDeviceId() {
        String tmDevice = "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tmDevice = tm.getImei();
        }
        else {
            tmDevice = tm.getDeviceId();
        }

        String tmSerial = tm.getSimSerialNumber();
        String androidId = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }

    public String getDeviceName() {
        manifacturer = Build.MANUFACTURER;
        model = Build.MODEL;
        if (model.startsWith(manifacturer)) {
            return capitalize(model);
        }
        return capitalize(manifacturer) + " " + model;
    }

    private String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
                return false;
            }
        }
        else {
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    BaseUtils.showToast(getApplicationContext(), "İzin vermeniz gerekmektedir.");
                }
                return;
            }
        }
    }
}