package com.example.lupillo.misnotas.BackEnd;
import android.content.Context;
import android.database.sqlite.*;

public class Conexion extends SQLiteOpenHelper {
    //La clase tiene que heredar de SQLiteOpenHelper
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME="MisNotas2.db";

    //Columnas de la tabla NOTA
    public static final String [] COLUMNAS_NOTA = {
            "Titulo",
            "Descripcion",
            "ArchivoMultimedia",
            "Clasificacion",
            "FechaTerminacion",
            "Recordatorio",
            "Cumplida",
            "FechaResistro"
    };
    //Tabla NOTA
    public static final String [] TABLES_DB = {"NOTA"};

    //Scrip para crear la tabla en la base de datos
    public static final String SQL_CREAR=
            "create table NOTA (" +
                    "Titulo" + "text not null," +
                    "Descripcion" + "text not null," +
                    "ArchivoMultimedia" + "text not null," +
                    "Clasificacion" + "text not null," +
                    "FechaTerminacion" + "text not null," +
                    "Recordatorio" + "text not null," +
                    "Cumplida" + "text not null," +
                    "FechaResistro" + "text not null);";


    public void onCreate(SQLiteDatabase db){
        //Ejecutamos la sentencia
        db.execSQL(SQL_CREAR);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public Conexion(Context context){
        //el parametro null hace referencia a un Cursor
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
