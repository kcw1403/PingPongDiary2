package kcat.org.pingpongdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {

    String[] beginnerTrainingList = new String[]{
        "화/백\n 크로스",
        "화/백\n 스트레이트",
        "화/백 전환",
        "화/백\n크로스 커트",
        "화/백 스트레이트\n커트",
        "화/백\n전환 커트"
    };

    String[] intermediateTrainingList = new String[]{
            "화/백 드라이브\n 크로스",
            "화/백 드라이브\n 스트레이트",
            "화/백 드라이브\n 전환",
            "팔켄베리 풋워크",
            "커트볼 화드라이브\n크로스/스트레이트",
            "커트볼 백드라이브\n크로스/스트레이트",
    };

    String[] expertTrainingList = new String[]{
            "화쪽 짧은 서브\n 플릭 후 자유",
            "백쪽 짧은 서브\n 플릭 후 자유",
            "서브 후 3구 화/백 드라이브 후 자유",
            "서브 후 3구 화/백 플릭 후 자유",
            "화/백 커트볼\n 드라이브 스트레이트",
            "화/백 커트볼\n좌우 랜덤 드라이브"
    };


    int trainingLevel = 0;
    ArrayList<Button>trainingBtnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        trainingBtnList = new ArrayList<Button>();
        findViewById(R.id.beginnerBtn).setOnClickListener(this);
        findViewById(R.id.intermediateBtn).setOnClickListener(this);
        findViewById(R.id.expertBtn).setOnClickListener(this);
        for(int i=0; i<6; i++)
        {
            int id = getApplication().getResources().getIdentifier("training"+(i+1), "id", this.getPackageName());
            Button temp = (Button)findViewById(id);
            temp.setOnClickListener(this);
            trainingBtnList.add(temp);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.beginnerBtn)
        {
            trainingLevel = 0;
            for(int i=0; i<6; i++)
            {
                trainingBtnList.get(i).setText(beginnerTrainingList[i]);
            }
        }else if(view.getId() == R.id.intermediateBtn)
        {
            trainingLevel = 1;
            for(int i=0; i<6; i++)
            {
                trainingBtnList.get(i).setText(intermediateTrainingList[i]);
            }
        }else if(view.getId() == R.id.expertBtn)
        {
            trainingLevel = 2;
            for(int i=0; i<6; i++)
            {
                trainingBtnList.get(i).setText(expertTrainingList[i]);
            }
        }
    }
}
