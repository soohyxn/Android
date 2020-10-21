package kr.ac.duksung.movie;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = (TextView)findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button);

        Intent intent = getIntent();
        final String select = intent.getStringExtra("key");
        textView.setText(select + "선택!\n" + "추가 정보를 원하시면 More 버튼 클릭!");

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
