package com.example.aplicacion_m13;

import static com.example.aplicacion_m13.MainActivity.*;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class util {
    public static class ValidarUsuarioTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String contrasena = params[1];
            //String url = "http://10.0.2.2/validacuenta.php"; // Reemplaza esto con la URL de tu archivo PHP
            String url = "http://10.0.2.2/validacuenta.php"; // Reemplaza esto con la URL de tu archivo PHP

            String resultado = null;
            try {
                // Crear la conexión HTTP
                URL direccion = new URL(url);
                HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);

                // Crear los datos del formulario
                String datos = "user=" + usuario + "&password=" + contrasena;

                // Escribir los datos del formulario en la solicitud HTTP
                OutputStream salida = conexion.getOutputStream();
                byte[] bytes = datos.getBytes(StandardCharsets.UTF_8);
                salida.write(bytes);
                salida.flush();
                salida.close();

                // Leer la respuesta del servidor
                InputStream entrada = conexion.getInputStream();
                BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                StringBuilder respuesta = new StringBuilder();
                String linea;

                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }

                // Cerrar la conexión HTTP
                entrada.close();
                conexion.disconnect();

                // Procesar la respuesta del servidor
                resultado = respuesta.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultado;
        }
    }


    public static class getData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            String url = "http://10.0.2.2/dataUsuario.php"; // Reemplaza esto con la URL de tu archivo PHP

            String resultado = null;
            try {
                // Crear la conexión HTTP
                URL direccion = new URL(url);
                HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);

                // Crear los datos del formulario
                String datos = "id=" + id;

                // Escribir los datos del formulario en la solicitud HTTP
                OutputStream salida = conexion.getOutputStream();
                byte[] bytes = datos.getBytes(StandardCharsets.UTF_8);
                salida.write(bytes);
                salida.flush();
                salida.close();

                // Leer la respuesta del servidor
                InputStream entrada = conexion.getInputStream();
                BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                StringBuilder respuesta = new StringBuilder();
                String linea;

                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }

                // Cerrar la conexión HTTP
                entrada.close();
                conexion.disconnect();

                // Procesar la respuesta del servidor
                resultado = respuesta.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultado;
        }


    }


    public static class setData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String nombre = params[0];
            String apellido = params[1];
            String departamento = params[0];
            String usuario = params[0];
            String contrasena = params[0];
            String url = "http://10.0.2.2/setUsuario.php";

            String resultado = null;
            try {
                // Crear la conexión HTTP
                URL direccion = new URL(url);
                HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);

                // Crear los datos del formulario
                String datos = "nombre=" + nombre + "&apellido=" + apellido + "&departamento=" + departamento + "&user=" + usuario + "&password=" + contrasena;
                // Escribir los datos del formulario en la solicitud HTTP
                OutputStream salida = conexion.getOutputStream();
                byte[] bytes = datos.getBytes(StandardCharsets.UTF_8);
                salida.write(bytes);
                salida.flush();
                salida.close();

                // Leer la respuesta del servidor
                InputStream entrada = conexion.getInputStream();
                BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                StringBuilder respuesta = new StringBuilder();
                String linea;

                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }

                // Cerrar la conexión HTTP
                entrada.close();
                conexion.disconnect();

                // Procesar la respuesta del servidor
                resultado = respuesta.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultado;
        }


    }



    public static Document convertirStringToXMLDocument(String xmlString)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
