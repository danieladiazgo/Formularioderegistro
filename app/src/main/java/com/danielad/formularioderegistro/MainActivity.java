package com.danielad.formularioderegistro;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String nombre, password, password2, correo, sexo, hobbies="", ciudad;
    private EditText eNombre, ePassword, ePassword2, eCorreo;
    private RadioButton rMasculino, rFemenino;
    private CheckBox cBailar, cDormir, cLeer, cCantar;
    private Button bAceptar;
    private TextView tInfo;
    private Spinner sCiudades;
    private Button bFecha;
    private TextView tFecha;
    private int dia=0, mes, ano;
    private int d,m,a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = (EditText) findViewById(R.id.eNombre);
        ePassword = (EditText) findViewById(R.id.ePassword);
        ePassword2 = (EditText) findViewById(R.id.ePassword2);
        eCorreo = (EditText) findViewById(R.id.eCorreo);
        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        cBailar = (CheckBox) findViewById(R.id.cBailar);
        cDormir = (CheckBox) findViewById(R.id.cDormir);
        cLeer = (CheckBox) findViewById(R.id.cLeer);
        cCantar = (CheckBox) findViewById(R.id.cCantar);
        bAceptar = (Button) findViewById(R.id.bAceptar);
        tInfo = (TextView) findViewById(R.id.tInfo);
        sCiudades = (Spinner) findViewById(R.id.sCiudades);
        bFecha = (Button) findViewById(R.id.bFecha);
        tFecha = (TextView) findViewById(R.id.tFecha);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bFecha.setOnClickListener(this);

        bAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                nombre = eNombre.getText().toString();
                password = ePassword.getText().toString();
                password2 = ePassword2.getText().toString();
                correo = eCorreo.getText().toString();
                hobbies="";


                if (rMasculino.isChecked()){
                    sexo = "Masculino";
                }else {
                    sexo= "Femenino";
                }
                if (cBailar.isChecked()){
                    hobbies += "Bailar ";
                }

                if (cCantar.isChecked()){
                    hobbies += "Cantar ";
                }

                if (cDormir.isChecked()){
                    hobbies += "Dormir ";
                }

                if (cLeer.isChecked()){
                    hobbies += "Leer ";
                }

                if (nombre.isEmpty() | password.isEmpty() | password2.isEmpty() | correo.isEmpty()| sexo.isEmpty() | hobbies.isEmpty() | ciudad.isEmpty() | dia==0){

                    tInfo.setText("Usted no ha completado todos los campos, por favor revise e intente nuevamente.");
                }
                else {

                    if (password.equals(password2)){
                        tInfo.setText("Su registro ha sido exitoso" + "\n\nUsuario: " +nombre+ "\nContraseña:" + password+"\nCorreo: "+correo+"\nSexo: "+sexo+ "\nFecha de nacimiento: "+d+"/"+m+"/"+a+" \nLugar de Nacimiento: " + ciudad+"\nHobbies: "+hobbies );
                    }else{
                        tInfo.setText("Las contraseñas no coinciden. Por favor intentelo nuevamente.");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                d=dayOfMonth;
                m=monthOfYear+1;
                a=year;
            }
        }
        ,dia,mes,ano);
        datePickerDialog.show();
    }
}
