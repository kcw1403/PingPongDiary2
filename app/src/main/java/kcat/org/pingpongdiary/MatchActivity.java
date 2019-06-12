package kcat.org.pingpongdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
                //Toast.makeText(MatchActivity.this,data.size()+"",Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<MatchDto> dataList = matchDao.select( s_name, s_handType,  s_racketType,  s_fRubber,  s_bRubber );

        MyAdapter myAdapter = new MyAdapter();


        for(MatchDto matchDto : dataList) {
         //   adapter.add("이름 : "+s.getName() + "세트 :"+s.getWinSet()+"승 "+ s.getRoseSet()+"패");
            myAdapter.addItem(matchDto);
        }

        listView = (ListView)findViewById(R.id.matchList);
        listView.setAdapter(myAdapter);

    }
}
