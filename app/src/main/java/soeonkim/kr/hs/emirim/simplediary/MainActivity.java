package soeonkim.kr.hs.emirim.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                fileName += year + " / " + (month+1) + " / " + day + ".txt";
                String readData = readDiary(fileName);
                edit.setText(readData);
                but.setEnabled(true);
            }
        });
    }//onCreate 닫음
    public String readDiary(String fileName){
        return null;
    }
}
