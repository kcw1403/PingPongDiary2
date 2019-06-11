package kcat.org.pingpongdiary;

import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Dictionary;

public class WriteMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner handSpinner;
    Spinner fRubberSpinner;
    Spinner bRubberSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    String[] rubberTypeItem = new String[]{"평면러버","숏핌플","롱핌플","안티러버"};

    String name;
    String clubName;
    int rank;
    int handy;
    int hand;
    int frontRubber;
    int backRubber;
    int winSet;
    int roseSet;
    String review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_match);

        handSpinner = (Spinner) findViewById(R.id.handType);
        fRubberSpinner = (Spinner)findViewById(R.id.front_rubber);
        bRubberSpinner = (Spinner)findViewById(R.id.back_rubber);

        handSpinner.setOnItemSelectedListener(this);
        fRubberSpinner.setOnItemSelectedListener(this);
        bRubberSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,handTypeItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handSpinner.setAdapter(adapter);
        ArrayAdapter<String> rubber_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,rubberTypeItem);
        rubber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fRubberSpinner.setAdapter(rubber_adapter);
        bRubberSpinner.setAdapter(rubber_adapter);

        findViewById(R.id.saveMatchBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name =((EditText)findViewById(R.id.nameBox)).getText().toString();
                clubName =((EditText)findViewById(R.id.clubNameBox)).getText().toString();

                if(!(((EditText) findViewById(R.id.rankBox)).getText().toString().equals("")))
                    rank = Integer.parseInt(((EditText) findViewById(R.id.rankBox)).getText().toString());
                else
                    rank = 100;

                if(!(((EditText) findViewById(R.id.handyScore)).getText().toString().equals("")))
                    handy = Integer.parseInt(((EditText) findViewById(R.id.rankBox)).getText().toString());
                else
                    handy = 0;

                hand = handSpinner.getSelectedItemPosition();
                frontRubber = fRubberSpinner.getSelectedItemPosition();
                backRubber = bRubberSpinner.getSelectedItemPosition();


                review =((EditText)findViewById(R.id.reviewBox)).getText().toString();

                Toast.makeText(WriteMatchActivity.this,String.valueOf(hand)+","+String.valueOf(frontRubber)+","+String.valueOf(backRubber),Toast.LENGTH_SHORT).show();
                if(name.equals(""))
                {
                    Toast.makeText(WriteMatchActivity.this,"이름을 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == handSpinner.getId())
        {
            Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
