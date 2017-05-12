package com.spiderman.calnories.food;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spiderman.calnories.R;
import com.spiderman.calnories.data.FoodModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Biekaeksa on 4/15/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHelper> {
    private Context context;
    private List<FoodModel> list;
    private FoodAdapter.FoodListener  foodListener;

    public FoodAdapter(Context context, List<FoodModel> list, FoodListener foodListener) {
        this.context = context;
        this.list = list;
        this.foodListener = foodListener;
    }

    public class FoodViewHelper extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.food_calorie)
        TextView txtCalorie;
        @BindView(R.id.food_name)
        TextView txtName;
        @BindView(R.id.food_imageview)
        ImageView imgFood;
        @BindView(R.id.btnInfo)
        ImageButton btnInfo;

        public FoodViewHelper(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnInfo.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public FoodAdapter.FoodViewHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_food_row_item, null);
        return new FoodAdapter.FoodViewHelper(view);
    }

    @Override
    public void onBindViewHolder(final FoodAdapter.FoodViewHelper holder, int position) {
        FoodModel foodModel = list.get(position);
        holder.txtCalorie.setText(String.valueOf(foodModel.getCalories_food()) + " kkal");
        holder.txtName.setText(foodModel.getFood_name());
        Glide.with(context)
                .load(foodModel.getPhoto_url())
                .error(ContextCompat.getDrawable(context, R.drawable.ic_circle))
                .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_circle))
                .into(holder.imgFood);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void replaceData(List<FoodModel> foodModels){
        this.list = foodModels;
        notifyDataSetChanged();
    }

    public interface FoodListener{
        void onInfoFoodClick();
    }
}
