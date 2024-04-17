package com.example.store_games.Modelos;

public class Juegos {

    private int id;
    private String titulo,genero, plataforma, desarrollador,descripcion;
    private int anioPublicacion,calificacion;
    private float  precio;


    //Constructor vacio
    public Juegos() {

    }
    //Contructor con argumentos crea una instancia correcta
    public Juegos(int id,int precio, String titulo, String genero, String plataforma, String desarrollador,String descripcion, int anioPublicacion, int calificacion){
    this.id = id;
    this.titulo = titulo;
    this.genero = genero;
    this.plataforma = plataforma;
    this.desarrollador = desarrollador;
    this.descripcion =  descripcion;
    this.anioPublicacion = anioPublicacion;
    this.calificacion = calificacion;
    this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    //click derecho generate y se generan
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Juegos{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", desarrollador='" + desarrollador + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", calificacion=" + calificacion +
                '}';
    }
}
