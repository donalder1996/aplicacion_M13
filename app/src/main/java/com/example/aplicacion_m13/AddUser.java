package com.example.aplicacion_m13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;


public class AddUser extends AppCompatActivity {
    private EditText etNombre, etApellido, etDepartamento, etUsuario, etContrasena;
    private Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        etNombre = findViewById(R.id.editTextNombre);
        etApellido = findViewById(R.id.editTextApellido);
        etDepartamento = findViewById(R.id.editTextDepartamento);
        etUsuario = findViewById(R.id.editTextUsuario);
        etContrasena = findViewById(R.id.editTextContraseña);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }

        });
    }
    private void add() {
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String departamento = etDepartamento.getText().toString();
        String usuario = etUsuario.getText().toString();
        String contrasena = etContrasena.getText().toString();
        String resultado = null;

        if (usuario.isEmpty() || apellido.isEmpty()  || departamento.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Todos los campos tienen que estar llenos", Toast.LENGTH_SHORT).show();
        }

        try {
            resultado = new util.setData().execute(nombre, apellido, departamento, usuario, contrasena).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(resultado.equals("\"true\"")){
            Toast.makeText(this, "Se ha insertado correctamente.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent((getApplication()),EmpleadosLista.class);
            startActivity(intent);
        }else if(resultado.equals("\"false\"")){
            Toast.makeText(this, "No se ha podido insertar.", Toast.LENGTH_SHORT).show();
        }

    }
}
