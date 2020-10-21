package kr.ac.duksung.dusthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AirActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    ListView listView;
    ArrayList<String> items;
    ArrayList<String> citynames;
    ArrayList<String> covalues;
    ArrayList<String> o3values;
    ArrayList<String> no2values;
    ArrayList<String> pm10values;
    ArrayList<String> pm25values;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        setTitle("서울 구별 미세먼지");

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView3);
        listView = findViewById(R.id.listView);

        items = new ArrayList<String>();
        citynames = new ArrayList<String>();
        covalues = new ArrayList<String>();
        o3values = new ArrayList<String>();
        no2values = new ArrayList<String>();
        pm10values = new ArrayList<String>();
        pm25values = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        makeRequest();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                intent.putExtra("cityname",citynames.get(i));
                intent.putExtra("co", covalues.get(i));
                intent.putExtra("o3", o3values.get(i));
                intent.putExtra("no2", no2values.get(i));
                intent.putExtra("pm10",pm10values.get(i));
                intent.putExtra("pm25",pm25values.get(i));

                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                textView.setText(null);
                items.clear();
                textView2.setText(null);
                makeRequest();
        }
        return false;
    }

    public void makeRequest() {
        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst" +
                "?serviceKey=LVPJEwy8mJVSUUVVOBFzarmxU8%2BdUwT9DxtE4pdFCvy0Q%2FEjWwMZkaJwkAxUGqPbvip1YTDfGmZYCQL2RDqk3g%3D%3D" +
                "&numOfRows=25&pageNo=1&sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&_returnType=json";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        );
        request.setShouldCache(false);
        MainActivity.requestQueue.add(request);
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

    public void parseJson(String json) {
        try {
            Integer max = 0;
            Integer min = 1000;
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("list");

            JSONObject obj_data = array.getJSONObject(0);
            String date = obj_data.getString("dataTime");
            textView.setText(date);

            for(int i=0; i<array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String ku = obj.getString("cityName");
                String pm10 = obj.getString("pm10Value");

                citynames.add(obj.getString("cityName"));
                covalues.add(obj.getString("coValue"));
                o3values.add(obj.getString("o3Value"));
                no2values.add(obj.getString("no2Value"));
                pm10values.add(obj.getString("pm10Value"));
                pm25values.add(obj.getString("pm25Value"));

                if(max <= Integer.parseInt(pm10))
                    max = Integer.parseInt(pm10);
                if(min >= Integer.parseInt(pm10))
                    min = Integer.parseInt(pm10);

                items.add(ku + " : " + pm10);
            }
            adapter.notifyDataSetChanged();

            textView2.setText("최고: " + max + " /최저: " + min);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}