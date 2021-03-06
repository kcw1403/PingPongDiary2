package kcat.org.pingpongdiary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;

public class WriteMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner handSpinner;
    Spinner fRubberSpinner;
    Spinner bRubberSpinner;
    Spinner racketSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    String[] rubberTypeItem = new String[]{"평면러버","숏핌플","롱핌플","안티러버"};
    String[] racketTypeItem = new String[]{"세이크","일펜","중펜"};

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
    EditText etName;
    ListView nameList;
    LinearLayout nameListLayout;
    ArrayList<MatchDto> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_match);

        matchDao = new MatchDao(this);
        handSpinner = (Spinner) findViewById(R.id.handType);
        fRubberSpinner = (Spinner)findViewById(R.id.front_rubber);
        bRubberSpinner = (Spinner)findViewById(R.id.back_rubber);
        racketSpinner = (Spinner)findViewById(R.id.racket_type);

        handSpinner.setOnItemSelectedListener(this);
        fRubberSpinner.setOnItemSelectedListener(this);
        bRubberSpinner.setOnItemSelectedListener(this);
        racketSpinner.setOnItemSelectedListener(this);

        etName = (EditText)findViewById(R.id.nameBox);
        nameList = (ListView)findViewById(R.id.nameList);
        nameListLayout = (LinearLayout)findViewById(R.id.nameListLayout);

        ArrayAdapter nameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1) ;
        nameList.setAdapter(nameAdapter);
        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MatchDto temp =  dataList.get(i);
                ((EditText)findViewById(R.id.nameBox)).setText(temp.getName());
                ((EditText)findViewById(R.id.clubNameBox)).setText(temp.getClubName());
                ((EditText)findViewById(R.id.handyScore)).setText(String.valueOf(temp.getHandy()));
                ((EditText)findViewById(R.id.rankBox)).setText(String.valueOf(temp.getRank()));
                handSpinner.setSelection(temp.getHandType());
                fRubberSpinner.setSelection(temp.getFrontRubber());
                bRubberSpinner.setSelection(temp.getBackRubber());
                racketSpinner.setSelection(temp.getRacket_type());
                nameListLayout.setVisibility(View.INVISIBLE);

            }
        });
        dataList =  matchDao.selectName();
        for(MatchDto matchDto : dataList) {
            nameAdapter.add(matchDto.getName());

        }
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true)
                {
                    nameListLayout.setVisibility(View.VISIBLE);
                }else{
                    nameListLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
        findViewById(R.id.closeNameLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameListLayout.setVisibility(View.INVISIBLE);
            }
        });

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

        m = Calendar.getInstance();

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WriteMatchActivity.this, MatchActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.matchDateBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(WriteMatchActivity.this, listener, m.get(Calendar.YEAR), m.get(Calendar.MONTH), m.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        findViewById(R.id.saveMatchBtn).setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(WriteMatchActivity.this,"셋트를 제대로 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                review =((EditText)findViewById(R.id.reviewBox)).getText().toString();

                if(name.equals(""))
                {
                    Toast.makeText(WriteMatchActivity.this,"이름을 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(matchDate.equals(""))
                {
                    Toast.makeText(WriteMatchActivity.this,"경기 날짜를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                //MatchDto(String name,String clubName,int rank, int handy, int frontRubber, int backRubber, int winSet, int roseSet, String matchDate, String review)
                MatchDto matchDto = new MatchDto(name,clubName,rank,handy, handType, racketType,frontRubber,backRubber,winSet,roseSet,matchDate,review);

                try {
                    matchDao.insert(matchDto);
                } catch (SQLException e) {
                    Toast.makeText(WriteMatchActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    //e.printStackTrace();
                }

                Toast.makeText(WriteMatchActivity.this,"등록되었습니다.",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WriteMatchActivity.this, MatchActivity.class);
                startActivity(i);
                finish();
            }
        });

}

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            m.set(Calendar.HOUR_OF_DAY,hour);
            m.set(Calendar.MINUTE,minute);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmm");
//            Toast.makeText(WriteMatchActivity.this,hour+","+minute,Toast.LENGTH_LONG).show();
            ((EditText)findViewById(R.id.matchDateBox)).setText(sdf.format(m.getTime()));
        }
    };

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            m.set(i,i1,i2);
            TimePickerDialog dialog = new TimePickerDialog(WriteMatchActivity.this, timeSetListener, m.get(Calendar.HOUR_OF_DAY), m.get(Calendar.MINUTE),false);
            dialog.show();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == handSpinner.getId())
        {
         //   Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
