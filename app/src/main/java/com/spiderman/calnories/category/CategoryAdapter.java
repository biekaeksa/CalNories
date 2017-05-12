package com.spiderman.calnories.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spiderman.calnories.R;
import com.spiderman.calnories.data.CategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Biekaeksa on 4/16/2017.
 */

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHelper>{
    private Context context;
    private List<CategoryModel> list;
    private CategoryAdapter.CategoryListener listener;

    public CategoryAdapter(Context context, List<CategoryModel> list, CategoryListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public class CategoryViewHelper extends RecyclerView.ViewHolder implements View.OnClickListener  {
        @BindView(R.id.category_name)
        TextView txtCategory;
        @BindView(R.id.imgIcon)
        ImageView imgIcon;

        public CategoryViewHelper(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickCategory(view, getPosition());
        }
    }

    @Override
    public CategoryViewHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_category_row_item, null);
        return new CategoryAdapter.CategoryViewHelper(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHelper holder, int position) {
        CategoryModel model = list.get(position);
        holder.txtCategory.setText(model.getKategori());
        Glide.with(context)
                .load(model.getIcon())
                .into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface CategoryListener{
        void onClickCategory(View view, int position);
    }

    public void replaceData(List<CategoryModel> categoryModels){
        this.list = categoryModels;
        notifyDataSetChanged();
    }

}
