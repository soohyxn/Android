package kr.teamcadi.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String operator = "";
    String num1 = "";
    String num2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_process = (TextView)findViewById(R.id.tv_process);
        final TextView tv_result = (TextView)findViewById(R.id.tv_result);

        Button btn_0 = (Button)findViewById(R.id.btn_0);
        Button btn_1 = (Button)findViewById(R.id.btn_1);
        Button btn_2 = (Button)findViewById(R.id.btn_2);
        Button btn_3 = (Button)findViewById(R.id.btn_3);
        Button btn_4 = (Button)findViewById(R.id.btn_4);
        Button btn_5 = (Button)findViewById(R.id.btn_5);
        Button btn_6 = (Button)findViewById(R.id.btn_6);
        Button btn_7 = (Button)findViewById(R.id.btn_7);
        Button btn_8 = (Button)findViewById(R.id.btn_8);
        Button btn_9 = (Button)findViewById(R.id.btn_9);
        Button btn_plus = (Button)findViewById(R.id.btn_plus);
        Button btn_subtract = (Button)findViewById(R.id.btn_subtract);
        Button btn_multiply = (Button)findViewById(R.id.btn_multiply);
        Button btn_division1 = (Button)findViewById(R.id.btn_division1);
        Button btn_division2 = (Button)findViewById(R.id.btn_division2);
        Button btn_result = (Button)findViewById(R.id.btn_result);
        Button btn_clear = (Button)findViewById(R.id.btn_clear);
        Button btn_del = (Button)findViewById(R.id.btn_del);
        Button btn_dot = (Button)findViewById(R.id.btn_dot);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_0:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("0");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "0");
                            tv_result.setText(tv_result.getText().toString() + "0");
                        }
                        break;
                    case R.id.btn_1:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("1");
                            tv_process.setText("1");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "1");
                            tv_result.setText(tv_result.getText().toString() + "1");
                        }
                        break;
                    case R.id.btn_2:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("2");
                            tv_process.setText("2");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "2");
                            tv_result.setText(tv_result.getText().toString() + "2");
                        }
                        break;
                    case R.id.btn_3:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("3");
                            tv_process.setText("3");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "3");
                            tv_result.setText(tv_result.getText().toString() + "3");
                        }
                        break;
                    case R.id.btn_4:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("4");
                            tv_process.setText("4");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "4");
                            tv_result.setText(tv_result.getText().toString() + "4");
                        }
                        break;
                    case R.id.btn_5:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("5");
                            tv_process.setText("5");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "5");
                            tv_result.setText(tv_result.getText().toString() + "5");
                        }
                        break;
                    case R.id.btn_6:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("6");
                            tv_process.setText("6");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "6");
                            tv_result.setText(tv_result.getText().toString() + "6");
                        }
                        break;
                    case R.id.btn_7:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("7");
                            tv_process.setText("7");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "7");
                            tv_result.setText(tv_result.getText().toString() + "7");
                        }
                        break;
                    case R.id.btn_8:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("8");
                            tv_process.setText("8");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "8");
                            tv_result.setText(tv_result.getText().toString() + "8");
                        }
                        break;
                    case R.id.btn_9:
                        if(tv_result.getText().toString().equals("0")) {
                            tv_result.setText("9");
                            tv_process.setText("9");
                        }
                        else {
                            tv_process.setText(tv_process.getText().toString() + "9");
                            tv_result.setText(tv_result.getText().toString() + "9");
                        }
                        break;
                    case R.id.btn_dot:
                        if(tv_result.getText().toString().equals("0"))
                            tv_process.setText("0");
                        tv_process.setText(tv_process.getText().toString() + ".");
                        tv_result.setText(tv_result.getText().toString() + ".");
                        break;
                    case R.id.btn_result:
                        tv_process.setText(tv_process.getText().toString() + "=");
                        num2 = tv_result.getText().toString();

                        Double num_1 = Double.parseDouble(num1);
                        Double num_2 = Double.parseDouble(num2);

                        switch (operator) {
                            case "+":
                                tv_result.setText(num_1 + num_2 + "");
                                break;
                            case "-":
                                tv_result.setText(num_1 - num_2 + "");
                                break;
                            case "*":
                                tv_result.setText(num_1 * num_2 + "");
                                break;
                            case "/":
                                try {
                                    if(num_2 == 0)
                                        throw new ArithmeticException();
                                    tv_result.setText(num_1 / num_2 + "");
                                }
                                catch (ArithmeticException e){
                                    Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case "%":
                                try {
                                    if(num_2 == 0)
                                        throw new ArithmeticException();
                                    tv_result.setText(num_1 % num_2 + "");
                                }
                                catch (ArithmeticException e) {
                                    Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다", Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                        num1 = tv_result.getText().toString();
                        break;
                    case R.id.btn_plus:
                        num1 = tv_result.getText().toString();
                        if(num1.equals("0"))
                            tv_process.setText("0");
                        operator = "+";
                        tv_result.setText("");
                        tv_process.setText(tv_process.getText().toString() + "+");
                        break;
                    case R.id.btn_subtract:
                        num1 = tv_result.getText().toString();
                        if(num1.equals("0"))
                            tv_process.setText("0");
                        operator = "-";
                        tv_result.setText("");
                        tv_process.setText(tv_process.getText().toString() + "-");
                        break;
                    case R.id.btn_multiply:
                        num1 = tv_result.getText().toString();
                        if(num1.equals("0"))
                            tv_process.setText("0");
                        operator = "*";
                        tv_result.setText("");
                        tv_process.setText(tv_process.getText().toString() + "x");
                        break;
                    case R.id.btn_division1:
                        num1 = tv_result.getText().toString();
                        if(num1.equals("0"))
                            tv_process.setText("0");
                        operator = "/";
                        tv_result.setText("");
                        tv_process.setText(tv_process.getText().toString() + "/");
                        break;
                    case R.id.btn_division2:
                        num1 = tv_result.getText().toString();
                        if(num1.equals("0"))
                            tv_process.setText("0");
                        operator = "%";
                        tv_result.setText("");
                        tv_process.setText(tv_process.getText().toString() + "%");
                        break;
                    case R.id.btn_clear:
                        tv_process.setText("");
                        tv_result.setText("0");
                        operator = "";
                        num1 = "";
                        num2 = "";
                        break;
                    case R.id.btn_del:
                        String del_process = tv_process.getText().toString();
                        tv_process.setText(del_process.substring(0,del_process.length() - 1));
                        String del_result = tv_result.getText().toString();
                        tv_result.setText(del_process.substring(0,del_process.length() - 1));
                }
            }
        };
        btn_0.setOnClickListener(onClickListener);
        btn_1.setOnClickListener(onClickListener);
        btn_2.setOnClickListener(onClickListener);
        btn_3.setOnClickListener(onClickListener);
        btn_4.setOnClickListener(onClickListener);
        btn_5.setOnClickListener(onClickListener);
        btn_6.setOnClickListener(onClickListener);
        btn_7.setOnClickListener(onClickListener);
        btn_8.setOnClickListener(onClickListener);
        btn_9.setOnClickListener(onClickListener);
        btn_dot.setOnClickListener(onClickListener);
        btn_plus.setOnClickListener(onClickListener);
        btn_subtract.setOnClickListener(onClickListener);
        btn_multiply.setOnClickListener(onClickListener);
        btn_division1.setOnClickListener(onClickListener);
        btn_division2.setOnClickListener(onClickListener);
        btn_result.setOnClickListener(onClickListener);
        btn_clear.setOnClickListener(onClickListener);
        btn_del.setOnClickListener(onClickListener);
    }
}