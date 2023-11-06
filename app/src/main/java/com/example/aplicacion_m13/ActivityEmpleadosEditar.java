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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.concurrent.ExecutionException;

public class ActivityEmpleadosEditar extends AppCompatActivity {
    private Button btn;
    private TextView tvNombre, tvDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_editar);
        tvNombre = findViewById(R.id.textNombre);
        tvDatos = findViewById(R.id.textDatos);
        String resultado = "";
        String id = getIntent().getStringExtra("id");
        //Para añadir la información de la base de datos
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
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });
    }
    //Este alerDialog permite al usuario la opción de mantenerse en la pantalla actual o pasar al listado
    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción")
                .setMessage("¿Quieres permanecer en esta pantalla o ir al listado de empleados")
                .setPositiveButton("Permanecer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acción a realizar al seleccionar "Permanecer"
                        dialog.dismiss(); // Cierra el diálogo
                    }
                })
                .setNegativeButton("Ir a listado", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acción a realizar al seleccionar "Ir a la siguiente pantalla
                        Intent intent = new Intent((getApplication()),EmpleadosLista.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}