package com.example.aplicacion_m13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        private void iniciarActividad(){
            String usuario = etUsuario.getText().toString();
            String contrasena = etContraseña.getText().toString();
            //Si la opción primera o segunda está vacío mostrar un mensaje mediante toast
            if (usuario.isEmpty() && contrasena.isEmpty()) {
                Toast.makeText(this, "campos en blanco", Toast.LENGTH_SHORT).show();

            }
            if (usuario.isEmpty()) {
                Toast.makeText(this, "usuario en blanco", Toast.LENGTH_SHORT).show();
            }
            if (contrasena.isEmpty()) {
                Toast.makeText(this, "contraseña en blanco", Toast.LENGTH_SHORT).show();


            }

        }

}