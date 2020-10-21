package kr.ac.duksung.duksunghome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayList<String> urls;
    ArrayAdapter<String> adapter;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<String>();
        urls = new ArrayList<String>();
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        makeRequest();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), items.get(i), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), DetailedNews.class);
                intent.putExtra("url",urls.get(i));
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
                adapter.clear();
                makeRequest();
        }
        return false;
    }

    public void makeRequest() {
        String url = "http://www.chosun.com/";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            Toast.makeText(getApplicationContext(), "encoding error",
                                    Toast.LENGTH_LONG).show();
                        }
                        parseHtml(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "network error",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void parseHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements mainElements = doc.select("h2 a");
        Elements itemElements = doc.select("dl dt a");
        for(Element element : mainElements) {
            items.add(element.text().trim());
            android.util.Log.d("test: ", element.text());
        }
        for(Element element : itemElements) {
            items.add(element.text().trim());
            android.util.Log.d("test: ", element.text());
        }
        for(Element element : mainElements) {
            urls.add(element.attr("href").trim());
            android.util.Log.d("test: ", element.text());
        }
        for(Element element : itemElements) {
            urls.add(element.attr("href").trim());
            android.util.Log.d("test: ", element.text());
        }

        android.util.Log.d("test: ", items.size() + " items");
        adapter.notifyDataSetChanged();
    }

}
