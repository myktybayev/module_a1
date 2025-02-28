package kz.worldskills.a107.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kz.worldskills.a107.MainActivity;
import kz.worldskills.a107.R;
import kz.worldskills.a107.ui.authen.LoadJSONAsset;
import kz.worldskills.a107.ui.authen.LoginActivity;

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

        String jsonString = LoadJSONAsset.loadJSONFromAsset(getActivity(), "store.json");
        if (jsonString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String title = jsonObject.getString("name");
                    String desc = jsonObject.getString("desc");
                    int baga = jsonObject.getInt("price");

                    itemList.add(new Item(R.drawable.ic_notifications_black_24dp, title, desc, baga, 0));


                }


            } catch (JSONException e) {

                Log.i("json_text", "e: " + e.toString());
                e.printStackTrace();
            }
        }

//        itemList.add(new Item(R.drawable.ic_notifications_black_24dp, "Laptop", "Ноутбук описание", 200000, 0));
//        itemList.add(new Item(R.drawable.ic_catalog, "MacBook", "Apple Macbook", 1800000, 0));
//        itemList.add(new Item(R.drawable.ic_catalog, "Phone", "Телефон описание", 300000, 0));
//        itemList.add(new Item(R.drawable.ic_account, "Car", "Машина описание", 240000, 0));
//        itemList.add(new Item(R.drawable.ic_korzinka, "Car1", "Мышка описание", 250000, 0));
//        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка2", "Мышка описание", 150000, 0));
//        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка3", "Мышка описание", 100000, 0));
//        itemList.add(new Item(R.drawable.ic_korzinka, "Мышка", "Мышка описание", 12000, 0));

        catalogAdapter = new CatalogAdapter(getActivity(), itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(catalogAdapter);

//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                catalogAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                catalogAdapter.filter(newText);
                return false;
            }
        });

        return view;
    }

}