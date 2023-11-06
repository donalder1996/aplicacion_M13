package com.example.aplicacion_m13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etContraseña;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsuario = findViewById(R.id.editTextNombre);
        etContraseña = findViewById(R.id.editTextContra);
        btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad();

            }

        });
    }

    private void iniciarActividad() {
        String usuario = etUsuario.getText().toString();
        String contrasena = etContraseña.getText().toString();
        String resultado = null;

        //Si la opción primera o segunda está vacío mostrar un mensaje mediante toast
        if (usuario.isEmpty() && contrasena.isEmpty()) {
            Toast.makeText(this, "campos en blanco", Toast.LENGTH_SHORT).show();

        } else if (usuario.isEmpty()) {
            Toast.makeText(this, "usuario en blanco", Toast.LENGTH_SHORT).show();
        } else if (contrasena.isEmpty()) {
            Toast.makeText(this, "contraseña en blanco", Toast.LENGTH_SHORT).show();


        }

        try {
            resultado = new util.ValidarUsuarioTask().execute(usuario, contrasena).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Si el resultado es null significa que no se ha conectado con la base de datos,
        // si es "null" significa que la consulta no ha encontrado datos
        if(resultado.equals("\"noConnection\"")){
            Toast.makeText(this, "No se ha podido conectar con la Base de Datos.", Toast.LENGTH_SHORT).show();
        }else if (!resultado.equals("\"noData\"")){
            Document doc = util.convertirStringToXMLDocument(resultado);
            NodeList listaId = doc.getElementsByTagName("id");
            NodeList listaRoot = doc.getElementsByTagName("admin");
            String id = listaId.item(0).getTextContent();
            String root = listaRoot.item(0).getTextContent();

            //Esto lanzaría el ectivity de la información del empleado
            if(root.equals("0")){
                Intent intent = new Intent(this, ActivityEmpleados.class);
                intent.putExtra("id", id); // Agrega el ID como dato extra
                startActivity(intent);
                finish();

            }else if (root.equals("1")){
                Intent intent1 = new Intent(this, ActivityEmpleadosEditar.class);
                intent1.putExtra("id", id); // Agrega el ID como dato extra
                startActivity(intent1);
                finish();

            }else {
                Toast.makeText(this, "No se ha podido iniciar sesion.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }

    }

}

