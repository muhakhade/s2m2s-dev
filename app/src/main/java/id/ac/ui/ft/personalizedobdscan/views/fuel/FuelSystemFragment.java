package id.ac.ui.ft.personalizedobdscan.views.fuel;

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

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.FragmentFuelSystemBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelSystemResponse;
import id.ac.ui.ft.personalizedobdscan.util.FuelSystemXAxisFormatter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.fuelsystem.FuelSystemViewModel;

public class FuelSystemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentFuelSystemBinding binding;
    private FuelSystemViewModel viewModel;
    private SharedPreferences mPrefs;

    private BarChart fuelCostChart;
    private BarChart tripCostChart;

    public static FuelSystemFragment newInstance() {
        return new FuelSystemFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_fuel_system,
                container,
                false
        );
        binding.fuelAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        mPrefs = getActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        initComponent();

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onRefresh() {
        getFuelAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initChart();
        getFuelAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(FuelSystemViewModel.class);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initChart() {
        fuelCostChart = binding.barChartFuelCost;
        tripCostChart = binding.barChartTripCost;

        fuelCostChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fuelCostChart.getXAxis().setGranularityEnabled(true);
        fuelCostChart.getXAxis().setGranularity(1f);
        fuelCostChart.getDescription().setEnabled(false);
        fuelCostChart.setFitBars(true);

        tripCostChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        tripCostChart.getXAxis().setGranularityEnabled(true);
        tripCostChart.getXAxis().setGranularity(1f);
        tripCostChart.getDescription().setEnabled(false);
        tripCostChart.setFitBars(true);
    }

    private void getFuelAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.fuelSystemAnalysis(email).
                observe(this, new Observer<BaseResponse<FuelSystemResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<FuelSystemResponse> response) {
                        binding.fuelAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initFuelCostLineChart(response.getData());
                                initTripCostLineChart(response.getData());
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initFuelCostLineChart(List<FuelSystemResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelSystemResponse e : responses) {
            entries.add(new BarEntry(idx, e.getFuelCost().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_fuel_cost));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        fuelCostChart.getXAxis().setValueFormatter(new FuelSystemXAxisFormatter());
        fuelCostChart.setData(data);
        fuelCostChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initTripCostLineChart(List<FuelSystemResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelSystemResponse e : responses) {
            entries.add(new BarEntry(idx, e.getTripCost().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_trip_cost));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        tripCostChart.getXAxis().setValueFormatter(new FuelSystemXAxisFormatter());
        tripCostChart.setData(data);
        tripCostChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
