package id.ac.ui.ft.personalizedobdscan.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.BrakingRecommendationRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.LoginRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.RegisterRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.AccuSystemResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterStatisticResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingRecommendationResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingStatisticResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.EmissionSystemResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.EmissionTripResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelConsSecResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelSystemResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.LoginResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.MLResultResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.OilSystemResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationRepository {
    private APIService apiService;

    private static class SingletonHelper
    {
        private static final ApplicationRepository INSTANCE = new ApplicationRepository();
    }

    public static ApplicationRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ApplicationRepository() {
        apiService = RetrofitBuilder.retrofit.create(APIService.class);
    }

    public LiveData<BaseResponse<LoginResponse>> login(final LoginRequest request) {
        final MutableLiveData<BaseResponse<LoginResponse>> data = new MutableLiveData<>();

        apiService.login(request).enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<RegisterResponse>> register(final RegisterRequest request) {
        final MutableLiveData<BaseResponse<RegisterResponse>> data = new MutableLiveData<>();

        apiService.register(request).enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<BrakingStatisticResponse>> brakingStatistic(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<BrakingStatisticResponse>> data = new MutableLiveData<>();

        apiService.getBrakingStatisticData(request).enqueue(new Callback<BaseResponse<BrakingStatisticResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakingStatisticResponse>> call, Response<BaseResponse<BrakingStatisticResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakingStatisticResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<BrakingSummaryResponse>> brakingSummary(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<BrakingSummaryResponse>> data = new MutableLiveData<>();

        apiService.getBrakingSummaryData(request).enqueue(new Callback<BaseResponse<BrakingSummaryResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakingSummaryResponse>> call, Response<BaseResponse<BrakingSummaryResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakingSummaryResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<BrakingRecommendationResponse>> brakingSummary(final BrakingRecommendationRequest request) {
        final MutableLiveData<BaseResponse<BrakingRecommendationResponse>> data = new MutableLiveData<>();

        apiService.getBrakingSummaryData(request).enqueue(new Callback<BaseResponse<BrakingRecommendationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakingRecommendationResponse>> call, Response<BaseResponse<BrakingRecommendationResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakingRecommendationResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<FuelSystemResponse>> fuelSystemAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<FuelSystemResponse>> data = new MutableLiveData<>();

        apiService.getFuelSystemData(request).enqueue(new Callback<BaseResponse<FuelSystemResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<FuelSystemResponse>> call, Response<BaseResponse<FuelSystemResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<FuelSystemResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<FuelConsResponse>> fuelConsAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<FuelConsResponse>> data = new MutableLiveData<>();

        apiService.getFuelConsData(request).enqueue(new Callback<BaseResponse<FuelConsResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<FuelConsResponse>> call, Response<BaseResponse<FuelConsResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<FuelConsResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<FuelConsSecResponse>> fuelConsSecAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<FuelConsSecResponse>> data = new MutableLiveData<>();

        apiService.getFuelConsSecData(request).enqueue(new Callback<BaseResponse<FuelConsSecResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<FuelConsSecResponse>> call, Response<BaseResponse<FuelConsSecResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<FuelConsSecResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


    public LiveData<BaseResponse<AirFilterSummaryResponse>> airFilterSummary(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<AirFilterSummaryResponse>> data = new MutableLiveData<>();

        apiService.getAirFilterData(request).enqueue(new Callback<BaseResponse<AirFilterSummaryResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<AirFilterSummaryResponse>> call, Response<BaseResponse<AirFilterSummaryResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AirFilterSummaryResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<AirFilterStatisticResponse>> airFilterStatistic(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<AirFilterStatisticResponse>> data = new MutableLiveData<>();

        apiService.getAirFilterMonthlyData(request).enqueue(new Callback<BaseResponse<AirFilterStatisticResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<AirFilterStatisticResponse>> call, Response<BaseResponse<AirFilterStatisticResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AirFilterStatisticResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<EmissionSystemResponse>> emissionSystemAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<EmissionSystemResponse>> data = new MutableLiveData<>();

        apiService.getEmissionSystemData(request).enqueue(new Callback<BaseResponse<EmissionSystemResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<EmissionSystemResponse>> call, Response<BaseResponse<EmissionSystemResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<EmissionSystemResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<EmissionTripResponse>> emissionTripAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<EmissionTripResponse>> data = new MutableLiveData<>();

        apiService.getEmissionTripData(request).enqueue(new Callback<BaseResponse<EmissionTripResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<EmissionTripResponse>> call, Response<BaseResponse<EmissionTripResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<EmissionTripResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<OilSystemResponse>> oilSystemAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<OilSystemResponse>> data = new MutableLiveData<>();

        apiService.getOilSystemData(request).enqueue(new Callback<BaseResponse<OilSystemResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<OilSystemResponse>> call, Response<BaseResponse<OilSystemResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<OilSystemResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<AccuSystemResponse>> accuSystemAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<AccuSystemResponse>> data = new MutableLiveData<>();

        apiService.getAccuSystemData(request).enqueue(new Callback<BaseResponse<AccuSystemResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<AccuSystemResponse>> call, Response<BaseResponse<AccuSystemResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AccuSystemResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<MLResultResponse>> mlResultStatistic(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<MLResultResponse>> data = new MutableLiveData<>();

        apiService.getMLResult(request).enqueue(new Callback<BaseResponse<MLResultResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<MLResultResponse>> call, Response<BaseResponse<MLResultResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MLResultResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}

