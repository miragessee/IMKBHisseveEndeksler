package com.hakan.imkbhisseveendeksler.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.hakan.imkbhisseveendeksler.R;
import com.hakan.imkbhisseveendeksler.activities.ui.chartcustom.MyMarkerView;
import com.hakan.imkbhisseveendeksler.activities.ui.chartcustom.VerticalTextView;
import com.hakan.imkbhisseveendeksler.base.BaseUtils;
import com.hakan.imkbhisseveendeksler.base.Globals;
import com.hakan.imkbhisseveendeksler.base.StringEncrypt;
import com.hakan.imkbhisseveendeksler.callbacks.StocksDetailCallback;
import com.hakan.imkbhisseveendeksler.controllers.StocksController;
import com.hakan.imkbhisseveendeksler.databinding.ActivityMainBinding;
import com.hakan.imkbhisseveendeksler.databinding.ActivityStockDetailBinding;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.GraphicDatum;
import com.hakan.imkbhisseveendeksler.models.stocksdetail.responses.StocksDetailResponse;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

public class StockDetailActivity extends AppCompatActivity {

    public static int stockId;
    public static String stockID_AES;

    private ActivityStockDetailBinding binding;

    private KProgressHUD hud;

    private StocksController stocksController = StocksController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStockDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hud = KProgressHUD.create(StockDetailActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        stocksController.postStockDetail(stockID_AES, new StocksDetailCallback() {
            @Override
            public void onSuccess(StocksDetailResponse stocksDetailResponse) {
                List<Entry> entries = new ArrayList<Entry>();
                for (GraphicDatum data : stocksDetailResponse.getGraphicData()) {
                    entries.add(new Entry(data.getDay(), data.getValue().floatValue()));
                }

                LineDataSet set1 = new LineDataSet(entries, "Değerler");
                set1.setDrawIcons(false);

                set1.enableDashedLine(10f, 5f, 0f);

                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.BLACK);

                set1.setLineWidth(1f);
                set1.setCircleRadius(3f);

                set1.setDrawCircleHole(false);

                set1.setFormLineWidth(1f);
                set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                set1.setFormSize(15.f);

                set1.setValueTextSize(9f);

                set1.enableDashedHighlightLine(10f, 5f, 0f);

                set1.setDrawFilled(true);
                set1.setFillFormatter(new IFillFormatter() {
                    @Override
                    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                        return binding.chart.getAxisLeft().getAxisMinimum();
                    }
                });

                if (Utils.getSDKInt() >= 18) {
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.fade_red);
                    set1.setFillDrawable(drawable);
                } else {
                    set1.setFillColor(Color.BLACK);
                }

                LineData lineData = new LineData(set1);
                binding.chart.setBackgroundColor(Color.WHITE);

                binding.chart.getDescription().setEnabled(false);

                TextView xAxisName = new TextView(getApplicationContext());
                xAxisName.setText("Günler");
                xAxisName.setX(binding.textViewOffer.getX());
                xAxisName.setY(binding.textViewOffer.getY());

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
                params.setMargins(0, 30, 0, 0);

                addContentView(xAxisName, params);

                VerticalTextView yAxisName = new VerticalTextView(getApplicationContext(),null);
                yAxisName.setText("Değerler");
                yAxisName.setGravity(Gravity.BOTTOM);
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                params2.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;

                addContentView(yAxisName, params2);

                binding.chart.setTouchEnabled(true);

                binding.chart.setDrawGridBackground(false);

                MyMarkerView mv = new MyMarkerView(getApplicationContext(), R.layout.custom_marker_view);

                mv.setChartView(binding.chart);
                binding.chart.setMarker(mv);

                binding.chart.setDragEnabled(true);
                binding.chart.setScaleEnabled(true);

                binding.chart.setPinchZoom(true);

                binding.chart.animateX(1500);

                binding.chart.setData(lineData);
                binding.chart.invalidate();

                binding.textViewBid.setText(getString(R.string.stockDetailBid) + " " + stocksDetailResponse.getBid());
                binding.textViewChange.setText(getString(R.string.stockDetailChange) + " " + stocksDetailResponse.getChannge());
                binding.textViewDifference.setText(getString(R.string.stockDetailDifference) + " " + stocksDetailResponse.getDifference());
                binding.textViewOffer.setText(getString(R.string.stockDetailOffer) + " " + stocksDetailResponse.getOffer());
                binding.textViewHighest.setText(getString(R.string.stockDetailHighest) + " " + stocksDetailResponse.getHighest());
                binding.textViewLowest.setText(getString(R.string.stockDetailLowest) + " " + stocksDetailResponse.getLowest());
                binding.textViewMaximum.setText(getString(R.string.stockDetailMaximum) + " " + stocksDetailResponse.getMaximum());
                binding.textViewMinimum.setText(getString(R.string.stockDetailMinimum) + " " + stocksDetailResponse.getMinimum());
                binding.textViewPrice.setText(getString(R.string.stockDetailPrice) + " " + stocksDetailResponse.getPrice());
                binding.textViewVolume.setText(getString(R.string.stockDetailVolume) + " " + stocksDetailResponse.getVolume());

                if (stocksDetailResponse.getIsDown()) {
                    binding.imageViewDegisim.setImageDrawable(getResources().getDrawable(R.drawable.ic_down_arrow));
                }

                try {
                    binding.textViewSymbol.setText(getString(R.string.stockDetailSymbol) + " " + StringEncrypt.decrypt(Globals.AES_KEY, Globals.AES_IVX, stocksDetailResponse.getSymbol()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                hud.dismiss();
            }

            @Override
            public void onError() {
                hud.dismiss();
                BaseUtils.showToast(getApplicationContext(), "StockDetail başarısız.");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}