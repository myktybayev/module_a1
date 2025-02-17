package kz.worldskills.a107.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.worldskills.a107.R;

public class CatalogFragment extends Fragment {

    private View view;
    RecyclerView recyclerView;
    CatalogAdapter catalogAdapter;
    List<Item> itemList;
    SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.searchView);
        itemList = new ArrayList<>();

        itemList.add(new Item(R.drawable.ic_notifications_black_24dp, "Ноутбук", "Ноутбук описание", 200000));
        itemList.add(new Item(R.drawable.ic_catalog, "MacBook", "Apple Macbook", 1800000));
        itemList.add(new Item(R.drawable.ic_catalog, "Телефон", "Телефон описание", 300000));
        itemList.add(new Item(R.drawable.ic_account, "Машина", "Машина описание", 240000));
        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка1", "Мышка описание", 250000));
        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка2", "Мышка описание", 150000));
        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка3", "Мышка описание", 100000));
        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка", "Мышка описание", 12000));

        catalogAdapter = new CatalogAdapter(getActivity(), itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(catalogAdapter);

//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

}