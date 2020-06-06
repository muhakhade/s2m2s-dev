package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OilSystemResponse {
    @SerializedName("olr")
    @Expose
    private Double olrLeft;
    @SerializedName("olr_jarak")
    @Expose
    private Double estimatedTimeOil;
    @SerializedName("olr_waktu")
    @Expose
    private Double estimatedDistanceOil;

    public Double getOlrLeft() {
        return olrLeft;
    }

    public void setOlrLeft(Double olrLeft) {
        this.olrLeft = olrLeft;
    }

    public Double getEstimatedTimeOil() {
        return estimatedTimeOil;
    }

    public void setEstimatedTimeOil(Double estimatedTimeOil) {
        this.estimatedTimeOil = estimatedTimeOil;
    }

    public Double getEstimatedDistanceOil() {
        return estimatedDistanceOil;
    }

    public void setEstimatedDistanceOil(Double estimatedDistanceOil) {
        this.estimatedDistanceOil = estimatedDistanceOil;
    }
}
