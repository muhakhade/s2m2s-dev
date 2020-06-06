package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.EmissionSystemResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.EmissionTripResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class EmissionSystemViewModel extends AndroidViewModel {
    public EmissionSystemViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<EmissionSystemResponse>> emissionSystemAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().emissionSystemAnalysis(request);
    }

    public LiveData<BaseResponse<EmissionTripResponse>> emissionTripAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().emissionTripAnalysis(request);
    }

}
