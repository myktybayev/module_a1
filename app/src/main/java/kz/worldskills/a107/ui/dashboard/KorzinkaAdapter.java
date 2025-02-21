package kz.worldskills.a107.ui.dashboard;

import static kz.worldskills.a107.ui.dashboard.KorzinkaFragment.changeSumma;

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
import kz.worldskills.a107.ui.home.Item;

public class KorzinkaAdapter extends RecyclerView.Adapter<KorzinkaAdapter.ViewHolder> {

    private List<Item> itemList;
    Context context;
    int plusCount = 0;
    int summa = 0;

    public KorzinkaAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public KorzinkaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_korzina, parent, false);
        return new KorzinkaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KorzinkaAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.title.setText(item.title);
        holder.bagaTv.setText(item.baga+" т");
        holder.imageView.setImageResource(item.suretId);
        holder.tv_shtuk.setText(""+item.count);

        summa = summa + (item.baga * item.count);

        if(item.count == 1){
            holder.iv_minus.setImageDrawable(context.getResources().getDrawable(R.drawable.delete_icon));
        }else{
            holder.iv_minus.setImageDrawable(context.getResources().getDrawable(R.drawable.minus_icon));
        }

        holder.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                summa = summa -

                if(item.count == 1){
                    itemList.remove(position);
                    notifyDataSetChanged();
                }else{
                    holder.tv_shtuk.setText(""+(item.count-1));
                    if(item.count-1 == 1){
                        holder.iv_minus.setImageDrawable(context.getResources().getDrawable(R.drawable.delete_icon));
                    }else{
                        holder.iv_minus.setImageDrawable(context.getResources().getDrawable(R.drawable.minus_icon));
                    }
                }

                summa = summa - (item.baga * item.count);

                changeSumma(summa);
            }
        });

        holder.iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plusCount++;
                holder.tv_shtuk.setText(""+(item.count+plusCount));

                summa = summa + (item.baga * item.count);

                if(item.count != 1){
                    holder.iv_minus.setImageDrawable(context.getResources().getDrawable(R.drawable.minus_icon));
                }

                changeSumma(summa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, iv_minus, iv_plus;
        TextView title, bagaTv, tv_shtuk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.suretId);
            iv_minus = itemView.findViewById(R.id.iv_minus);
            iv_plus = itemView.findViewById(R.id.iv_plus);
            title = itemView.findViewById(R.id.titleTv);
            bagaTv = itemView.findViewById(R.id.bagaTv);
            tv_shtuk = itemView.findViewById(R.id.tv_shtuk);
        }
    }
}
