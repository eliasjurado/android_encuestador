package com.elias.www.sistemaencuesta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorEncuesta extends BaseAdapter {

    ArrayList<Encuesta_BE> ListaInterna;
    Context Contexto;

    public AdaptadorEncuesta(ArrayList<Encuesta_BE> listaInterna, Context contexto) {
        ListaInterna = listaInterna;
        Contexto = contexto;
    }

    @Override
    public int getCount() {
        return ListaInterna.size();
    }

    @Override
    public Object getItem(int position) {
        return ListaInterna.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListaInterna.get(position).getIdEncuesta();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater vi =LayoutInflater.from(Contexto);
            convertView = vi.inflate(R.layout.itemencuesta,null);
        }
        TextView otvTitulo=convertView.findViewById(R.id.TVITEM_Titulo);
        TextView otvComentario=convertView.findViewById(R.id.TVITEM_Comentario);
        TextView otvAutor=convertView.findViewById(R.id.TVITEM_Autor);

        otvTitulo.setText(ListaInterna.get(position).getTitulo());
        otvComentario.setText(ListaInterna.get(position).getComentario());
        otvAutor.setText("Autor : "+ListaInterna.get(position).getAutor());

        return convertView;
    }
}
