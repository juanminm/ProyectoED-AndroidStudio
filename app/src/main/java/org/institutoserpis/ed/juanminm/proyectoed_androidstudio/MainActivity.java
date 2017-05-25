package org.institutoserpis.ed.juanminm.proyectoed_androidstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    TextView outputValue;
    Spinner unitSystemsSpinner;
    Spinner fromUnitSystemSpinner;
    Spinner toUnitSystemSpinner;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = (EditText) findViewById(R.id.inputValue);
        outputValue = (TextView) findViewById(R.id.outputValue);
        unitSystemsSpinner = (Spinner) findViewById(R.id.unitSystemsSpinner);
        fromUnitSystemSpinner =  (Spinner) findViewById(R.id.fromUnitSystemSpinner);
        toUnitSystemSpinner = (Spinner) findViewById(R.id.toUnitSystemSpinner);
        convertButton = (Button) findViewById(R.id.convertValue);

        unitSystemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Spinner sp = (Spinner) view;
                    String s =  sp.getSelectedItem().toString();
                    String[] a;

                    if(s.equals(getString(R.string.length))) {
                        a = getResources().getStringArray(R.array.lengthUnits);
                    } else if (s.equals(getString(R.string.area))) {
                        a = getResources().getStringArray(R.array.areaUnits);
                    } else if (s.equals(getString(R.string.volume))) {
                        a = getResources().getStringArray(R.array.volumeUnits);
                    } else if (s.equals(getString(R.string.temperature))) {
                        a = getResources().getStringArray(R.array.temperatureUnits);
                    } else if (s.equals(getString(R.string.mass))) {
                        a = getResources().getStringArray(R.array.massUnits);
                    } else if (s.equals(getString(R.string.time))) {
                        a = getResources().getStringArray(R.array.timeUnits);
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_spinner_item, a);
                    adapter.notifyDataSetChanged();
                    fromUnitSystemSpinner.setAdapter(adapter);
                    toUnitSystemSpinner.setAdapter(adapter);
                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            }
        )
    }
}
