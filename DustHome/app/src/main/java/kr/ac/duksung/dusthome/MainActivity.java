package kr.ac.duksung.dusthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    Button btn;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        btn = (Button)findViewById(R.id.button);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void makeRequest() {
        String url = "http://203.252.219.241:8080/FinalProject/loginPro.jsp?id=" + editText.getText().toString() + "&passwd=" + editText2.getText().toString();
        final StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*try {
                            response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            Toast.makeText(getApplicationContext(), "encoding error",
                                    Toast.LENGTH_LONG).show();
                        }*/

                        android.util.Log.d("test: ", response);
                        String result = response.trim();

                        if(result.equals("1")) {
                            Intent intent = new Intent(getApplicationContext(), AirActivity.class);
                            startActivity(intent);
                        }
                        else {
                            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                            dlg.setTitle("Login Failed!");
                            dlg.setMessage("Please try again.");
                            dlg.setPositiveButton("확인",null);
                            dlg.show();
                        }

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

}