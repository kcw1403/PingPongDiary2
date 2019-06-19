package kcat.org.pingpongdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;

public class MatchRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    Spinner handSpinner;
    Spinner racketSpinner;
    Spinner fRubberSpinner;
    Spinner bRubberSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    String[] rubberTypeItem = new String[]{"평면러버","숏핌플","롱핌플","안티러버"};
    String[] racketTypeItem = new String[]{"세이크","일펜","중펜"};


    int id;
    String name;
    String clubName;
    int rank;
    int handy;
    int handType;
    int racketType;
    int frontRubber;
    int backRubber;
    int winSet;
    int roseSet;
    String matchDate;
    String review;
    Calendar m;
    MatchDao matchDao;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MatchActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_record);

        matchDao = new MatchDao(this);
        Intent intent = getIntent();
        MatchDto matchDto = (MatchDto) intent.getSerializableExtra("matchData");
        id = matchDto.getId();
        handSpinner = (Spinner) findViewById(R.id.handType);
        fRubberSpinner = (Spinner) findViewById(R.id.front_rubber);
        bRubberSpinner = (Spinner) findViewById(R.id.back_rubber);
        racketSpinner = (Spinner) findViewById(R.id.racket_type);

        handSpinner.setOnItemSelectedListener(this);
        fRubberSpinner.setOnItemSelectedListener(this);
        bRubberSpinner.setOnItemSelectedListener(this);
        racketSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,handTypeItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handSpinner.setAdapter(adapter);
        ArrayAdapter<String> rubber_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,rubberTypeItem);
        rubber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fRubberSpinner.setAdapter(rubber_adapter);
        bRubberSpinner.setAdapter(rubber_adapter);
        ArrayAdapter<String> racket_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,racketTypeItem);
        racket_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        racketSpinner.setAdapter(racket_adapter);

        ((EditText)findViewById(R.id.nameBox)).setText(matchDto.getName());
        ((EditText)findViewById(R.id.clubNameBox)).setText(matchDto.getClubName());
        ((EditText)findViewById(R.id.rankBox)).setText(String.valueOf(matchDto.getRank()));
        ((EditText)findViewById(R.id.handyScore)).setText(String.valueOf(matchDto.getHandy()));
        ((Spinner)findViewById(R.id.handType)).setSelection(matchDto.getHandType());
        ((Spinner)findViewById(R.id.racket_type)).setSelection(matchDto.getRacket_type());
        ((Spinner)findViewById(R.id.front_rubber)).setSelection(matchDto.getFrontRubber());
        ((Spinner)findViewById(R.id.back_rubber)).setSelection(matchDto.getBackRubber());
        ((EditText)findViewById(R.id.win_set)).setText(String.valueOf(matchDto.getWinSet()));
        ((EditText)findViewById(R.id.rose_set)).setText(String.valueOf(matchDto.getRoseSet()));
        ((EditText)findViewById(R.id.matchDateBox)).setText(matchDto.getMatchDate());
        ((EditText)findViewById(R.id.reviewBox)).setText(matchDto.getReview());

        findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchDto matchDto = new MatchDto();
                matchDto.setId(id);
                if(matchDao.delete(matchDto)> 0)
                {
                    Toast.makeText(MatchRecord.this,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                };
            }
        });

        findViewById(R.id.modifyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name =((EditText)findViewById(R.id.nameBox)).getText().toString();
                clubName =((EditText)findViewById(R.id.clubNameBox)).getText().toString();
                matchDate =((EditText)findViewById(R.id.matchDateBox)).getText().toString();


                if(!(((EditText) findViewById(R.id.rankBox)).getText().toString().equals("")))
                    rank = Integer.parseInt(((EditText) findViewById(R.id.rankBox)).getText().toString());
                else
                    rank = 100;

                if(!(((EditText) findViewById(R.id.handyScore)).getText().toString().equals("")))
                    handy = Integer.parseInt(((EditText) findViewById(R.id.handyScore)).getText().toString());
                else
                    handy = 0;

                handType = handSpinner.getSelectedItemPosition();
                frontRubber = fRubberSpinner.getSelectedItemPosition();
                backRubber = bRubberSpinner.getSelectedItemPosition();
                racketType = racketSpinner.getSelectedItemPosition();

                if(!(((EditText) findViewById(R.id.win_set)).getText().toString().equals("")))
                    winSet = Integer.parseInt(((EditText) findViewById(R.id.win_set)).getText().toString());
                else
                    winSet = 0;

                if(!(((EditText) findViewById(R.id.rose_set)).getText().toString().equals("")))
                    roseSet = Integer.parseInt(((EditText) findViewById(R.id.rose_set)).getText().toString());
                else
                    roseSet = 0;

                if(winSet == 0 && roseSet == 0 )
                {
                    Toast.makeText(MatchRecord.this,"셋트를 제대로 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                review =((EditText)findViewById(R.id.reviewBox)).getText().toString();


                if(name.equals(""))
                {
                    Toast.makeText(MatchRecord.this,"이름을 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(matchDate.equals(""))
                {
                    Toast.makeText(MatchRecord.this,"경기 날짜를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                //MatchDto(String name,String clubName,int rank, int handy, int frontRubber, int backRubber, int winSet, int roseSet, String matchDate, String review)
                MatchDto matchDto = new MatchDto(name,clubName,rank,handy, handType, racketType,frontRubber,backRubber,winSet,roseSet,matchDate,review);
                matchDto.setId(id);
                try {
                    matchDao.update(matchDto);
                } catch (SQLException e) {
                    Toast.makeText(MatchRecord.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    //e.printStackTrace();
                }
                Toast.makeText(MatchRecord.this,"수정되었습니다.",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
