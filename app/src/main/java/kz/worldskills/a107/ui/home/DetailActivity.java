package kz.worldskills.a107.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import kz.worldskills.a107.R;

public class DetailActivity extends AppCompatActivity {
    ImageView suretId;
    TextView titleTv, bagaTv, descTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        suretId = findViewById(R.id.suretId);
        titleTv = findViewById(R.id.titleTv);
        bagaTv = findViewById(R.id.bagaTv);
        descTv = findViewById(R.id.descTv);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int imageView =  intent.getIntExtra("imageView", 0);
        int baga =  intent.getIntExtra("baga", 0);

        suretId.setImageResource(imageView);
        titleTv.setText(title);
        descTv.setText(description);
        bagaTv.setText(""+baga);

        setTitle("Detail");
    }
}

/*

                detailIntent.putExtra("title", item.title);
                detailIntent.putExtra("description", item.desc);
                detailIntent.putExtra("imageView", item.suretId);
                baga
 */