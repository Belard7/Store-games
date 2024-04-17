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

public class GestionarJuegos extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txtTitulo, txtGenero, txtPlataforma, txtDesarrollador, txtDescripcion, txtAnioPublicacion, txtPrecio, txtCalificacion;
    int id;
    JuegosDB juegosDB;
    Button btnGuardar, btnActualizar, btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestionar_juegos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        context = getApplicationContext();
        txtTitulo = findViewById(R.id.ges_txtTitulo);
        txtDesarrollador = findViewById(R.id.ges_txtDesarrollador);
        txtGenero = findViewById(R.id.ges_txtGenero);
        txtPlataforma = findViewById(R.id.ges_txtPlataforma);
        txtDescripcion = findViewById(R.id.ges_txtDescripcion);
        txtAnioPublicacion = findViewById(R.id.ges_txtAniopublicacion);
        txtCalificacion = findViewById(R.id.ges_txtCalificacion);
        txtPrecio = findViewById(R.id.ges_txtPrecio);

        // Botones
        btnActualizar = findViewById(R.id.ges_btnActualizar);
        btnGuardar = findViewById(R.id.ges_btnguardar);
        btnBorrar = findViewById(R.id.ges_btnEliminar);

        Intent i = getIntent();
        Bundle contenedor = i.getExtras();
        id = contenedor.getInt("id");

        if (id != 0) {
            txtTitulo.setText(contenedor.getString("titulo"));
            txtGenero.setText(contenedor.getString("genero"));
            txtDesarrollador.setText(contenedor.getString("desarrollador"));
            txtPlataforma.setText(contenedor.getString("plataforma"));
            txtDescripcion.setText(contenedor.getString("descripcion"));
            txtAnioPublicacion.setText(String.valueOf(contenedor.getInt("anioPublicacion")));
            txtCalificacion.setText(String.valueOf(contenedor.getInt("calificacion")));
            txtPrecio.setText(String.valueOf(contenedor.getFloat("precio")));

            btnGuardar.setEnabled(false);
        } else {
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
        }
    }

    private void limpiarCampos() {
        id = 0;
        txtTitulo.setText("");
        txtGenero.setText("");
        txtPlataforma.setText("");
        txtDesarrollador.setText("");
        txtDescripcion.setText("");
        txtAnioPublicacion.setText("");
        txtCalificacion.setText("");
        txtPrecio.setText("");
    }

    private Juegos llenarDatosJuego() {
        Juegos juegos = new Juegos();

        String t = txtTitulo.getText().toString();
        String g = txtGenero.getText().toString();
        String pf = txtPlataforma.getText().toString();
        String d = txtDesarrollador.getText().toString();
        String dp = txtDescripcion.getText().toString();
        String year = txtAnioPublicacion.getText().toString();
        String c = txtCalificacion.getText().toString();
        String p = txtPrecio.getText().toString();

        juegos.setId(id);
        juegos.setTitulo(t);
        juegos.setGenero(g);
        juegos.setPlataforma(pf);
        juegos.setDesarrollador(d);
        juegos.setDescripcion(dp);
        juegos.setAnioPublicacion(Integer.parseInt(year));
        juegos.setCalificacion(Integer.parseInt(c));
        juegos.setPrecio(Float.parseFloat(p));

        return juegos;
    }

    private void guardar() {
        juegosDB = new JuegosDB(context, "JuegosDB.db", null, 1);
        Juegos juegos = llenarDatosJuego();
        if (id == 0) {
            juegosDB.agregar(juegos);
            Toast.makeText(context, "Guardado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            juegosDB.actualizar(id, juegos);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void borrar() {
        juegosDB = new JuegosDB(context, "JuegosDB.db", null, 1);
        Juegos juegos = llenarDatosJuego();
        if (id == 0) {
            Toast.makeText(context, "No es posible borrar", Toast.LENGTH_SHORT).show();
        } else {
            juegosDB.borrar(id);
            limpiarCampos();
            btnGuardar.setEnabled(true);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ges_btnguardar) {
            guardar();
        } else if (id == R.id.ges_btnActualizar) {
            guardar();
        } else if (id == R.id.ges_btnEliminar) {
            borrar();
        }
    }
}


