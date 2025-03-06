package kz.worldskills.a107.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.content.Context;

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

//                    String drawableName = "airpods_pro2_1";
                    JSONArray imageArray = jsonObject.getJSONArray("images");
                    for (int j = 0; j < imageArray.length(); j++) {
                        String imgName = imageArray.getString(j);
                        imgName = imgName.substring(imgName.indexOf("/")+1,imgName.indexOf("."));
                        itemList.add(new Item(getDrawableId(getActivity(), imgName), title, desc, baga, 0));
                        break;
                    }
                }


            } catch (JSONException e) {

                Log.i("json_text", "e: " + e.toString());
                e.printStackTrace();
            }
        }

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

    public static int getDrawableId(Context context, String drawableName) {
        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }

}