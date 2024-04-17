package com.example.store_games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.store_games.Controladores.JuegosDB;
import com.example.store_games.Modelos.Juegos;

public class BuscarJuegos extends AppCompatActivity implements View.OnClickListener {


    Context context;
    EditText txtTitulo;
    Button btnBuscar;
    JuegosDB juegosDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_juegos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        context = getApplicationContext();
        txtTitulo = findViewById(R.id.bus_txtTitulo);
        btnBuscar = findViewById(R.id.bus_btnBuscar);
    }

    @Override
    public void onClick(View view) {
    if (view.getId() == R.id.bus_btnBuscar);
    String titulo = txtTitulo.getText().toString();
        Juegos juegos = buscarJuegos(titulo);
        if (juegos != null){
            Bundle contenedor = new Bundle();
            contenedor.putInt("id", juegos.getId());
            contenedor.putString("titulo", juegos.getTitulo());
            contenedor.putString("genero", juegos.getGenero());
            contenedor.putString("plataforma", juegos.getPlataforma());
            contenedor.putString("desarrollador", juegos.getDesarrollador());
            contenedor.putString("descripcion", juegos.getDescripcion());
            contenedor.putInt("anioPublicacion", juegos.getAnioPublicacion());
            contenedor.putFloat("precio", juegos.getPrecio());

            Intent i = new Intent(BuscarJuegos.this,GestionarJuegos.class);
            i.putExtras(contenedor);
            startActivity(i);
        }else{
            Toast.makeText(context,"No se encontro el juego con ese nombre",Toast.LENGTH_SHORT).show();


        }
    }

    private Juegos buscarJuegos(String titulo) {
        juegosDB = new JuegosDB(context,"LibrosBD.db", null, 1);
        Juegos juegos = juegosDB.elemento(titulo);
        return juegos;
    }
}