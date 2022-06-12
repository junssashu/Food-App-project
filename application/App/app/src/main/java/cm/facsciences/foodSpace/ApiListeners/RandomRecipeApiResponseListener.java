package cm.facsciences.foodSpace.ApiListeners;

public interface RandomRecipeApiResponseListener {

    void didFetch(cm.facsciences.foodSpace.ApiTools.RandomRecipeApiResponse response, String message);
    void didError(String message);
}
