package cm.facsciences.foodSpace.ApiAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cm.facsciences.foodSpace.ApiListeners.RecipeClickListener;
import cm.facsciences.foodSpace.ApiTools.Recipe;
import cm.facsciences.foodSpace.R;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {

    Context mContext;
    List<Recipe> list;
    RecipeClickListener clickListener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener clickListener ) {
        mContext = context;
        this.list = list;
        this.clickListener= clickListener;


    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_random_recipes, parent, false) );
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textview_title.setText(list.get(position).title);
        holder.textview_title.setSelected(true);
        holder.textview_likes.setText((list.get(position).aggregateLikes+" Likes"));
        holder.textview_likes.setText(list.get(position).servings+" Servings");
        holder.textview_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.ImageView_food);

        holder.Random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onRecipeclicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView Random_list_container;
    TextView textview_title, textview_servings, textview_time, textview_likes;
    ImageView ImageView_food;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        Random_list_container= itemView.findViewById(R.id.Random_list_container);
        textview_likes = itemView.findViewById(R.id.text_view_like);
        textview_servings =itemView.findViewById(R.id.text_view_servings);
        textview_time = itemView.findViewById(R.id.text_view_time);
        textview_title= itemView.findViewById(R.id.textview_title);
        ImageView_food= itemView.findViewById(R.id.imageview_food);

    }
}

