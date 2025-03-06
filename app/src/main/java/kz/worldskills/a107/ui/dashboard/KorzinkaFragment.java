package kz.worldskills.a107.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.worldskills.a107.R;
import kz.worldskills.a107.ui.home.CatalogAdapter;
import kz.worldskills.a107.ui.home.CatalogFragment;
import kz.worldskills.a107.ui.home.Item;

public class KorzinkaFragment extends Fragment {

    private View view;
    static LinearLayout layout1, layout2;
    Button btnCatalog;

    RecyclerView recyclerView;
    static KorzinkaAdapter catalogAdapter;
    static ImageView tv_trash;
    static TextView tvSumma;
    static ArrayList<Item> korzinkaList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        btnCatalog = view.findViewById(R.id.btnCatalog);
        tv_trash = view.findViewById(R.id.tv_trash);
        tvSumma = view.findViewById(R.id.tvSumma);

        recyclerView = view.findViewById(R.id.recyclerView);
        int summa = 0;



        //New Code

        if (korzinkaList.size() == 0) {
            korzinkaModeCleaned();
        } else {
            for (Item item : korzinkaList) {
                summa = summa + (item.baga * item.count);
            }
        }

        tvSumma.setText(summa + " т");

        catalogAdapter = new KorzinkaAdapter(getActivity(), korzinkaList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(catalogAdapter);


        btnCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });


        tv_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                korzinkaList.clear();
                korzinkaModeCleaned();
            }
        });


        return view;
    }

    public static void korzinkaModeCleaned() {
        layout2.setVisibility(View.GONE);
        tv_trash.setVisibility(View.GONE);
        layout1.setVisibility(View.VISIBLE);
    }

    public void korzinkaModeList() {

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        tv_trash.setVisibility(View.VISIBLE);
    }

    public static void changeSumma() {
        int summa = 0;
        for (Item item1 : korzinkaList) {
            summa += item1.baga * item1.count;
        }
        tvSumma.setText(summa + " т");

        if (korzinkaList.size() == 0) {
            korzinkaModeCleaned();
        }
    }

    public static void addToKorzinka(Item item) {
        korzinkaList.add(item);
        if (catalogAdapter != null)
            catalogAdapter.notifyDataSetChanged();

        for (Item item1 : korzinkaList) {
            Log.i("Korzinka", "added " + item1.title);

        }
    }

    public static ArrayList<Item> getKorzinka() {
        return korzinkaList;
    }
}