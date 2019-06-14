package kcat.org.pingpongdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MatchRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    Spinner handSpinner;
    Spinner fRubberSpinner;
    Spinner bRubberSpinner;
    Spinner racketSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    String[] rubberTypeItem = new String[]{"평면러버","숏핌플","롱핌플","안티러버"};
    String[] racketTypeItem = new String[]{"세이크","일펜","중펜"};

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

        Intent intent = getIntent();
        MatchDto matchDto = (MatchDto) intent.getSerializableExtra("matchData");
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
        ((Spinner)findViewById(R.id.handType)).setSelection(matchDto.getHandy());
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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
