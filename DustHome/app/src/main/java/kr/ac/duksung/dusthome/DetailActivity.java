package kr.ac.duksung.dusthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailActivity extends AppCompatActivity {
    TextView textView;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String cityname = intent.getStringExtra("cityname");
        String co = intent.getStringExtra("co");
        String o3 = intent.getStringExtra("o3");
        String no2 = intent.getStringExtra("no2");
        String pm10 = intent.getStringExtra("pm10");
        String pm25 = intent.getStringExtra("pm25");

        setTitle(cityname + "상세정보");

        textView = (TextView)findViewById(R.id.textView);
        textView3 = (TextView)findViewById(R.id.textView3);

        textView.setText("일산화탄소: " + co + "\n오존: " + o3 + "\n이산화질소: " + no2 + "\n미세먼지: " + pm10 + "\n초미세먼지: " + pm25);

        makeRequest();

    }

    public void makeRequest() {
        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst" +
                "?serviceKey=LVPJEwy8mJVSUUVVOBFzarmxU8%2BdUwT9DxtE4pdFCvy0Q%2FEjWwMZkaJwkAxUGqPbvip1YTDfGmZYCQL2RDqk3g%3D%3D" +
                "&numOfRows=10&pageNo=1&itemCode=PM10&dataGubun=DAILY&searchCondition=MONTH&_returnType=json";
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
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("list");

            JSONObject obj = array.getJSONObject(0);
            String seoul = obj.getString("seoul");
            String time = obj.getString("dataTime");
            textView3.setText(seoul + " (" + time + ")");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}