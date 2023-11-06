package com.example.aplicacion_m13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.concurrent.ExecutionException;

public class ActivityEmpleados extends AppCompatActivity {
    private TextView tvNombre, tvDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
        tvNombre = findViewById(R.id.textNombre);
        tvDatos = findViewById(R.id.textDatos);
        String resultado = "";
        String id = getIntent().getStringExtra("id");
        try {
            resultado = new util.getData().execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Document doc = util.convertirStringToXMLDocument(resultado);
        NodeList listaNombre = doc.getElementsByTagName("nombre");
        NodeList listaApellido = doc.getElementsByTagName("apellido");
        NodeList listaDepartamento = doc.getElementsByTagName("departamento");
        String nombre = listaNombre.item(0).getTextContent();
        String apellido = listaApellido.item(0).getTextContent();
        String departamento = listaDepartamento.item(0).getTextContent();
        tvNombre.setText(nombre + " " + apellido);
        tvDatos.setText(departamento);
    }



}