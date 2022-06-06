package com.example.cookingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.ActivityMeoHay;
import com.example.cookingapp.Model.Tips;
import com.example.cookingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMeoHay extends RecyclerView.Adapter<AdapterMeoHay.ItemViewHolder> {
    private List<Tips> listTips;
    private Context mContext;

    public AdapterMeoHay(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Tips> listTips) {
        this.listTips = listTips;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterMeoHay.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meo_hay_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMeoHay.ItemViewHolder holder, int position) {
        Tips tips = listTips.get(position);

        if (tips == null) {
            return;
        }

        Picasso.get().load(tips.getTipURL()).into(holder.imageViewDisplay);
        holder.textViewDisplay.setText(tips.getTitle());

        // List item OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send data to ActivityMeoHay
                Intent intent = new Intent(mContext, ActivityMeoHay.class);
                intent.putExtra("tipTitle", tips.getTitle());
                intent.putExtra("tipDescription", tips.getDescription());
                intent.putExtra("tipURL", tips.getTipURL());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listTips != null) {
            return listTips.size();
        }

        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDisplay;
        TextView textViewDisplay;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewDisplay = itemView.findViewById(R.id.imageViewMeoHayDisplay);
            textViewDisplay = itemView.findViewById(R.id.textViewMeoHayDisplay);
        }
    }
}