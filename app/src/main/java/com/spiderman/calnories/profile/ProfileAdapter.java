package com.spiderman.calnories.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spiderman.calnories.R;
import com.spiderman.calnories.category.CategoryAdapter;
import com.spiderman.calnories.data.DummyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Biekaeksa on 4/16/2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private Context context;
    private List<DummyModel> list;

    public ProfileAdapter(Context context, List<DummyModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_grid_item, null);
        return new ProfileAdapter.ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        DummyModel model = list.get(position);
        holder.txtCalorie.setText(model.getGridCalorie()+ " kkal");
        holder.txtTarget.setText(model.getGridDummy());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_calorie)
        TextView txtCalorie;
        @BindView(R.id.profile_target)
        TextView txtTarget;
        public ProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void replaceData(List<DummyModel> dummyModels){
        this.list = dummyModels;
        notifyDataSetChanged();
    }
}
