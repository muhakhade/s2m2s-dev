package id.ac.ui.ft.personalizedobdscan.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityEmissionSystemBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.EmissionSystemResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsResponse;
import id.ac.ui.ft.personalizedobdscan.util.EmissionSystemXAxisFormatter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.EmissionSystemViewModel;

public class EmissionSystemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityEmissionSystemBinding binding;
    private EmissionSystemViewModel viewModel;
    private SharedPreferences mPrefs;

    private BarChart co2PersecChart;
    private BarChart co2PertripChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_emission_system);
        binding.emissionAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRefresh() {
        getEmissionAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initChart();
        getEmissionAnalysisData();
        getDrivingBehaviourData();
    }

    private void initChart() {
        co2PersecChart = binding.barChartCo2Persec;
        co2PertripChart = binding.barChartCo2Pertrip;

        co2PersecChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        co2PersecChart.getXAxis().setGranularityEnabled(true);
        co2PersecChart.getXAxis().setGranularity(1f);
        co2PersecChart.getDescription().setEnabled(false);
        co2PersecChart.setFitBars(true);

        co2PertripChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        co2PertripChart.getXAxis().setGranularityEnabled(true);
        co2PertripChart.getXAxis().setGranularity(1f);
        co2PertripChart.getDescription().setEnabled(false);
        co2PertripChart.setFitBars(true);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EmissionSystemViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void getEmissionAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.emissionSystemAnalysis(email).
                observe(this, new Observer<BaseResponse<EmissionSystemResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<EmissionSystemResponse> response) {
                        binding.emissionAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initCo2PersecLineChart(response.getData());
                                initCo2PertripLineChart(response.getData());
                                setCo2Total(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void getDrivingBehaviourData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.fuelConsAnalysis(email).
                observe(this, new Observer<BaseResponse<FuelConsResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<FuelConsResponse> response) {
                        binding.emissionAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                setDrivingAnalysis(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initCo2PersecLineChart(List<EmissionSystemResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (EmissionSystemResponse e : responses) {
            entries.add(new BarEntry(idx, e.getCo2Persec().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_co2_persec));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        co2PersecChart.getXAxis().setValueFormatter(new EmissionSystemXAxisFormatter());
        co2PersecChart.setData(data);
        co2PersecChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initCo2PertripLineChart(List<EmissionSystemResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (EmissionSystemResponse e : responses) {
            entries.add(new BarEntry(idx, e.getAvgCo2().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_co2_trip));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        co2PertripChart.getXAxis().setValueFormatter(new EmissionSystemXAxisFormatter());
        co2PertripChart.setData(data);
        co2PertripChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void setDrivingAnalysis(FuelConsResponse response) {

        binding.tvDbCondition.setText(String.format(Locale.US, "%d%%", response.getBehaviourCondition()));

    }

    private void setCo2Total(EmissionSystemResponse response) {
        int co2_emitted = response.getTotalCo2().intValue();

        binding.tvTotalCo2Emitted.setText(String.format(Locale.US, "%d%%", co2_emitted));
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
