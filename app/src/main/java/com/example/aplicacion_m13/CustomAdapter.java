package com.example.aplicacion_m13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<usuario> lst;

    public CustomAdapter(Context context, List<usuario> lst) {
        this.context = context;
        this.lst = lst;
    }


    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        TextView TextViewNombre;
        TextView TextViewDepartamento;

        usuario u=lst.get(i);
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.listview_usuario, null);
        }

        TextViewNombre=view.findViewById(R.id.textViewNombre);
        TextViewDepartamento=view.findViewById(R.id.textViewDepartamento);

        TextViewNombre.setText(u.nombre);
        TextViewDepartamento.setText(u.departamento);
        return view;
    }
}
