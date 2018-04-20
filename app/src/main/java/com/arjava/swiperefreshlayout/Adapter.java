package com.arjava.swiperefreshlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Model> modelList;
    private int rowLayout;
    private Context context;

    public Adapter(List<Model> sports, int item_rv, Context applicationContext) {
        this.modelList = sports;
        this.rowLayout = item_rv;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model result = modelList.get(position);

        Glide.with(context).load(result.getStrSportThumb()).into(holder.imageView);

        holder.textView.setText(result.getStrSport());

    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageSport);
            textView = itemView.findViewById(R.id.tvNameSport);

        }
    }
}
