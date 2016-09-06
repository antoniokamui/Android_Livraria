package com.example.rm30316.livrariasqlite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RM30316 on 05/09/2016.
 */
public class MySQLHelper extends SQLiteOpenHelper{

    public MySQLHelper(Context context){
        super(context, "livros", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            String criar_tabela_livros = "CREATE TABLE Livros ("+
                                        "id Integer primary key autoincrement, "+
                                        "titulo text, "+
                                        "autor text)";
            db.execSQL(criar_tabela_livros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    this.onCreate(db);
    }
}
