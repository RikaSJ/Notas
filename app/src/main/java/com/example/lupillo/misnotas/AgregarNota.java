package com.example.lupillo.misnotas;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lupillo.misnotas.BackEnd.Nota;
import com.example.lupillo.misnotas.FrontEnd.NotaDAO;

import java.util.Calendar;
import java.util.Date;

public class AgregarNota extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtTitulo;
    EditText txtDescripcion;
    EditText txtFecha;
    Spinner spinTipo;
    Button btnFecha;
    DatePickerDialog datePickerDialog;

    Toolbar toolbar;



    int anio;
    int mes;
    int dia;
    Calendar calendario;
    Date fecha;
    String s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarnota);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        spinTipo=findViewById(R.id.spnTipo);
        txtFecha=findViewById(R.id.txtFecha);
        btnFecha=findViewById(R.id.btnFecha);
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                anio=calendario.get(Calendar.YEAR);
                mes=calendario.get(Calendar.MONTH);
                dia=calendario.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(AgregarNota.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                if(year>=anio){
                                    if(month>=mes){
                                        if(dayOfMonth>=dia){
                                            txtFecha.setText(dayOfMonth+"/"+month+"/"+year);
                                        }else{
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "El día debe ser igual o mayor al día actual",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "El mes debe ser igual o mayor al mes actual",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "El año debe ser igual o mayor al año actual",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        },anio,mes,dia);
                datePickerDialog.show();
            }
        });

        //Recuperar el Intent del MainActivity
        s=(String)getIntent().getSerializableExtra("kaka");
        llenarSpin();

    }

    //Metodo para cuando se selecciona un item del spiner tipo de sus 2 opciones (tarea o nota)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        Toast.makeText(parent.getContext(),parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



    public void btnAgregar_click(View v){
        NotaDAO ado =new NotaDAO(getApplicationContext());
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
        /*long result = ado.Agregar(
                new Nota(txtTitulo.getText().toString(),
                        txtDescripcion.getText().toString(),

                )
        );
        if (result>0){
            Toast.makeText(this, "Adición exitosa",Toast.LENGTH_LONG).show();
        }*/

        //finish() se utiliza para terminar el intent del MainActivity.java
        finish();

    }

    public void btnEliminar_click(View v){
        NotaDAO dao=new NotaDAO(this);
        /*
        if(dao.Eliminar(txtTitulo.getText().toString())!=0){
            Toast.makeText(this, "Eliminación exitosa!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Algo salió mal.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void btnBuscar_click(View v){
        Cursor C;
        NotaDAO ado = new NotaDAO(getApplicationContext());
        String Nombre =txtTitulo.getText().toString();
        if(!Nombre.isEmpty()){
            C=ado.Buscar(Nombre);
            if(C.moveToFirst()){
                txtTitulo.setText(C.getString(0));
                Toast.makeText(this,"Búsqueda existosa!",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Ningún elemento encontrado!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Ingrese un nombre para buscar",Toast.LENGTH_LONG).show();
        }
    }

    public void llenarSpin(){
        String datos []=new String[2];
        datos[0]="Nota";
        datos[1]="Tarea";
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        spinTipo.setAdapter(adp);
        spinTipo.setOnItemSelectedListener(this);
    }
}