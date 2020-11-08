package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameField,heightField,weightField;
    CheckBox rememberMeCheck;
    Spinner genderSpinner;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpSharedPrefs();
        checkPref();

    }
    public void setUpViews(){
        nameField=findViewById(R.id.nameField);
        heightField=findViewById(R.id.heightField);
        weightField=findViewById(R.id.weightField);
        rememberMeCheck=findViewById(R.id.chkRemember);
        genderSpinner=findViewById(R.id.spn);
        // genderSpinner.setSelection(1);
    }
    public void setUpSharedPrefs(){
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();
    }
    public void saveOnClick(View view) {
        boolean in =false;
        try {
            double w=Double.parseDouble(weightField.getText().toString());
            double h=Double.parseDouble(heightField.getText().toString());


        }catch(Exception e){
            Toast.makeText(this,"Please enter real values for weight and height",Toast.LENGTH_LONG).show();

            in= true;
        }

        if(nameField.getText().toString().isEmpty()||weightField.getText().toString().isEmpty()||heightField.getText().toString().isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();


        }
        else if(!in){
            if(rememberMeCheck.isChecked()){
                editor.putString("NAME",nameField.getText().toString());
                editor.putString("WEIGHT",weightField.getText().toString());
                editor.putString("HEIGHT",heightField.getText().toString());
                editor.putString("GENDER",genderSpinner.getSelectedItem().toString());
                editor.putBoolean("FLAG",true);
                editor.commit();
            }else{
                editor.putBoolean("FLAG",false);
                editor.commit();
            }

        }




    }

    public void checkPref(){
        boolean flag=pref.getBoolean("FLAG",false);
        if(flag){
            String name =pref.getString("NAME","");
            String gender=pref.getString("GENDER","");
            String height=pref.getString("HEIGHT","");
            String weight=pref.getString("WEIGHT","");
            rememberMeCheck.setChecked(true);
            nameField.setText(name);
            heightField.setText(height);
            weightField.setText(weight);

        }
    }

    public void continueOnClick(View view) {
        Intent intent=new Intent(this,MainActivity2.class);
        intent.putExtra("NAME",nameField.getText().toString());
        intent.putExtra("WEIGHT",weightField.getText().toString());
        intent.putExtra("HEIGHT",heightField.getText().toString());
        intent.putExtra("GENDER",genderSpinner.getSelectedItem().toString());


        startActivity(intent);
    }
}