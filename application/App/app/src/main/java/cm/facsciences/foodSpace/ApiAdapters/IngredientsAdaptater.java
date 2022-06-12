package cm.facsciences.foodSpace.ApiAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cm.facsciences.foodSpace.ApiTools.ExtendedIngredient;
import cm.facsciences.foodSpace.R;

public class IngredientsAdaptater extends RecyclerView.Adapter<IngredientViewholder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdaptater(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewholder(LayoutInflater.from((context)).inflate(R.layout.list_meal_ingredients,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewholder holder, int position) {
        holder.text_View_ingredient_name.setText(list.get(position).name);
        holder.text_View_ingredient_name.setSelected(true);
        holder.text_View_ingredient_quantity.setSelected(true);
        holder.text_View_ingredient_quantity.setText(list.get(position).original);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.image_View_ingredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientViewholder extends RecyclerView.ViewHolder{
    TextView text_View_ingredient_quantity,text_View_ingredient_name;
    ImageView image_View_ingredients;
    public IngredientViewholder(@NonNull View itemView) {
        super(itemView);
        text_View_ingredient_quantity = itemView.findViewById(R.id.text_View_ingredient_quantity);
        text_View_ingredient_name = itemView.findViewById(R.id.text_View_ingredient_name);
        image_View_ingredients = itemView.findViewById(R.id.image_View_ingredients);

    }
}
