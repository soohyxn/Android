package kr.ac.duksung.advisor2home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfessorActivity extends AppCompatActivity {

    String [] professors = {"정원호","음두헌", "강남희", "임양미", "박태정"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        ListView professorList = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, professors);


        professorList.setAdapter(adapter);

        professorList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //8개의 셀 중 하나의 셀을 터치하면 감지하고 onItemClick실행됨
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), professors[i], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                intent.putExtra("professor", professors[i]);
                startActivity(intent);
            }
        });
    }
}

