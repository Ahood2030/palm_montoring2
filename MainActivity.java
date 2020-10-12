package com.example.palm_montoring.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.palm_montoring.R;
import com.example.palm_montoring.SEARCHActivity;
import com.example.palm_montoring.SQlite.DBHelper;
import com.example.palm_montoring.SQlite.Diseasses;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
private CardView home,search,tree_add,disease,montor_palm,Doctors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper=new DBHelper(getBaseContext());
        SQLiteDatabase DB=dbHelper.getWritableDatabase();
        home= (CardView)findViewById(R.id.home);
        search= (CardView)findViewById(R.id.search_palm);
        tree_add= (CardView)findViewById(R.id.tree_add);
        disease= (CardView)findViewById(R.id.disease);
        montor_palm= (CardView)findViewById(R.id.montor_palm);
        //Doctors= (CardView)findViewById(R.id.Doctors);

        home.setOnClickListener(this);
        search.setOnClickListener(this);
        tree_add.setOnClickListener(this);
        disease.setOnClickListener(this);
        montor_palm.setOnClickListener(this);
        //Doctors.setOnClickListener(this);


        //BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);
     /*   bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent =  new Intent(getBaseContext(),MainActivity.class);
                        break;
                    case R.id.tree_add:
                        intent =  new Intent(getBaseContext(),mangeActivity.class);

                        break;
                    case R.id.montor_palm:
                        intent =  new Intent(getBaseContext(),MonitoringActivity.class);
                        break;
                    case R.id.disease:
                        intent =  new Intent(getBaseContext(),Diseasses.class);
                        break;
                   case R.id.search_palm:
                       intent =  new Intent(getBaseContext(),SEARCHActivity.class);

                       break;


                }
                startActivity(intent);
                return true;
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.home:
                intent =  new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tree_add:
                intent =  new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.montor_palm:
                intent =  new Intent(getBaseContext(), MonitoringActivity.class);
                startActivity(intent);
                break;
            case R.id.disease:
                intent =  new Intent(getBaseContext(), Diseasses.class);
                startActivity(intent);
                break;
            case R.id.search_palm:
                intent =  new Intent(getBaseContext(), SEARCHActivity.class);
                startActivity(intent);
                break;
           /* case R.id.Doctors:
                intent = new Intent(getBaseContext(),doctors.class);
                startActivity(intent);
                break;*/


        }

    }
}

