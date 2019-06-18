package kcat.org.pingpongdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {
    private ListView listView;
    MatchDao matchDao;
    String s_name;
    int s_handType;
    int s_racketType;
    int s_fRubber;
    int s_bRubber;

    int winMatch;
    int roseMatch;
    MatchDto matchDto;
    int limitIndex = 10;

    CheckBox leftHand;
    CheckBox rightHand;
    CheckBox jPenholder;
    CheckBox shakeHand;
    CheckBox cPenholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        matchDao = new MatchDao(this);
        findViewById(R.id.registerMatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MatchActivity.this,WriteMatchActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.showMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitIndex += 5;
                ListInit(limitIndex);
                //Toast.makeText(MatchActivity.this,data.size()+"",Toast.LENGTH_SHORT).show();
            }
        });

        leftHand = (CheckBox)findViewById(R.id.leftHand);
        rightHand = (CheckBox)findViewById(R.id.rightHand);
        jPenholder = (CheckBox)findViewById(R.id.jPenholder);
        shakeHand = (CheckBox)findViewById(R.id.shakeHand);
        cPenholder = (CheckBox)findViewById(R.id.cPenholder);


        ListInit(10);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                matchDto = (MatchDto) adapterView.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), String.valueOf(matchDto.getId()),Toast.LENGTH_SHORT).show();
                //matchRecordDialog = new MatchRecordDialog(MatchActivity.this,matchDto,positiveListener,negativeListener,netralListener);
                //matchRecordDialog.show();
                Intent next = new Intent(MatchActivity.this,MatchRecord.class);
                next.putExtra("matchData",matchDto);
                startActivity(next);
                finish();

            }
        });

        findViewById(R.id.searchBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitIndex = 10;
                ListInit(limitIndex);
            }
        });
    }

    public void ListInit(int limitValue)
    {
        winMatch = 0;
        roseMatch = 0;
        ArrayList<MatchDto> dataList = matchDao.select( s_name, s_handType,  s_racketType,  s_fRubber,  s_bRubber );
        MyAdapter myAdapter= new MyAdapter();
        int count = 0;
        for(MatchDto matchDto : dataList) {
            //   adapter.add("이름 : "+s.getName() + "세트 :"+s.getWinSet()+"승 "+ s.getRoseSet()+"패");

            if(matchDto.getHandType() == 0 && !rightHand.isChecked())
            {
                continue;
            }else if(matchDto.getHandType() == 1 && !leftHand.isChecked())
            {
                continue;
            }

            if(matchDto.getRacket_type() == 0 && !shakeHand.isChecked())
            {
                continue;
            }else if(matchDto.getRacket_type() == 1 && !jPenholder.isChecked())
            {
                continue;
            }else if(matchDto.getRacket_type() == 2 && !cPenholder.isChecked())
            {
                continue;
            }

            if(count < limitValue)
            {
                myAdapter.addItem(matchDto);
                count++;
            }
            if(matchDto.getWinSet() > matchDto.getRoseSet())
            {
                winMatch++;
            }else{
                roseMatch++;
            }
        }

        TextView tv_recordBox = (TextView)findViewById(R.id.tv_recordBox);
        tv_recordBox.setText(winMatch +"승 " + roseMatch +"패");
        TextView tv_winRecord = (TextView)findViewById(R.id.tv_winRecord);
        double winPercent = winMatch * 100.0 / (double)(winMatch + roseMatch);

        tv_winRecord.setText(String.valueOf(winPercent)+"%");
        listView = (ListView)findViewById(R.id.matchList);
        listView.setAdapter(myAdapter);
    }
}
