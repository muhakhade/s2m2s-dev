package id.ac.ui.ft.personalizedobdscan.viewmodels.fuelsystem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsSecResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class FuelConsumptionViewModel extends AndroidViewModel {
    public FuelConsumptionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<FuelConsResponse>> fuelConsAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().fuelConsAnalysis(request);
    }

    public LiveData<BaseResponse<FuelConsSecResponse>> fuelConsSecAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().fuelConsSecAnalysis(request);
    }
}
