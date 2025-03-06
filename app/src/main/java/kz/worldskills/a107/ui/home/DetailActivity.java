package kz.worldskills.a107.ui.home;

import static kz.worldskills.a107.ui.dashboard.KorzinkaFragment.addToKorzinka;
import static kz.worldskills.a107.ui.dashboard.KorzinkaFragment.getKorzinka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kz.worldskills.a107.R;

public class DetailActivity extends AppCompatActivity {
    ImageView suretId;
    TextView titleTv, bagaTv, descTv;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        suretId = findViewById(R.id.suretId);
        titleTv = findViewById(R.id.titleTv);
        bagaTv = findViewById(R.id.bagaTv);
        descTv = findViewById(R.id.descTv);
        btnSubmit = findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int imageView =  intent.getIntExtra("imageView", 0);
        int baga =  intent.getIntExtra("baga", 0);

        Item item = new Item(imageView, title , description , baga, 1);

        suretId.setImageResource(imageView);
        titleTv.setText(title);
        descTv.setText(description);
        bagaTv.setText(""+baga);

        ArrayList<Item> itemArrayList = getKorzinka();

        for (Item item1: itemArrayList) {
            if(item1.title.equals(item.title)){
                btnSubmit.setText("В корзине");
            }
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToKorzinka(item);
                finish();
            }
        });

        setTitle("Detail");
    }
}

/*

                detailIntent.putExtra("title", item.title);
                detailIntent.putExtra("description", item.desc);
                detailIntent.putExtra("imageView", item.suretId);
                baga
 */