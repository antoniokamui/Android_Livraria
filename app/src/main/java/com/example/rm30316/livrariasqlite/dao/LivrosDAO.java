package com.example.rm30316.livrariasqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rm30316.livrariasqlite.model.Livro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RM30316 on 05/09/2016.
 */
public class LivrosDAO {

    private final String TABELA_LIVROS = "livros";
    private final String KEY_ID = "id";
    private final String KEY_TITULO = "titulo";
    private final String KEY_AUTOR = "autor";

    private final String[] COLUNAS = {KEY_ID, KEY_AUTOR, KEY_TITULO};

    private MySQLHelper dbHelper;

    public LivrosDAO(Context context){
        dbHelper = new MySQLHelper(context);
    }

    public void add(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_TITULO, livro.getTitulo());
        valores.put(KEY_AUTOR, livro.getAutor());

        db.insert(TABELA_LIVROS, null, valores);
        db.close();
    }

    public Livro get(int id){
        Livro livro = new Livro();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABELA_LIVROS,
                                 COLUNAS,
                                " id = ? ",
                                new String[] {String.valueOf(id)}, //argumentos do select
                                null, //group by
                                null, //havig
                                null, //order by
                                null); //limit
        if(cursor != null){
            cursor.moveToFirst();
            livro.setId(cursor.getInt(0));
            livro.setAutor(cursor.getString(1));
            livro.setTitulo(cursor.getString(2));
        }
        return livro;
    }

    public List<Livro> getAll(){
        List<Livro> livros = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM "+ TABELA_LIVROS;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
         do {
             Livro livro = new Livro();
             livro.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
             livro.setTitulo(cursor.getString(cursor.getColumnIndex(KEY_TITULO)));
             livro.setAutor(cursor.getString(cursor.getColumnIndex(KEY_AUTOR)));
         }while(cursor.moveToNext());
        }
        return livros;
    }

    public void update(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_AUTOR, livro.getAutor());
        valores.put(KEY_TITULO, livro.getTitulo());
        db.update(TABELA_LIVROS,
                valores,
                KEY_ID + " = ? ",
                new String[] {String.valueOf(livro.getId())});
        db.close();
    }

    public void delete(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABELA_LIVROS,
                  KEY_ID + " = ? ",
                  new String[] {String.valueOf(livro.getId())});
        db.close();
    }
}
