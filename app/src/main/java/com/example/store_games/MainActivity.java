package com.example.store_games;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    Button btnListar,btnRegistrar,btnBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        init();
    }
    private void init(){
        context = getApplicationContext(); //accede a los recursos y funciones
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnListar = findViewById(R.id.btnListar);
    }

    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.btnRegistrar){
            Toast.makeText(context,"Registrar", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context,GestionarJuegos.class);

            Bundle contenedor = new Bundle();

            contenedor.putInt("id", 0);
            i.putExtras(contenedor);

            startActivity(i);


        } else if (id == R.id.btnListar) {
            Toast.makeText(context,"Listar", Toast.LENGTH_SHORT).show();
            Intent i2 = new Intent(context, ListadoJuegos.class);
            startActivity(i2);
        }else if (id ==R.id.btnBuscar){
            Toast.makeText(context,"Buscar",Toast.LENGTH_SHORT).show();
            Intent i3 = new Intent(context, BuscarJuegos.class);
            startActivity(i3);
        }
    }
}