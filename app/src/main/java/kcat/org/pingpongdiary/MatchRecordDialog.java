package kcat.org.pingpongdiary;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

public class MatchRecordDialog extends Dialog implements  AdapterView.OnItemSelectedListener{

    private Button mPositiveButton;
    private Button mNegativeButton;



    private Button mNetralButton;
    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;
    private View.OnClickListener mNetralListener;
    private int id;

    Spinner handSpinner;
    Spinner fRubberSpinner;
    Spinner bRubberSpinner;
    Spinner racketSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    String[] rubberTypeItem = new String[]{"평면러버","숏핌플","롱핌플","안티러버"};
    String[] racketTypeItem = new String[]{"세이크","일펜","중펜"};

    public MatchDto getMatchDto() {
        return matchDto;
    }

    private MatchDto matchDto;

    public MatchRecordDialog(@NonNull Context context, MatchDto matchDto) {
        super(context);
        this.matchDto = matchDto;
        id = matchDto.getId();
    }
    public MatchRecordDialog(@NonNull Context context, MatchDto matchDto,View.OnClickListener positiveListener,View.OnClickListener negativeListener,View.OnClickListener netralListener) {
        super(context);
        this.matchDto = matchDto;
        this.mPositiveListener = positiveListener;
        this.mNegativeListener = negativeListener;
        this.mNetralListener = netralListener;
        id = matchDto.getId();
    }
    public int SaveData()
    {
        String name =((EditText)findViewById(R.id.et_nameBox)).getText().toString();
        String clubName =((EditText)findViewById(R.id.et_clubNameBox)).getText().toString();
        String matchDate =((EditText)findViewById(R.id.matchDateBox)).getText().toString();

        int rank = 100;
        if(!(((EditText) findViewById(R.id.et_rankBox)).getText().toString().equals("")))
            rank = Integer.parseInt(((EditText) findViewById(R.id.et_rankBox)).getText().toString());
        else
            rank = 100;

        int handy = 0;
        if(!(((EditText) findViewById(R.id.et_handyScore)).getText().toString().equals("")))
            handy = Integer.parseInt(((EditText) findViewById(R.id.et_handyScore)).getText().toString());
        else
            handy = 0;


        int handType = handSpinner.getSelectedItemPosition();
        int frontRubber = fRubberSpinner.getSelectedItemPosition();
        int backRubber = bRubberSpinner.getSelectedItemPosition();
        int racketType = racketSpinner.getSelectedItemPosition();

        int winSet = 0;
        if(!(((EditText) findViewById(R.id.win_set)).getText().toString().equals("")))
            winSet = Integer.parseInt(((EditText) findViewById(R.id.win_set)).getText().toString());


        int roseSet = 0;
        if(!(((EditText) findViewById(R.id.rose_set)).getText().toString().equals("")))
            roseSet = Integer.parseInt(((EditText) findViewById(R.id.rose_set)).getText().toString());


        if(winSet == 0 && roseSet == 0 )
        {
            Toast.makeText(this.getContext(),"셋트를 제대로 입력해주세요",Toast.LENGTH_SHORT).show();
            return 0;
        }

        String review =((EditText)findViewById(R.id.reviewBox)).getText().toString();


        if(name.equals(""))
        {
            Toast.makeText(this.getContext(),"이름을 입력해주세요",Toast.LENGTH_SHORT).show();
            return 0;
        }
        if(matchDate.equals(""))
        {
            Toast.makeText(this.getContext(),"경기 날짜를 입력해주세요",Toast.LENGTH_SHORT).show();
            return 0;
        }
        //MatchDto(String name,String clubName,int rank, int handy, int frontRubber, int backRubber, int winSet, int roseSet, String matchDate, String review)

        this.matchDto = new MatchDto(name,clubName,rank,handy, handType, racketType,frontRubber,backRubber,winSet,roseSet,matchDate,review);
        this.matchDto.setId(id);
        return 1;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_match_record);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);



        //셋팅
        mPositiveButton=(Button)findViewById(R.id.modifyBtn);
        mNegativeButton=(Button)findViewById(R.id.cancelBtn);
        mNetralButton=(Button)findViewById(R.id.deleteBtn);

        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        mPositiveButton.setOnClickListener(mPositiveListener);
        mNegativeButton.setOnClickListener(mNegativeListener);
        mNetralButton.setOnClickListener(mNetralListener);

        handSpinner = (Spinner) findViewById(R.id.handType);
        fRubberSpinner = (Spinner)findViewById(R.id.front_rubber);
        bRubberSpinner = (Spinner)findViewById(R.id.back_rubber);
        racketSpinner = (Spinner)findViewById(R.id.racket_type);

        handSpinner.setOnItemSelectedListener(this);
        fRubberSpinner.setOnItemSelectedListener(this);
        bRubberSpinner.setOnItemSelectedListener(this);
        racketSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,handTypeItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handSpinner.setAdapter(adapter);
        ArrayAdapter<String> rubber_adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,rubberTypeItem);
        rubber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fRubberSpinner.setAdapter(rubber_adapter);
        bRubberSpinner.setAdapter(rubber_adapter);
        ArrayAdapter<String> racket_adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,racketTypeItem);
        racket_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        racketSpinner.setAdapter(racket_adapter);

        ((EditText)findViewById(R.id.et_nameBox)).setText(matchDto.getName());
        ((EditText)findViewById(R.id.et_clubNameBox)).setText(matchDto.getClubName());
        ((EditText)findViewById(R.id.et_rankBox)).setText(String.valueOf(matchDto.getRank()));
        ((EditText)findViewById(R.id.et_handyScore)).setText(String.valueOf(matchDto.getHandy()));
        ((Spinner)findViewById(R.id.handType)).setSelection(matchDto.getHandy());
        ((Spinner)findViewById(R.id.racket_type)).setSelection(matchDto.getRacket_type());
        ((Spinner)findViewById(R.id.front_rubber)).setSelection(matchDto.getFrontRubber());
        ((Spinner)findViewById(R.id.back_rubber)).setSelection(matchDto.getBackRubber());
        ((EditText)findViewById(R.id.win_set)).setText(String.valueOf(matchDto.getWinSet()));
        ((EditText)findViewById(R.id.rose_set)).setText(String.valueOf(matchDto.getRoseSet()));
        ((EditText)findViewById(R.id.matchDateBox)).setText(matchDto.getMatchDate());
        ((EditText)findViewById(R.id.reviewBox)).setText(matchDto.getReview());



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
