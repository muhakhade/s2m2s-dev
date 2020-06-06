package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelConsResponse {
    @SerializedName("behaviour_condition")
    @Expose
    private String behaviourCondition;
    @SerializedName("avg_cons")
    @Expose
    private Double avgCons;
    @SerializedName("rt_cons")
    @Expose
    private Double rtCons;
    @SerializedName("rt_rpm")
    @Expose
    private Double rtRpm;
    @SerializedName("rt_speed")
    @Expose
    private Double rtSpeed;
    @SerializedName("rt_accelpos")
    @Expose
    private Double rtAccelPos;

    public String getBehaviourCondition() {
        return behaviourCondition;
    }

    public void setBehaviourCondition(String behaviourCondition) {
        this.behaviourCondition = behaviourCondition;
    }

    public Double getAvgCons() {
        return avgCons;
    }

    public void setAvgCons(Double avgCons) {
        this.avgCons = avgCons;
    }

    public Double getRtCons() {
        return rtCons;
    }

    public void setRtCons(Double rtCons) {
        this.rtCons = rtCons;
    }

    public Double getRtRpm() {
        return rtRpm;
    }

    public void setRtRpm(Double rtRpm) {
        this.rtRpm = rtRpm;
    }

    public Double getRtSpeed() {
        return rtSpeed;
    }

    public void setRtSpeed(Double rtSpeed) {
        this.rtSpeed = rtSpeed;
    }

    public Double getRtAccelPos() {
        return rtAccelPos;
    }

    public void setRtAccelPos(Double rtAccelPos) {
        this.rtAccelPos = rtAccelPos;
    }
}
