package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmissionSystemResponse {
    @SerializedName("co2persec")
    @Expose
    private Double co2Persec;

    public Double getCo2Persec() {
        return co2Persec;
    }

    public void setCo2Persec(Double co2Persec) {
        this.co2Persec = co2Persec;
    }

}
