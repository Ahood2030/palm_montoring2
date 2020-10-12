package com.example.palm_montoring.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.palm_montoring.Activity.ViewActivity;
import com.example.palm_montoring.R;
import com.example.palm_montoring.SQlite.view_palm_disActivity;

public class HomeActivity extends AppCompatActivity {
Button view_m,View_d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        view_m=findViewById(R.id.button_m);
        View_d=findViewById(R.id.button_d);
        view_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getBaseContext(), ViewActivity.class);
                startActivity(intent);
            }
        });
        View_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =  new Intent(getBaseContext(), view_palm_disActivity.class);
                startActivity(intent);

            }
        });
    }
}
