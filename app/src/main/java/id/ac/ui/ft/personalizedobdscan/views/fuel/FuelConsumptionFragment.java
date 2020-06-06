package id.ac.ui.ft.personalizedobdscan.views.fuel;

import android.animation.LayoutTransition;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import id.ac.ui.ft.personalizedobdscan.databinding.FragmentFuelConsumptionBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsSecResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.util.FuelConsXAxisFormatter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.fuelsystem.FuelConsumptionViewModel;

public class FuelConsumptionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentFuelConsumptionBinding binding;
    private FuelConsumptionViewModel viewModel;

    private SharedPreferences mPrefs;

    private BarChart fuelConsRtChart;
    private BarChart fuelConsChart;
    private BarChart rpmRtChart;
    private BarChart speedRtChart;
    private BarChart accelPosRtChart;

    public static FuelConsumptionFragment newInstance() {
        return new FuelConsumptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_fuel_consumption,
                container,
                false
        );

        mPrefs = getActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding.fuelSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onRefresh() {
        getFuelConsumptionData();
        getFuelConsumptionSecData();
    }

    private void initComponent() {
        initViewModel();
        getFuelConsumptionData();
        getFuelConsumptionSecData();
        initConsChart();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(FuelConsumptionViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }


    private void initConsChart() {
        fuelConsRtChart = binding.barChartFuelConsRt;
        fuelConsChart = binding.barChartFuelCons;
        rpmRtChart = binding.barChartRpmRt;
        speedRtChart = binding.barChartSpeedRt;
        accelPosRtChart = binding.barChartAccelPosRt;

        fuelConsRtChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fuelConsRtChart.getXAxis().setGranularityEnabled(true);
        fuelConsRtChart.getXAxis().setGranularity(1f);
        fuelConsRtChart.getDescription().setEnabled(false);
        fuelConsRtChart.setFitBars(true);

        fuelConsChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fuelConsChart.getXAxis().setGranularityEnabled(true);
        fuelConsChart.getXAxis().setGranularity(1f);
        fuelConsChart.getDescription().setEnabled(false);
        fuelConsChart.setFitBars(true);

        rpmRtChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        rpmRtChart.getXAxis().setGranularityEnabled(true);
        rpmRtChart.getXAxis().setGranularity(1f);
        rpmRtChart.getDescription().setEnabled(false);
        rpmRtChart.setFitBars(true);

        speedRtChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        speedRtChart.getXAxis().setGranularityEnabled(true);
        speedRtChart.getXAxis().setGranularity(1f);
        speedRtChart.getDescription().setEnabled(false);
        speedRtChart.setFitBars(true);

        accelPosRtChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        accelPosRtChart.getXAxis().setGranularityEnabled(true);
        accelPosRtChart.getXAxis().setGranularity(1f);
        accelPosRtChart.getDescription().setEnabled(false);
        accelPosRtChart.setFitBars(true);
    }

    private void getFuelConsumptionData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.fuelConsAnalysis(email).
                observe(this, new Observer<BaseResponse<FuelConsResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<FuelConsResponse> response) {
                        binding.fuelSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initFuelConsLineChart(response.getData());
                                setFuelConsumptionAnalysis(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void getFuelConsumptionSecData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.fuelConsSecAnalysis(email).
                observe(this, new Observer<BaseResponse<FuelConsSecResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<FuelConsSecResponse> response) {
                        binding.fuelSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initFuelConsRtLineChart(response.getData());
                                initRpmRtLineChart(response.getData());
                                initSpeedRtLineChart(response.getData());
                                initAccelPosRtLineChart(response.getData());
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initFuelConsRtLineChart(List<FuelConsSecResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelConsSecResponse e : responses) {
            entries.add(new BarEntry(idx, e.getRtCons().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_fuel_cons_rt));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        fuelConsRtChart.getXAxis().setValueFormatter(new FuelConsXAxisFormatter());
        fuelConsRtChart.setData(data);
        fuelConsRtChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initFuelConsLineChart(List<FuelConsResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelConsResponse e : responses) {
            entries.add(new BarEntry(idx, e.getAvgCons().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_fuel_cons));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        fuelConsChart.getXAxis().setValueFormatter(new FuelConsXAxisFormatter());
        fuelConsChart.setData(data);
        fuelConsChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initRpmRtLineChart(List<FuelConsSecResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelConsSecResponse e : responses) {
            entries.add(new BarEntry(idx, e.getRtRpm().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_rpm_rt));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        rpmRtChart.getXAxis().setValueFormatter(new FuelConsXAxisFormatter());
        rpmRtChart.setData(data);
        rpmRtChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initSpeedRtLineChart(List<FuelConsSecResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelConsSecResponse e : responses) {
            entries.add(new BarEntry(idx, e.getRtSpeed().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_speed_rt));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        speedRtChart.getXAxis().setValueFormatter(new FuelConsXAxisFormatter());
        speedRtChart.setData(data);
        speedRtChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initAccelPosRtLineChart(List<FuelConsSecResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelConsSecResponse e : responses) {
            entries.add(new BarEntry(idx, e.getRtAccelPos().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_accel_pos_rt));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        accelPosRtChart.getXAxis().setValueFormatter(new FuelConsXAxisFormatter());
        accelPosRtChart.setData(data);
        accelPosRtChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void setFuelConsumptionAnalysis(FuelConsResponse response) {

        binding.tvDbCondition.setText(String.format(Locale.US, "%d%%", response.getBehaviourCondition()));

    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
