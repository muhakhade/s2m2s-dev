package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MLResultResponse {
    @SerializedName("predict_value")
    @Expose
    private Double predictValue;
    @SerializedName("difference")
    @Expose
    private Double Diff;

    public Double getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(Double predictValue) {
        this.predictValue = predictValue;
    }

    public Double getDiff() {
        return Diff;
    }

    public void setDiff(Double Diff) { this.Diff = Diff; }

}
