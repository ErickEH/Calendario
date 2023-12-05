package com.example.calendario;

import android.widget.EditText;

public class Nodo {
        public String evento;
        public String fecha;
        public Nodo siguiente;
public Nodo(EditText text_fecha, EditText text_evento){
        evento = text_evento.getText().toString();
        fecha = text_fecha.getText().toString();

        siguiente = null;
}
}
