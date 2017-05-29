package org.institutoserpis.ed.juanminm.proyectoed_androidstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        fromUnitSystemSpinner = (Spinner) findViewById(R.id.fromUnitSystemSpinner);
        toUnitSystemSpinner = (Spinner) findViewById(R.id.toUnitSystemSpinner);

        unitSystemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner sp = (Spinner) parent;
                String s = sp.getSelectedItem().toString();
                String[] a;

                if (s.equals(getString(R.string.length))) {
                    a = getResources().getStringArray(R.array.lengthUnits);
                } else if (s.equals(getString(R.string.area))) {
                    a = getResources().getStringArray(R.array.areaUnits);
                } else if (s.equals(getString(R.string.volume))) {
                    a = getResources().getStringArray(R.array.volumeUnits);
                } else if (s.equals(getString(R.string.temperature))) {
                    a = getResources().getStringArray(R.array.temperatureUnits);
                } else if (s.equals(getString(R.string.mass))) {
                    a = getResources().getStringArray(R.array.massUnits);
                } else {
                    a = getResources().getStringArray(R.array.timeUnits);
                }

                inputValue.setText("0");

                ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_item, a);
                adapter.notifyDataSetChanged();
                fromUnitSystemSpinner.setAdapter(adapter);
                toUnitSystemSpinner.setAdapter(adapter);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fromUnitSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String in = String.valueOf(inputValue.getText());

                convert(Double.valueOf(in));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toUnitSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String in = String.valueOf(inputValue.getText());

                convert(Double.valueOf(in));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String in = String.valueOf(inputValue.getText());

                if (in.equals("null")) {
                    inputValue.setText("0");
                } else {
                    convert(Double.valueOf(in));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected void convert(double in) {
        double ratioFrom;
        double ratioTo;
        double out;
        String system = (String) unitSystemsSpinner.getSelectedItem();
        String fromUnit = (String) fromUnitSystemSpinner.getSelectedItem();
        String toUnit = (String) toUnitSystemSpinner.getSelectedItem();

        ratioFrom = getRatio(system, fromUnit);
        ratioTo = getRatio(system, toUnit);

        out = in * ratioTo / ratioFrom;

        outputValue.setText(String.valueOf(out));
    }

    private double getRatio(String system, String unit) {
        double ratio = 1;
        if (system.equals(getString(R.string.length))) {
            if (unit.equals(getString(R.string.lengthSINanometerSymbol))) {
                ratio = Globals.METER_TO_NANOMETER;
            } else if (unit.equals(getString(R.string.lengthSIMicrometerSymbol))) {
                ratio = Globals.METER_TO_MICROMETER;
            } else if (unit.equals(getString(R.string.lengthSIMillimeterSymbol))) {
                ratio = Globals.METER_TO_MILLIMETER;
            } else if (unit.equals(getString(R.string.lengthSICentimeterSymbol))) {
                ratio = Globals.METER_TO_CENTIMETER;
            } else if (unit.equals(getString(R.string.lengthSIKilometerSymbol))) {
                ratio = Globals.METER_TO_KILOMETER;
            } else if (unit.equals(getString(R.string.lengthImpInchSymbol))) {
                ratio = Globals.METER_TO_INCH;
            } else if (unit.equals(getString(R.string.lengthImpFootSymbol))) {
                ratio = Globals.METER_TO_FOOT;
            } else if (unit.equals(getString(R.string.lengthImpYardSymbol))) {
                ratio = Globals.METER_TO_YARD;
            } else if (unit.equals(getString(R.string.lengthImpMileSymbol))) {
                ratio = Globals.METER_TO_MILE;
            }
        } else if (system.equals(getString(R.string.area))) {
            if (unit.equals(getString(R.string.areaSISquareNanometerSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUARENANOMETER;
            } else if (unit.equals(getString(R.string.areaSISquareMicrometerSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREMICROMETER;
            } else if (unit.equals(getString(R.string.areaSISquareMillimeterSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREMILLIMETER;
            } else if (unit.equals(getString(R.string.areaSISquareCentimeterSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUARECENTIMETER;
            } else if (unit.equals(getString(R.string.areaSISquareKilometerSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREKILOMETER;
            } else if (unit.equals(getString(R.string.areaImpSquareInchSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREINCH;
            } else if (unit.equals(getString(R.string.areaImpSquareFootSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREFOOT;
            } else if (unit.equals(getString(R.string.areaImpSquareYardSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREYARD;
            } else if (unit.equals(getString(R.string.areaImpSquareMileSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREMILE;
            } else if (unit.equals(getString(R.string.areaImpSquareAcreSymbol))) {
                ratio = Globals.SQUAREMETER_TO_ACRE;
            } else if (unit.equals(getString(R.string.areaImpSquareHectareSymbol))) {
                ratio = Globals.SQUAREMETER_TO_HECTARE;
            }
        } else if (system.equals(getString(R.string.volume))) {
            if (unit.equals(getString(R.string.volumeSICubicNanometerSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICNANOMETER;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_CUBICMICROMETER;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_CUBICMILLIMETER;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_CUBICCENTIMETER;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_CUBICKILOMETER;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_CUBICINCH;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_CUBICFOOT;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_MILLILITER;
            } else if (unit.equals(getString(R.string.volume))) {
                ratio = Globals.CUBICMETER_TO_LITER;
            }
        } else if (system.equals(getString(R.string.mass))) {
        } else if (system.equals(getString(R.string.temperature))) {
        } else if (system.equals(getString(R.string.temperature))) {
        }
        return ratio;
    }
}
