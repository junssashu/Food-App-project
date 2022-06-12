package cm.facsciences.foodSpace.ApiListeners;

import cm.facsciences.foodSpace.ApiTools.RecipeDetailsResponse;

public interface RecipeDetailsListerner {

    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
