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

import java.util.Locale;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityAccuSystemBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.AccuSystemResponse;
import id.ac.ui.ft.personalizedobdscan.util.Percent;
import id.ac.ui.ft.personalizedobdscan.viewmodels.AccuSystemViewModel;

public class AccuSystemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityAccuSystemBinding binding;
    private AccuSystemViewModel viewModel;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_accu_system);
        binding.batteryAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRefresh() {
        getAccuAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initAccuImage();
        getAccuAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AccuSystemViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initAccuImage() {
        setAccuValue(new Percent(0));
    }

    private void getAccuAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.accuSystemAnalysis(email).
                observe(this, new Observer<BaseResponse<AccuSystemResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<AccuSystemResponse> response) {
                        binding.batteryAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                setAccuAnalysis(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void setAccuAnalysis(AccuSystemResponse response) {
        int accu_life = response.getAccuLife().intValue();
        int data_volt = response.getDataVolt().intValue();

        setAccuValue(new Percent(accu_life));

        binding.tvAccuPercentage.setText(String.format(Locale.US, "%d%%", accu_life));
        binding.tvAccuVolt.setText(String.format(Locale.US, "%d V", data_volt));
        if (accu_life < 60) {
            binding.tvAccuChange.setText(R.string.tv_accu_suggestion_replace);
        } else {
            if (data_volt < 12.6) {
                binding.tvAccuChange.setText(R.string.tv_accu_suggestion_charge);
            } else {
                binding.tvAccuChange.setText(R.string.tv_accu_suggestion_normal);
            }
        }
    }

    private void setAccuValue(Percent value) {
        binding.batteryProgressBar.setCurrentValue(value);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
