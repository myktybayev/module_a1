package kz.worldskills.a107.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.worldskills.a107.R;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private List<Item> itemList;
    private List<Item> filteredList;
    Context context;

    public CatalogAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.filteredList = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false);
        return new CatalogAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.ViewHolder holder, int position) {
        Item item = filteredList.get(position);
        holder.title.setText(item.title);
        holder.description.setText(item.desc);
        holder.imageView.setImageResource(item.suretId);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("title", item.title);
                detailIntent.putExtra("description", item.desc);
                detailIntent.putExtra("imageView", item.suretId);
                detailIntent.putExtra("baga", item.baga);
                context.startActivity(detailIntent);
            }
        });
        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("title", item.title);
                detailIntent.putExtra("description", item.desc);
                detailIntent.putExtra("imageView", item.suretId);
                detailIntent.putExtra("baga", item.baga);
                context.startActivity(detailIntent);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("title", item.title);
                detailIntent.putExtra("description", item.desc);
                detailIntent.putExtra("imageView", item.suretId);
                detailIntent.putExtra("baga", item.baga);
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(itemList);
        } else {
            for (Item item : itemList) {
                if (item.title.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.suretId);
            title = itemView.findViewById(R.id.titleTv);
            description = itemView.findViewById(R.id.descTv);
        }
    }
}
