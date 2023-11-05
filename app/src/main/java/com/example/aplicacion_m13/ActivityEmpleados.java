package com.example.aplicacion_m13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityEmpleados extends AppCompatActivity {
    private TextView tvNombre, tvDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
        tvNombre = findViewById(R.id.textNombre);
        tvDatos = findViewById(R.id.textNombre);
        //Para añadir la información de la base de datos
        //tvNombre.setText(prueba);
        //tvDatos.setText();
    }



}