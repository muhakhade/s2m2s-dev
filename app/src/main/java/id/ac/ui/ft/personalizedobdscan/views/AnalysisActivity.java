package id.ac.ui.ft.personalizedobdscan.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.views.airfilter.AirFilterActivity;
import id.ac.ui.ft.personalizedobdscan.views.braking.BrakingAnalysisActivity;
import id.ac.ui.ft.personalizedobdscan.views.fuel.FuelActivity;

public class AnalysisActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainGrid = findViewById(R.id.mainGrid);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initBrakingAnalysisCardView();
        initFuelSystemCardView();
        initAirFilterCardView();
        initOilCardView();
        initEmissionCardView();
        initAccuCardView();
    }

    private void initBrakingAnalysisCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(0);
        final Intent intent = new Intent(this, BrakingAnalysisActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initFuelSystemCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(1);
        final Intent intent = new Intent(this, FuelActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initAirFilterCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(2);
        final Intent intent = new Intent(this, AirFilterActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initOilCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(3);
        final Intent intent = new Intent(this, OilSystemActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initEmissionCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(4);
        final Intent intent = new Intent(this, EmissionSystemActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initAccuCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(5);
        final Intent intent = new Intent(this, AccuSystemActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
