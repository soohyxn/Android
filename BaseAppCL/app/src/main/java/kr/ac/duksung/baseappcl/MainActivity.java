package kr.ac.duksung.baseappcl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //부모의 onCreate 실행
        setContentView(R.layout.activity_main); //R클래스에 가서 이 아이디로 저장된 무대를 올려라

        button1 = (Button) findViewById(R.id.button1); //R클래스에 등록된 button1 id의 참조값을 가져와서 Button클래스형으로 변환
        button2 = (Button) findViewById(R.id.button2);

//   함축2 - 교재 코드

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼을 눌렀어요",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "두 번째 버튼을 눌렀어요",
                        Toast.LENGTH_SHORT).show();
            }
        });


//  함축1 - 중간형 코드(익명 클래스)
/*
        View.OnClickListener listener = new View.OnClickListener() { //익명클래스 기능으로 클래스 정의, 클래스 정의하고 객체를 생성해서 Listener객체에 저장
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼을 눌렀어요",
                        Toast.LENGTH_SHORT).show();
            }
        };
        button1.setOnClickListener(listener);
*/

/*
        ButtonListener listener = new ButtonListener(); //객체생성
        button1.setOnClickListener(listener);
*/

    }
/*
    private class ButtonListener implements View.OnClickListener { //private는 내부클래스, 내부사용목적
        @Override

        public void onClick(View view) { //버튼이 눌리면 실행됨
            Toast.makeText(getApplicationContext(), "버튼을 눌렀어요", //activity 자체의 참조값을 줌
                    Toast.LENGTH_SHORT).show(); //LENGTH_SHORT = 짧게 디스플레이
        }
    }
*/
}

