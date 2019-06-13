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

public class MatchRecordDialog extends Dialog implements  AdapterView.OnItemSelectedListener{

    private Button mPositiveButton;
    private Button mNegativeButton;
    private Button mNetralButton;
    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;
    private View.OnClickListener mNetralListener;

    Spinner handSpinner;
    Spinner fRubberSpinner;
    Spinner bRubberSpinner;
    Spinner racketSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    String[] rubberTypeItem = new String[]{"평면러버","숏핌플","롱핌플","안티러버"};
    String[] racketTypeItem = new String[]{"세이크","일펜","중펜"};

    private MatchDto matchDto;

    public MatchRecordDialog(@NonNull Context context, MatchDto matchDto) {
        super(context);
        this.matchDto = matchDto;
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
        mNegativeButton.setOnClickListener(mNetralListener);

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

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
