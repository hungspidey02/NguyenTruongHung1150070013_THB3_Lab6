package com.example.nguyentruonghung1150070013_thb3_lab6;

import android.content.Intent;
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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LengthActivity extends AppCompatActivity {

    private String[] lengthUnits = {
            "Hải lý", "Dặm", "Kilometer", "Lý",
            "Met", "Yard", "Foot", "Inch"
    };

    // ma trận tỉ lệ theo bảng đề
    private double[][] lengthRatio = {
            {1.0000000, 1.15077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.38583},
            {0.86897624, 1.0000000, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.00000},
            {0.53995680, 0.62137119, 1.0000000, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.07874},
            {0.04937365, 0.05681818, 0.0914400, 1.0000000, 91.44000, 100.000000, 300.000000, 3600.000000},
            {0.00053996, 0.00062137, 0.0010000, 0.01093613, 1.0000000, 1.09361300, 3.28084000, 39.3700800},
            {0.00049374, 0.00056818, 0.0009144, 0.01000000, 0.9144000, 1.00000000, 3.00000000, 36.0000000},
            {0.00016458, 0.00018939, 0.0003048, 0.00333333, 0.3048000, 0.33333333, 1.00000000, 12.0000000},
            {0.00001371, 0.00001578, 0.0000254, 0.00027778, 0.0254000, 0.02777778, 0.08333333, 1.00000000}
    };

    private EditText txtLength;
    private Spinner spnLengthUnits;
    private TextView[] lblResults;
    private Button btnBackMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_length);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtLength = findViewById(R.id.txtLength);
        spnLengthUnits = findViewById(R.id.spnLengthUnits);
        btnBackMoney = findViewById(R.id.btnBackMoney);

        lblResults = new TextView[] {
                findViewById(R.id.lblHaily),
                findViewById(R.id.lblDam),
                findViewById(R.id.lblKm),
                findViewById(R.id.lblLy),
                findViewById(R.id.lblMet),
                findViewById(R.id.lblYard),
                findViewById(R.id.lblFoot),
                findViewById(R.id.lblInch)
        };

        // adapter cho spinner độ dài
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                lengthUnits
        );
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnLengthUnits.setAdapter(adapter);

        // đổi khi chọn đơn vị
        spnLengthUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeLengthUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // đổi khi nhập số
        txtLength.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeLengthUnit();
            }
        });

        // nút quay về màn đổi tiền
        btnBackMoney.setOnClickListener(v -> finish());

        changeLengthUnit();
    }

    private void changeLengthUnit() {
        int row = spnLengthUnits.getSelectedItemPosition();
        if (row < 0) row = 0;

        String input = txtLength.getText().toString().trim();
        if (input.isEmpty()) input = "0";

        double number;
        try {
            number = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            number = 0;
        }

        for (int i = 0; i < lblResults.length; i++) {
            double value = number * lengthRatio[row][i];
            lblResults[i].setText(String.format("%.4f", value));
        }
    }
}
