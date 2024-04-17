package com.example.store_games.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.store_games.Modelos.Juegos;


import java.util.ArrayList;
import java.util.List;

public class JuegosDB extends SQLiteOpenHelper implements IjuegoDB {

    Context contexto;

    private List<Juegos> juegosList = new ArrayList<>();

    public JuegosDB (@Nullable Context context, @Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory, int version){

        super(context, name, factory,version);
        this.contexto = context;
    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE juegos(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT,"+
                "genero TEXT, "+
                "plataforma TEXT, " +
                "desarrollador TEXT, " +
                "descripcion TEXT, " +
                "anioPublicacion INTEGER,"+
                "precio REAL )";

        sqLiteDatabase.execSQL(sql);

        String insert = "INSERT INTO juegos VALUES (null, "+
                "'The Legend of Zelda: Breath of the Wild',"+
                "'Acción-aventura',"+
        "'Nintendo Switch, Wii U',"+
        "'Nintendo EPD',"+
        "'Embárcate en una aventura épica en el vasto mundo de Hyrule. Descubre ruinas antiguas, lucha contra monstruos feroces y resuelve acertijos para desentrañar el misterio de Calamity Ganon. ',"+
        "'2017',"+
                "'$59.99')";
        sqLiteDatabase.execSQL(insert);

        String insert2 = "INSERT INTO juegos VALUES (null," +
        "'The Witcher 3: Wild Hunt',"+
                "'RPG de acción',"+
                "'PC, PlayStation 4, Xbox One, Nintendo Switch,"+
                "CD Projekt Red,"+
                "Embárcate en una emocionante búsqueda como Geralt de Rivia, un cazador de monstruos mutante, en un vasto mundo abierto lleno de peligros y decisiones morales difíciles. Explora tierras salvajes, completa misiones épicas y forja tu propio destino en esta épica aventura de rol ,"+
                "2015,"+
                "$39.99')";
        sqLiteDatabase.execSQL(insert2);

    String sql2 = "CREATE TABLE desarrolladores ("+

    "id_desarrolador INTEGER,"+
            "nombre_desarrollador TEXT,"+
            "pais TEXT ,"+
            "anio_fundacion INTEGER )";
    sqLiteDatabase.execSQL(sql2);

    String introdu = "INSERT INTO desarrolladores VALUES (null, "+
            "'Nintendo EPD',"+
            "'Japon'" +
            "'2015')";
    sqLiteDatabase.execSQL(introdu);

        String introdu2 = "INSERT INTO desarrolladores VALUES (null, "+
                "'CD Projekt Red',"+
                "'Polonia'" +
                "'1994')";
        sqLiteDatabase.execSQL(introdu2);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Juegos elemento(int id) {
        SQLiteDatabase database = this.getReadableDatabase();//getread es solo para lectura
        String sql = "SELECT * FROM juegos WHERE id = "+id;
        Cursor cursor  = database.rawQuery(sql, null);

        //control de erroe con try catch
        try {
            if (cursor.moveToNext())
                return extraerJuego(cursor);
            else return null;//si no hay datos
        }catch (Exception e){
            Log.e("JuegoDb", "Error al extraer el juego"+e.getMessage());
            throw e;
        }finally {
            if (cursor != null) cursor.close();//cerramos el cursor
            //
        }
    }

    private Juegos extraerJuego(Cursor cursor) {
        Juegos juegos = new Juegos();
        juegos.setId(cursor.getInt(0));
        juegos.setTitulo(cursor.getString(1));
        juegos.setDescripcion(cursor.getString(2));
        juegos.setDesarrollador(cursor.getString(3));
        juegos.setGenero(cursor.getString(4));
        juegos.setPlataforma(cursor.getString(5));
        juegos.setAnioPublicacion(cursor.getInt(6));
        juegos.setPrecio(cursor.getFloat(7));
        return juegos;

    }

    @Override
    public Juegos elemento(String title) {
        SQLiteDatabase database = this.getReadableDatabase();
        String sel = "SELECT * FROM juegos WHERE nombre = '"+title+"'";
        Cursor cursor = database.rawQuery(sel, null);
        try {
            if (cursor.moveToNext())
                return extraerJuego(cursor);
            else return null;
        }catch(Exception e){
            Log.d("TAG", "Error de elemento"+e.getMessage());
            throw e;
        }finally {
            if (cursor != null) cursor.close();
        }
    }

    @Override
    public List<Juegos> lista() {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM juegos ORDER BY titulo ASC";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                juegosList.add(
                        new Juegos(

                        )
                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        return juegosList;
    }

    @Override
    public void agregar(Juegos juego) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", juego.getTitulo());
        values.put("descripcion", juego.getDescripcion());
        values.put("desarrollador", juego.getDesarrollador());
        values.put("genero", juego.getGenero());
        values.put("plataforma", juego.getPlataforma());
        values.put("yearpublicacion", juego.getAnioPublicacion());
        values.put("precio", juego.getPrecio());

        database.insert("juegos", null, values);

    }

    @Override
    public void actualizar(int id, Juegos juego) {


        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};

        ContentValues values = new ContentValues();
        values.put("nombre", juego.getTitulo());
        values.put("descripcion", juego.getDescripcion());
        values.put("desarrollador", juego.getDesarrollador());
        values.put("genero", juego.getGenero());
        values.put("plataforma", juego.getPlataforma());
        values.put("yearpublicacion", juego.getAnioPublicacion());
        values.put("precio", juego.getPrecio());

        database.update("juegos", values, "id = ?",parametros);
    }

    @Override
    public void borrar(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        database.delete("juegos", "id = ?", parametros);
    }
}
