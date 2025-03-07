package com.example.cadastrolivros.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(
                "CREATE TABLE LIVROS (NOME VARCAHR(300), AUTOR VARCHAR(100), EDITORA VARCHAR (100), ANO INTEGER, GENERO VARCHAR (100), DESCRICAO VARCHAR (500))");
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}
