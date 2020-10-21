package kr.ac.duksung.scheduler2home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView edittext1, edittext2;
    RadioGroup radioGroup;
    Button button;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        edittext1 = (TextView)findViewById(R.id.editText1);
        edittext2 = (TextView)findViewById(R.id.editText2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = edittext1.getText().toString();
                String text2 = edittext2.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ComputeActivity.class);
                if(text1.equals("")) {
                    Toast.makeText(getApplicationContext(), "첫번째 정수 입력",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton1:
                        intent.putExtra("operator", "+");
                        break;
                    case R.id.radioButton2:
                        intent.putExtra("operator", "-");
                        break;
                    case R.id.radioButton3:
                        intent.putExtra("operator", "*");
                        break;
                    case R.id.radioButton4:
                        intent.putExtra("operator", "/");
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "연산자 선택",
                                Toast.LENGTH_LONG).show();
                        return;
                }
                if(text2.equals("")) {
                    Toast.makeText(getApplicationContext(), "두번째 정수 입력",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                intent.putExtra("operand1", Integer.parseInt(text1));
                intent.putExtra("operand2", Integer.parseInt(text2));
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            String result = data.getStringExtra("appoint") + "\n";
            String compute = result.concat(tv.getText().toString());
            tv.setText(compute);
        }
    }
}