package com.spiderman.calnories.sports;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spiderman.calnories.R;
import com.spiderman.calnories.data.SportsModel;
import com.spiderman.calnories.util.SessionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Biekaeksa on 7/2/2017.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHelper> implements Filterable {
    private Context context;
    private List<SportsModel> list;
    private List<SportsModel> mList;
    private SportsAdapter.SportsListener listener;

    public SportsAdapter(Context context, List<SportsModel> list, SportsListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public SportsViewHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_sports_row_item, null);
        return new SportsAdapter.SportsViewHelper(view);
    }

    @Override
    public void onBindViewHolder(SportsViewHelper holder, int position) {
        SportsModel model = list.get(position);
        holder.textViewNama.setText(model.getActivity_name());
        holder.textViewDuration.setText(String.valueOf(model.getDuration())+" menit");
        holder.textViewKalori.setText(String.valueOf(model.getCalories_burn())+ " kkal");
        Glide.with(context)
                .load(model.getPhoto_url())
                .into(holder.circleImageViewFoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    list = mList;
                } else {
                    ArrayList<SportsModel> filterList = new ArrayList<>();
                    for (SportsModel sportsModel : mList) {
                        {
                            if (sportsModel.getActivity_name().toLowerCase().contains(charString) ||
                                    sportsModel.getActivity_name().contains(charString) ||
                                    sportsModel.getActivity_name().toUpperCase().contains(charString)) {
                                filterList.add(sportsModel);
                            }

                        }
                    }
                    list = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<SportsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SportsViewHelper extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.sports_circleimageview_foto)
        CircleImageView circleImageViewFoto;
        @BindView(R.id.sports_imageview_add)
        ImageView imageViewAdd;
        @BindView(R.id.sports_textview_duration)
        TextView textViewDuration;
        @BindView(R.id.sports_textview_kalori)
        TextView textViewKalori;
        @BindView(R.id.sports_textview_nama)
        TextView textViewNama;
        public SportsViewHelper(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
            imageViewAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickAdd(SessionUtils.getLoggedUser(context).getIdUser(), list.get(getAdapterPosition()).getCalories_burn(),
                    list.get(getAdapterPosition()).getId_activity());
        }
    }

    public void replaceData(List<SportsModel> sportsModels) {
        this.list = sportsModels;
        this.mList = sportsModels;
        notifyDataSetChanged();
    }

    public interface SportsListener{
        void onClickAdd(String id, float kalori, String id_activity);
    }
}
