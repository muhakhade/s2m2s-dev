package id.ac.ui.ft.personalizedobdscan.views.fuel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityFuelBinding;
import id.ac.ui.ft.personalizedobdscan.util.ViewPagerAdapter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.fuelsystem.FuelViewModel;

public class FuelActivity extends AppCompatActivity {
    private FuelViewModel viewModel;
    private ActivityFuelBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuel);
        setupViewPager(binding.pager);
        binding.tabs.setupWithViewPager(binding.pager);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initViewModel();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(FuelViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FuelConsumptionFragment.newInstance(), getString(R.string.tab_1_text_fuel));
        adapter.addFragment(FuelSystemFragment.newInstance(), getString(R.string.tab_2_text_fuel));
        viewPager.setAdapter(adapter);
    }
}

