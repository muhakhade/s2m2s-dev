package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccuSystemResponse {
    @SerializedName("accu_life")
    @Expose
    private Double accuLife;
    @SerializedName("volt1")
    @Expose
    private Double dataVolt;
    @SerializedName("accu_volt")
    @Expose
    private Double accuVolt;

    public Double getAccuLife() {
        return accuLife;
    }

    public void setAccuLife(Double accuLife) {
        this.accuLife = accuLife;
    }

    public Double getDataVolt() {
        return dataVolt;
    }

    public void setDataVolt(Double dataVolt) {
        this.dataVolt = dataVolt;
    }

    public Double getAccuVolt() {
        return accuVolt;
    }

    public void setAccuVolt(Double accuVolt) {
        this.accuVolt = accuVolt;
    }

}
