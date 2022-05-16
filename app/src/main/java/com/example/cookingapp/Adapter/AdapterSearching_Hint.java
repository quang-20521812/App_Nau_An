package com.example.cookingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookingapp.R;

import java.util.List;

public class AdapterSearching_Hint extends RecyclerView.Adapter<AdapterSearching_Hint.ItemViewHolder> {
    private List<String> listHints;
    private Context mContext;

    public AdapterSearching_Hint(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> listHints) {
        this.listHints = listHints;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterSearching_Hint.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_hint_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.textViewDisplay.setText(listHints.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ...
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listHints != null) {
            return listHints.size();
        }

        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDisplay;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDisplay = itemView.findViewById(R.id.textViewSearchingItem);
        }
    }
}