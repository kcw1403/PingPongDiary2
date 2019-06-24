package kcat.org.pingpongdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {

    String[] beginnerTrainingList = new String[]{
        "화/백\n 크로스 랠리",
        "화/백\n 스트레이트 랠리",
        "화/백 전환 랠리",
        "화/백\n크로스 커트 랠리",
        "화/백 스트레이트\n커트 랠리",
        "화/백\n전환 커트 랠리"
    };

    String[] intermediateTrainingList = new String[]{
            "화/백 드라이브\n 크로스 랠리",
            "화/백 드라이브\n 스트레이트 랠리",
            "화/백 드라이브\n 전환 랠리",
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        findViewById(R.id.beginnerBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
