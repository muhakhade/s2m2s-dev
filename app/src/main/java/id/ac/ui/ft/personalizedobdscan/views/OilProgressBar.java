package id.ac.ui.ft.personalizedobdscan.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.math.BigDecimal;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.util.Percent;

public class OilProgressBar extends AppCompatImageView {

    private static final BigDecimal MAX = BigDecimal.valueOf(10000);

    public OilProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageResource(R.drawable.oil_cliped);
    }

    public void setCurrentValue(Percent percent){
        int cliDrawableImageLevel = percent.asBigDecimal().multiply(MAX).intValue();
        setImageLevel(cliDrawableImageLevel);
    }
}
