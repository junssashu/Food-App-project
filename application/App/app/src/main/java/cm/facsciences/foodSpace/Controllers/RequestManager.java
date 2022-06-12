package cm.facsciences.foodSpace.Controllers;

import android.content.Context;

<<<<<<< HEAD
import cm.facsciences.foodSpace.ApiListeners.RandomRecipeApiResponseListener;
import cm.facsciences.foodSpace.ApiTools.RandomRecipeApiResponse;
=======
import java.util.List;

import cm.facsciences.foodSpace.ApiListeners.RandomRecipeApiResponseListener;
import cm.facsciences.foodSpace.ApiListeners.RecipeDetailsListerner;
import cm.facsciences.foodSpace.ApiTools.RandomRecipeApiResponse;
import cm.facsciences.foodSpace.ApiTools.RecipeDetailsResponse;
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04
import cm.facsciences.foodSpace.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
<<<<<<< HEAD
=======
import retrofit2.http.Path;
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04
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

<<<<<<< HEAD
    public void getRandomRecipes(RandomRecipeApiResponseListener listener){

        callRandomRecipes callRandomRecipes = mRetrofit.create(callRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(mContext.getString(R.string.api_key),"10" );
=======
    public void getRandomRecipes(RandomRecipeApiResponseListener listener, List<String>tags){

        callRandomRecipes callRandomRecipes = mRetrofit.create(callRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(mContext.getString(R.string.api_key),"10" , tags);
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04
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
<<<<<<< HEAD
                @Query("number") String number
        );

    }
=======
                @Query("number") String number,
                @Query("tags") List<String> tags
        );

    }
    public void getRecipeDetails(RecipeDetailsListerner listerner, int id){
        callRecipeDetails callRecipeDetails = mRetrofit.create(RequestManager.callRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, mContext.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listerner.didError(response.message());
                    return;
                }
                listerner.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listerner.didError(t.getMessage());
            }
        });
    }

    private  interface  callRecipeDetails{
        @GET("recipes/{id}/information")
        Call <RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apikey") String apikey
        );
    }
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04
}
