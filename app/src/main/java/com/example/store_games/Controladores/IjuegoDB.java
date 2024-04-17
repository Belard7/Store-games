package com.example.store_games.Controladores;
import com.example.store_games.Modelos.Juegos;

import java.util.List;

public interface IjuegoDB {
    //metodos

    Juegos elemento(int id);
    Juegos elemento (String title);
    List<Juegos> lista();

    void agregar(Juegos juego);
    void actualizar(int id, Juegos juego);
    void borrar(int id);


}
