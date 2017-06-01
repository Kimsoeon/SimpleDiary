package soeonkim.kr.hs.emirim.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker)findViewById(R.id.date_pick);
        edit = (EditText)findViewById(R.id.edit);
        but = (Button)findViewById(R.id.but);
        //Calendar는 추상클래스. 바로 객체생성 불가. 그렇다고 상속받아서 사용하긴 귀찮. getInstance 메서드가 (현재객체 참조값 반환하도록)만들어져있음.
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE); //DAY OF MONTH랑 같음

        date.init(year, month, day, new DatePicker.OnDateChangedListener() { //네번째 매개변수는 날짜가변경됐을때 동작할 핸들러
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) { //사용자가 날짜를 바꿀때마다 호출됨
                fileName += year + " / " + (month+1) + " / " + day + ".txt";
                String readData = readDiary(fileName);
                edit.setText(readData);
                but.setEnabled(true);
            }
        });
    }//onCreate 닫음
    public String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream fIn = null; //참조변수 초기화는 널
        try {
           fIn = openFileInput(fileName); //만약 파일이 없으면 예외처리
            byte[] buf = new byte[500]; // 500바이트 이하의 문자가 저장됐다고 생각하고 설정한값. 만약 500바이트 이상 입력되었으면 짤릴듯. 바이트수 키우면 더 많이
            //읽어올 수 있음ㅎ
            fIn.read(buf);
            diaryStr = new String(buf).trim(); // 500바이트 읽어온걸 string으로 변환 trim은 앞뒤공간만 제거 가능
            but.setText("수정");
        } catch (FileNotFoundException e) { //파일못찾을떄
            edit.setText(fileName + "의 일기가 존재하지 않습니다.");
            but.setText("새로 저장");
        } catch (IOException e) { //읽어오다가 문제 생길 경우
        }
        return diaryStr;
    }
}
