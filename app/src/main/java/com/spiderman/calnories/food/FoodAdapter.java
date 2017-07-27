package com.spiderman.calnories.food;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spiderman.calnories.R;
import com.spiderman.calnories.data.FoodModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Biekaeksa on 4/15/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHelper> implements Filterable {
    private Context context;
    private List<FoodModel> list;
    private List<FoodModel> mList;
    private FoodAdapter.FoodListener foodListener;

    public FoodAdapter(Context context, List<FoodModel> list, FoodListener foodListener) {
        this.context = context;
        this.foodListener = foodListener;
        this.mList = list;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mList = list;
                } else {
                    ArrayList<FoodModel> filterList = new ArrayList<>();
                    for (FoodModel foodModel : list) {
                        {
                            if (foodModel.getFood_name().toLowerCase().contains(charString) ||
                                    foodModel.getFood_name().contains(charString) ||
                                    foodModel.getFood_name().toUpperCase().contains(charString)) {
                                filterList.add(foodModel);
                            }

                        }
                    }
                    mList = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mList = (ArrayList<FoodModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class FoodViewHelper extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.food_textview_calories)
        TextView txtCalorie;
        @BindView(R.id.food_textview_nama_makanan)
        TextView txtName;
        @BindView(R.id.food_imageview_foto)
        ImageView imgFood;
        @BindView(R.id.food_cardview)
        CardView cardView;

        public FoodViewHelper(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            foodListener.onItemClick(mList.get(getAdapterPosition()).getId_food(),
                    mList.get(getAdapterPosition()).getFood_name(),
                    mList.get(getAdapterPosition()).getPhoto_url(),
                    mList.get(getAdapterPosition()).getCalories_food(),
                    mList.get(getAdapterPosition()).getReceipt(),
                    mList.get(getAdapterPosition()).getHow_to_make());
        }
    }


    @Override
    public FoodViewHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_food_row_item, null);
        return new FoodViewHelper(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHelper holder, int position) {
        FoodModel foodModel = mList.get(position);
        holder.txtName.setText(foodModel.getFood_name());
        holder.txtCalorie.setText(String.valueOf(foodModel.getCalories_food()) + " kkal");
        Glide.with(context)
                .load(foodModel.getPhoto_url())
                .into(holder.imgFood);

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void replaceData(List<FoodModel> foodModels) {
        this.list = foodModels;
        this.mList = foodModels;
        notifyDataSetChanged();
    }

    public interface FoodListener {
        void onItemClick(String id, String makanan, String photo, Float kalori, String resep,
                         String pembuatan);
    }
}
