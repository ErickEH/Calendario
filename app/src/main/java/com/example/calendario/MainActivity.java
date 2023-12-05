package com.example.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_fecha, button_evento, button_Mostrar, button_Cumplir;
    EditText text_Fecha, text_evento;
    private int dia, mes, año;

    private Nodo inicio = null;
    private Nodo ultimo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_fecha= findViewById(R.id.button_fecha);
        text_Fecha = findViewById(R.id.text_fecha);
        text_evento = findViewById(R.id.text_evento);
        button_fecha.setOnClickListener(this);
        button_evento =findViewById(R.id.button_evento);
        button_Mostrar = findViewById(R.id.button_Mostrar);
        button_Cumplir = findViewById(R.id.button_Cumplir);
        TextView outputTextView = findViewById(R.id.outputTextView);
        outputTextView.setVisibility(View.INVISIBLE);
        final boolean[] invisible = {true};
        button_Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(invisible[0]){
                    outputTextView.setVisibility(View.INVISIBLE);
                    invisible[0] = false;
                }else{
                    outputTextView.setVisibility((View.VISIBLE));
                    invisible[0] = true;
                }
            }
        });
        button_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNodos();
                actualizarSalida();
            }
        });

        button_Cumplir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fechahoy=fechaHoy();
                if(inicio != null) {
                    if (fechahoy.equals(inicio.fecha)) {
                        Nodo a = inicio;
                        inicio = inicio.siguiente;
                        a=null;
                        Toast.makeText(MainActivity.this, "El evento se cumplio con exito.", Toast.LENGTH_SHORT).show();
                        actualizarSalida();
                    }else{
                        Toast.makeText(MainActivity.this, "El evento no se realizo con exito, pues la fecha todavía no se cumple.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void agregarNodos(){
        Nodo nuevoNodo = new Nodo(text_Fecha, text_evento);
        if(inicio == null){
            inicio = nuevoNodo;
            ultimo = nuevoNodo;
        }else{
            ultimo.siguiente = nuevoNodo;
            ultimo=nuevoNodo;
        }
        Toast.makeText(this, "Se creó exitosamente el evento.", Toast.LENGTH_SHORT).show();
        text_Fecha.setText("");
        text_evento.setText("");
    }
    private void actualizarSalida(){
        StringBuilder resultado = new StringBuilder();
        Nodo imp = inicio;
        while (imp != null){
            resultado.append("Nombre del evento: ").append(imp.evento).append(" Fecha: ").append(imp.fecha).append("\n");
            imp = imp.siguiente;
        }
        TextView outputTextView = findViewById(R.id.outputTextView);
        outputTextView.setText(resultado.toString());

    }

    @Override
    public void onClick(View v) {
        if( v == button_fecha){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            año = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    text_Fecha.setText(i2 + "/" + (i1 + 1) + "/" + i);
                }
            }
                    , año, mes, dia);
            datePickerDialog.show();
        }
    }

    private String fechaHoy(){
        Calendar fechaActual = Calendar.getInstance();
        int dia1, mes1, año1;
        String fecha;
        dia1 = fechaActual.get(Calendar.DAY_OF_MONTH);
        mes1 = fechaActual.get(Calendar.MONTH)+1;
        año1 = fechaActual.get(Calendar.YEAR);
        if(dia1 < 10){
            fecha = dia1+"/"+mes1+"/"+año1;
        }else{
            fecha=dia1+"/"+mes1+"/"+año1;
        }
        return fecha;
    }

}