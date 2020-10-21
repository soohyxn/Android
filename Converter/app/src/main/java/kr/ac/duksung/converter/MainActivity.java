package kr.ac.duksung.converter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView; //3개의 필드를 가짐, 참조값(주소값)을 가지고 있음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //부모의 onCreate 호출
        setContentView(R.layout.activity_main); //무대를 올림, 배치관리자 같이 생성, 3개의 위젯 만들어짐

        editText = (EditText) findViewById(R.id.editText); //R클래스를 통해 참조값을 가져와서 참조값 저장 -> 접근할 수 있게 됨
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fahrenStr = editText.getText().toString(); //editText로 가서 사용자가 입력한 값을 가져와서 자바의 문자열로 변환
                fahrenStr = fahrenStr.trim(); //앞뒤공백제거, 아무것도 입력하지 않으면 공백문자 하나가 들어옴
                if(fahrenStr.equals("")) { //빈문자열 입력 경우
                    textView.setText("화씨값을 입력하세요~");
                    Toast.makeText(getApplicationContext(), "화씨값을 입력!!",
                            Toast.LENGTH_LONG).show(); //오랜시간동안 경고문을 보여줌

                } else {
                    Double fahren = Double.parseDouble(fahrenStr); //실수형으로 바꿈
                    Double celsius = (fahren - 32.0) / 1.8;
                    textView.setText("섭씨 " + celsius.toString()); //디스플레이할때는 텍스트형으로 해야함. 자바의 문자열형으로 바꿔줌
                }
            }
        });
    }
}


