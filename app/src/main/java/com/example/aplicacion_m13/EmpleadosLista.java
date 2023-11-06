package com.example.aplicacion_m13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SimpleAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.HashMap;
import java.util.ArrayList;

public class EmpleadosLista extends AppCompatActivity {
    private ListView lvList;
    List<usuario> lst, lista;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_lista);
        String resultado = "";
        String id = "";
        lvList = findViewById(R.id.listaListView);
        try {
            resultado = new util.getData().execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Document doc = util.convertirStringToXMLDocument(resultado);
        NodeList registros = doc.getElementsByTagName("registro");

        HashMap<String, String> diccionarioUsuarios = new HashMap<>();
        lista=new ArrayList<>();
        for (int i = 0; i < registros.getLength(); i++) {
            Node registro = registros.item(i);
            if (registro.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) registro;
                String nombre = (elemento.getElementsByTagName("nombre")
                        .item(0).getTextContent())
                        + " " +
                        elemento.getElementsByTagName("apellido")
                                .item(0).getTextContent();
                String departamento = elemento.getElementsByTagName("departamento").item(0).getTextContent();

                // Usar "nombre + apellido" como clave y "departamento" como valor
                diccionarioUsuarios.put(nombre, departamento);
                lista.add(new usuario(nombre,departamento));

            }
        }

        lst=new ArrayList<>();
        lst.add(new usuario("holaaa","adiossss"));
        lst.add(new usuario("123","gdfsgsd"));
        lst.add(new usuario("123","gdfsgsd"));
        lst.add(new usuario("123","gdfsgsd"));
        lst.add(new usuario("123","gdfsgsd"));

        CustomAdapter adapter=new CustomAdapter(this, lista);
        lvList.setAdapter(adapter);
        btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((getApplication()),AddUser.class);
                startActivity(intent);
            }

        });
    }

    private List<usuario> setList() {
        lst=new ArrayList<>();
        lst.add(new usuario("holaaa","adiossss"));
        lst.add(new usuario("123","gdfsgsd"));
        lst.add(new usuario("123","gdfsgsd"));
        lst.add(new usuario("123","gdfsgsd"));
        lst.add(new usuario("123","gdfsgsd"));
        return lst;
    }

}