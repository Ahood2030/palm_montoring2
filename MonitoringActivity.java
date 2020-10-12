package com.example.palm_montoring.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.palm_montoring.R;
import com.example.palm_montoring.SQlite.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.palm_montoring.SQlite.DBHelper.KEY_DATE_MONTORING;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_DATE_PLANT;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_HIMIDITY_LEVEL;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_N_LEVEL;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_PALM_NAME;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_PALM_NAME_M;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_PRODUCT;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_TABLE_PALM;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_TABLE_PALM_MONTORING;
import static com.example.palm_montoring.SQlite.DBHelper.KEY_TEMPERATURE;

public class MonitoringActivity extends AppCompatActivity {
    EditText picker,picker_plant,Palm_Name,prouduct,tempreture;
    Spinner spinner_h,spinner_n;
Button add, save;
ImageView date_plant,date_m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
//spinner for humidity
         spinner_h =  findViewById(R.id.spinner_h);
        ArrayList<String> arrayList_h = new ArrayList<>();
        arrayList_h.add("High");
        arrayList_h.add("Medidm");
        arrayList_h.add(" Low ");
        ArrayAdapter<String> arrayAdapter_h = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList_h);
        spinner_h.setAdapter(arrayAdapter_h);
        spinner_h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();

            }
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

//spinner for N
         spinner_n =  findViewById(R.id.spinner_net);
        ArrayList<String> arrayList_n= new ArrayList<>();
        arrayList_n.add("High");
        arrayList_n.add("Medium");
        arrayList_n.add(" Low ");
        ArrayAdapter<String> arrayAdapter_n= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList_h);
        spinner_n.setAdapter(arrayAdapter_n);
        spinner_n.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();

            }
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        // for planting date
        date_plant=findViewById(R.id.imageButton_plant);
        picker_plant = findViewById(R.id.date_picker_plant);

        picker_plant.setInputType(InputType.TYPE_CLASS_DATETIME);
        date_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MonitoringActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                picker_plant.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

// for monitoring date
        date_m=findViewById(R.id.image_date_m);
        picker = findViewById(R.id.date_picker);
        picker.setInputType(InputType.TYPE_CLASS_DATETIME);
        date_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MonitoringActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                picker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
//database function
       // Palm_Name=findViewById(R.id.edit_name);
        prouduct=findViewById(R.id.proud);
        tempreture=findViewById(R.id.temp);
        //add  new palm
        add=findViewById(R.id.button_add_palm);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getBaseContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_DATE_PLANT,picker_plant.getText().toString());

                long rowId = db.insert(KEY_TABLE_PALM
                        ,null,cv);
                if (rowId > 0) {
                    //5. Show a success message with the id of the newly created row
                    Toast.makeText(getBaseContext(), "Successfully Inserted, Row ID is " + rowId, Toast.LENGTH_LONG).show();
                }
                dbHelper.close();

            }
        });
        //add  update  new data fo monitoring
save=findViewById(R.id.button_save);
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DBHelper dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
       cv.put(KEY_PALM_NAME_M, Palm_Name.getText().toString());
        cv.put(KEY_DATE_MONTORING,picker.getText().toString());
        cv.put(KEY_HIMIDITY_LEVEL, spinner_h.getSelectedItem().toString());
        cv.put(KEY_N_LEVEL,spinner_n .getSelectedItem().toString());
        cv.put(KEY_TEMPERATURE, tempreture.getText().toString());
        cv.put(KEY_PRODUCT, prouduct.getText().toString());


// The result of the query is the row id if it is inserted properly
        long rowId = db.insert(KEY_TABLE_PALM_MONTORING,null,cv);
        if (rowId > 0) {
            // Show a success message with the id of the newly created row
            Toast.makeText(getBaseContext(), "Successfully Inserted, Row ID is " + rowId, Toast.LENGTH_LONG).show();
        }
        dbHelper.close();

    }
});



    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_monitoring, container, false);
    }
}
