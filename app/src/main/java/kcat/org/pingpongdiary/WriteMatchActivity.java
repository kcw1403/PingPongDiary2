package kcat.org.pingpongdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class WriteMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner handSpinner;
    String[] handTypeItem = new String[]{"오른손","왼손"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_match);

        handSpinner = (Spinner) findViewById(R.id.handType);


        handSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,handTypeItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        handSpinner.setAdapter(adapter);
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
