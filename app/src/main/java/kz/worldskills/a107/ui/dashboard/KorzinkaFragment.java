package kz.worldskills.a107.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.worldskills.a107.R;
import kz.worldskills.a107.ui.home.CatalogAdapter;
import kz.worldskills.a107.ui.home.Item;

public class KorzinkaFragment extends Fragment {

    private View view;
    LinearLayout layout1, layout2;
    Button btnCatalog;

    RecyclerView recyclerView;
    KorzinkaAdapter catalogAdapter;
    List<Item> itemList;
    ImageView tv_trash;
    static TextView tvSumma;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        btnCatalog = view.findViewById(R.id.btnCatalog);
        tv_trash = view.findViewById(R.id.tv_trash);
        tvSumma = view.findViewById(R.id.tvSumma);

        recyclerView = view.findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();

        itemList.add(new Item(R.drawable.ic_notifications_black_24dp, "Laptop", "Ноутбук описание", 5, 1));
        itemList.add(new Item(R.drawable.ic_catalog, "MacBook", "Apple Macbook", 10, 2));
        itemList.add(new Item(R.drawable.ic_catalog, "Phone", "Телефон описание", 15, 1));

        int summa = 0;
        for(Item item: itemList){
            summa = summa + (item.baga * item.count);
        }

        tvSumma.setText(summa+" т");

        catalogAdapter = new KorzinkaAdapter(getActivity(), itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(catalogAdapter);


        btnCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                tv_trash.setVisibility(View.VISIBLE);
            }
        });


        tv_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout2.setVisibility(View.GONE);
                tv_trash.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
            }
        });



        return view;
    }

    public static void changeSumma(int summa){
        tvSumma.setText(summa+" т");
    }

}