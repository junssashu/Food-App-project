package cm.facsciences.foodSpace;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import cm.facsciences.foodSpace.ApiAdapters.IngredientsAdaptater;
import cm.facsciences.foodSpace.ApiListeners.RecipeDetailsListerner;
import cm.facsciences.foodSpace.ApiTools.RecipeDetailsResponse;
import cm.facsciences.foodSpace.Controllers.RequestManager;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    TextView text_View_meal_name;
    TextView text_View_meal_source, text_View_meal_summary;
    ImageView image_View_meal_image;
    RecyclerView recycler_meal_ingredients;
    RequestManager mRequestManager;
    ProgressDialog mDialog;
    IngredientsAdaptater mIngredientsAdaptater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        mRequestManager=  new RequestManager(this);
        mRequestManager.getRecipeDetails(recipedetailslistener, id);
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("Loading Details...");
        mDialog.show();
    }

    private void findViews() {

        text_View_meal_name = findViewById(R.id.text_View_meal_name);
                text_View_meal_source = findViewById(R.id.text_View_meal_source);
        text_View_meal_summary= findViewById(R.id.text_View_meal_summary);
                image_View_meal_image = findViewById((R.id.image_View_meal_image));
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
    }

    private  final RecipeDetailsListerner recipedetailslistener = new RecipeDetailsListerner() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            mDialog.dismiss();
            text_View_meal_name.setText(response.title);
            text_View_meal_source.setText(response.sourceName);
            text_View_meal_summary.setText((response.summary));
            Picasso.get().load(response.image).into(image_View_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager((new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false)));
            mIngredientsAdaptater = new IngredientsAdaptater(RecipeDetailsActivity.this, response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(mIngredientsAdaptater);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}