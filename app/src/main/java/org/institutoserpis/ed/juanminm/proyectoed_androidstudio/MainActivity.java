package org.institutoserpis.ed.juanminm.proyectoed_androidstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    TextView outputValue;
    Spinner unitSystemsSpinner;
    Spinner fromUnitSystemSpinner;
    Spinner toUnitSystemSpinner;

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
        double fromRatio;
        double fromSubtract;
        double toRatio;
        double toAddition;
        double out;
        String system = (String) unitSystemsSpinner.getSelectedItem();
        String fromUnit = (String) fromUnitSystemSpinner.getSelectedItem();
        String toUnit = (String) toUnitSystemSpinner.getSelectedItem();

        fromRatio = getRatio(system, fromUnit);
        fromSubtract = getAdd(system, fromUnit);
        toRatio = getRatio(system, toUnit);
        toAddition = getAdd(system, toUnit);

        out = (in  + toAddition) * toRatio / fromRatio - fromSubtract;

        outputValue.setText(String.valueOf(out));
    }

    private double getRatio(String system, String unit) {
        double ratio = 1;
        if (system.equals(getString(R.string.length))) {
            if (unit.equals(getString(R.string.lengthSINanometerSymbol))) {
                ratio = Globals.METER_TO_NANOMETER_RATIO;
            } else if (unit.equals(getString(R.string.lengthSIMicrometerSymbol))) {
                ratio = Globals.METER_TO_MICROMETER_RATIO;
            } else if (unit.equals(getString(R.string.lengthSIMillimeterSymbol))) {
                ratio = Globals.METER_TO_MILLIMETER_RATIO;
            } else if (unit.equals(getString(R.string.lengthSICentimeterSymbol))) {
                ratio = Globals.METER_TO_CENTIMETER_RATIO;
            } else if (unit.equals(getString(R.string.lengthSIKilometerSymbol))) {
                ratio = Globals.METER_TO_KILOMETER_RATIO;
            } else if (unit.equals(getString(R.string.lengthImpInchSymbol))) {
                ratio = Globals.METER_TO_INCH_RATIO;
            } else if (unit.equals(getString(R.string.lengthImpFootSymbol))) {
                ratio = Globals.METER_TO_FOOT_RATIO;
            } else if (unit.equals(getString(R.string.lengthImpYardSymbol))) {
                ratio = Globals.METER_TO_YARD_RATIO;
            } else if (unit.equals(getString(R.string.lengthImpMileSymbol))) {
                ratio = Globals.METER_TO_MILE_RATIO;
            }
        } else if (system.equals(getString(R.string.area))) {
            if (unit.equals(getString(R.string.areaSISquareNanometerSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUARENANOMETER_RATIO;
            } else if (unit.equals(getString(R.string.areaSISquareMicrometerSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREMICROMETER_RATIO;
            } else if (unit.equals(getString(R.string.areaSISquareMillimeterSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREMILLIMETER_RATIO;
            } else if (unit.equals(getString(R.string.areaSISquareCentimeterSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUARECENTIMETER_RATIO;
            } else if (unit.equals(getString(R.string.areaSISquareKilometerSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREKILOMETER_RATIO;
            } else if (unit.equals(getString(R.string.areaImpSquareInchSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREINCH_RATIO;
            } else if (unit.equals(getString(R.string.areaImpSquareFootSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREFOOT_RATIO;
            } else if (unit.equals(getString(R.string.areaImpSquareYardSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREYARD_RATIO;
            } else if (unit.equals(getString(R.string.areaImpSquareMileSymbol))) {
                ratio = Globals.SQUAREMETER_TO_SQUAREMILE_RATIO;
            } else if (unit.equals(getString(R.string.areaImpSquareAcreSymbol))) {
                ratio = Globals.SQUAREMETER_TO_ACRE_RATIO;
            } else if (unit.equals(getString(R.string.areaImpSquareHectareSymbol))) {
                ratio = Globals.SQUAREMETER_TO_HECTARE_RATIO;
            }
        } else if (system.equals(getString(R.string.volume))) {
            if (unit.equals(getString(R.string.volumeSICubicNanometerSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICNANOMETER_RATIO;
            } else if (unit.equals(getString(R.string.volumeSICubicMicrometerSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICMICROMETER_RATIO;
            } else if (unit.equals(getString(R.string.volumeSICubicMillimeterSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICMILLIMETER_RATIO;
            } else if (unit.equals(getString(R.string.volumeSICubicCentimeterSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICCENTIMETER_RATIO;
            } else if (unit.equals(getString(R.string.volumeSICubicKilometerSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICKILOMETER_RATIO;
            } else if (unit.equals(getString(R.string.volumeImpCubicInchSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICINCH_RATIO;
            } else if (unit.equals(getString(R.string.volumeImpCubicFootSymbol))) {
                ratio = Globals.CUBICMETER_TO_CUBICFOOT_RATIO;
            } else if (unit.equals(getString(R.string.volumeImpMilliliterSymbol))) {
                ratio = Globals.CUBICMETER_TO_MILLILITER_RATIO;
            } else if (unit.equals(getString(R.string.volumeImpLiterSymbol))) {
                ratio = Globals.CUBICMETER_TO_LITER_RATIO;
            }
        } else if (system.equals(getString(R.string.mass))) {
            if (unit.equals(getString(R.string.massSIGramSymbol))) {
                ratio = Globals.KILOGRAM_TO_GRAM_RATIO;
            } else if (unit.equals(getString(R.string.massSITonSymbol))) {
                ratio = Globals.KILOGRAM_TO_TON_RATIO;
            } else if (unit.equals(getString(R.string.massImpOunceSymbol))) {
                ratio = Globals.KILOGRAM_TO_OUNCE_RATIO;
            } else if (unit.equals(getString(R.string.massImpPoundSymbol))) {
                ratio = Globals.KILOGRAM_TO_POUND_RATIO;
            }
        } else if (system.equals(getString(R.string.temperature))) {
            if (unit.equals(getString(R.string.temperatureImpFahrenheitSymbol))
                    || unit.equals(getString(R.string.temperatureImpRankineSymbol))) {
                ratio = Globals.CELSIUS_TO_FAHRENHEIT_RATIO;
            }
        } else if (system.equals(getString(R.string.time))) {
            if (unit.equals(getString(R.string.timeSINanosecondSymbol))) {
                ratio = Globals.SECOND_TO_NANOSECOND_RATIO;
            } else if (unit.equals(getString(R.string.timeSIMicrosecondSymbol))) {
                ratio = Globals.SECOND_TO_MICROSECOND_RATIO;
            } else if (unit.equals(getString(R.string.timeSIMillisecondSymbol))) {
                ratio = Globals.SECOND_TO_MILLISECOND_RATIO;
            } else if (unit.equals(getString(R.string.timeSIMinuteSymbol))) {
                ratio = Globals.SECOND_TO_MINUTE_RATIO;
            } else if (unit.equals(getString(R.string.timeSIHourSymbol))) {
                ratio = Globals.SECOND_TO_HOUR_RATIO;
            } else if (unit.equals(getString(R.string.timeSIDaySymbol))) {
                ratio = Globals.SECOND_TO_DAY_RATIO;
            } else if (unit.equals(getString(R.string.timeSIMonthSymbol))) {
                ratio = Globals.SECOND_TO_MONTH_RATIO;
            } else if (unit.equals(getString(R.string.timeSIYearSymbol))) {
                ratio = Globals.SECOND_TO_YEAR_RATIO;
            }
        }
        return ratio;
    }

    private double getAdd(String system, String unit) {
        double add = 0;

        if (system.equals(getString(R.string.temperature))) {
            if (unit.equals(getString(R.string.temperatureSIKelvinSymbol))) {
                add = Globals.CELSIUS_TO_KELVIN_ADD;
            } else if (unit.equals(getString(R.string.temperatureImpFahrenheitSymbol))) {
                add = Globals.CELSIUS_TO_FAHRENHEIT_ADD;
            } else if (unit.equals(getString(R.string.temperatureImpRankineSymbol))) {
                add = Globals.CELSIUS_TO_RANKINE_ADD;
            }
        }
        return add;
    }
}
