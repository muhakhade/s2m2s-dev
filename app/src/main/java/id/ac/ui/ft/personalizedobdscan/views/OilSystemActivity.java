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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityOilSystemBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.OilSystemResponse;
import id.ac.ui.ft.personalizedobdscan.util.Percent;
import id.ac.ui.ft.personalizedobdscan.viewmodels.OilSystemViewModel;

public class OilSystemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityOilSystemBinding binding;
    private OilSystemViewModel viewModel;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_oil_system);
        binding.oilAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRefresh() {
        getOilAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initOlrImage();
        getOilAnalysisData();
    }

    private void initOlrImage() {
        setOlrValue(new Percent(0));
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(OilSystemViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void getOilAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.oilSystemAnalysis(email).
                observe(this, new Observer<BaseResponse<OilSystemResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<OilSystemResponse> response) {
                        binding.oilAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                setOilAnalysis(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void setOilAnalysis(OilSystemResponse response) {
        int condition_oil = response.getOlrLeft().intValue();

        int percent_condition = condition_oil/30;

        setOlrValue(new Percent(percent_condition));

        binding.tvOilCondition.setText(String.format(Locale.US, "%d%%", condition_oil));
        binding.tvOilChangeEstimationDay.setText(String.format(Locale.US, "%d hari", response.getEstimatedTimeOil().intValue()));

        binding.tvOilChangeEstimationKm.setText(String.format(Locale.US, "%d KM", response.getEstimatedDistanceOil().intValue()));
    }

    private void setOlrValue(Percent value) {
        binding.oilProgressBar.setCurrentValue(value);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
