package kcat.org.pingpongdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.manageMatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MatchActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.finishBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.trainingBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( MainActivity.this, "준비중 입니다.",Toast.LENGTH_SHORT).show();
            }
        });

        MatchDao matchDao = new MatchDao(this);
        ArrayList<MatchDto>dataList =  matchDao.select("",0,0,0,0);
        int win=0;
        int rose=0;

        for(MatchDto matchDto : dataList)
        {
            if(matchDto.getWinSet() > matchDto.getRoseSet())
            {
                win++;
            }else{
                rose++;
            }
            if(win+rose > 20)
                break;
        }
        ((TextView)findViewById(R.id.winValue)).setText(String.valueOf(win));
        ((TextView)findViewById(R.id.roseValue)).setText(String.valueOf(rose));


    }
}
