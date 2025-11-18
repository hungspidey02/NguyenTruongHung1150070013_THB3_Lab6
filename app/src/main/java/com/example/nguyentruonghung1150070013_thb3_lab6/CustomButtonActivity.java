package com.example.nguyentruonghung1150070013_thb3_lab6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CustomButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_button);

        Button btnBackMoney = findViewById(R.id.btnBackMoney);
        Button btnGoLength = findViewById(R.id.btnGoLength);

        btnBackMoney.setOnClickListener(v ->
                startActivity(new Intent(CustomButtonActivity.this, MainActivity.class))
        );

        btnGoLength.setOnClickListener(v ->
                startActivity(new Intent(CustomButtonActivity.this, LengthActivity.class))
        );
    }
}
