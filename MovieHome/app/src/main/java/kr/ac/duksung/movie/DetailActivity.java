package kr.ac.duksung.movie;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    Integer[] images = {R.drawable.meal0,R.drawable.meal1,R.drawable.meal2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = (TextView)findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button)findViewById(R.id.button);

        Intent intent = getIntent();
        Integer position = intent.getIntExtra("image", 0);
        final String select = intent.getStringExtra("key");
        textView.setText(select);
        imageView.setImageResource(images[position]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_WEB_SEARCH);
                intent1.putExtra(SearchManager.QUERY, select);
                startActivity(intent1);
            }
        });
    }
}
