package kr.ac.duksung.scheduler2home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ComputeActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        Intent intent = getIntent();
        int operand1 = intent.getIntExtra("operand1", 0);
        int operand2 = intent.getIntExtra("operand2", 0);
        String operator = intent.getStringExtra("operator");
        int result = 0;

        switch(operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }

        String compute = "(" + operand1 + ")" + operator + "(" + operand2 + ")" + "=" + result;
        textView.setText(compute);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("appoint", textView.getText().toString());
                setResult(RESULT_OK,intent); //양방향 엑티비티
                finish();
            }
        });
    }
}