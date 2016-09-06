package com.example.rm30316.livrariasqlite.model;

/**
 * Created by RM30316 on 05/09/2016.
 */
public class Livro {

    public Livro(){

    }

    public Livro(String titulo, String autor){

        this.titulo = titulo;
        this.autor = autor;
    }


    private int id;
    private String titulo;
    private String autor;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
