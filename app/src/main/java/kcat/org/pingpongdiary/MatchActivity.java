package kcat.org.pingpongdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    MatchRecordDialog matchRecordDialog;
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
            }
        });

        findViewById(R.id.delMatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListInit();
                //Toast.makeText(MatchActivity.this,data.size()+"",Toast.LENGTH_SHORT).show();
            }
        });

        ListInit();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                matchDto = (MatchDto) adapterView.getItemAtPosition(i);
                matchRecordDialog = new MatchRecordDialog(MatchActivity.this,matchDto);

                matchRecordDialog.show();




//
//                Toast.makeText(MatchActivity.this,String.valueOf(matchDto.getId()),Toast.LENGTH_SHORT).show();
                Toast.makeText(MatchActivity.this,matchDto.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ListInit()
    {
        ArrayList<MatchDto> dataList = matchDao.select( s_name, s_handType,  s_racketType,  s_fRubber,  s_bRubber );
        MyAdapter myAdapter= new MyAdapter();
        for(MatchDto matchDto : dataList) {
            //   adapter.add("이름 : "+s.getName() + "세트 :"+s.getWinSet()+"승 "+ s.getRoseSet()+"패");
            myAdapter.addItem(matchDto);
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
