package cm.facsciences.foodSpace.Controllers;

import android.content.Context;

import cm.facsciences.foodSpace.ApiListeners.RandomRecipeApiResponseListener;
import cm.facsciences.foodSpace.ApiTools.RandomRecipeApiResponse;
import cm.facsciences.foodSpace.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context mContext;
    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        mContext = context;
    }

    public void getRandomRecipes(RandomRecipeApiResponseListener listener){

        callRandomRecipes callRandomRecipes = mRetrofit.create(callRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(mContext.getString(R.string.api_key),"10" );
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private  interface  callRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );

    }
}
