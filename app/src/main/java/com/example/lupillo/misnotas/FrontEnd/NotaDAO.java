package com.example.lupillo.misnotas.FrontEnd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lupillo.misnotas.BackEnd.Conexion;
import com.example.lupillo.misnotas.BackEnd.Nota;

public class NotaDAO {
    //Adaptador para la base de datos
    private SQLiteDatabase adaptador;

    public NotaDAO(Context context){
        //Hace que el adaptador pueda leer y exribir en la base de datos
        adaptador = new Conexion(context).getWritableDatabase();

    }
    public long Agregar(Nota objNota){
        ContentValues content = new ContentValues();
        //Decimos que vamos a agregar
        //primer parametro = columa
        //segundo parametro = valor
        content.put(Conexion.COLUMNAS_NOTA[0],objNota.getTitulo());
        content.put(Conexion.COLUMNAS_NOTA[1],objNota.getDescripcion());
        content.put(Conexion.COLUMNAS_NOTA[2],objNota.getArchivoMultimedia());
        content.put(Conexion.COLUMNAS_NOTA[3],objNota.getClasificacion());
        content.put(Conexion.COLUMNAS_NOTA[4],objNota.getFechaTerminacion());
        content.put(Conexion.COLUMNAS_NOTA[5],objNota.getRecordatorio());
        content.put(Conexion.COLUMNAS_NOTA[6],objNota.getCumplida());
        content.put(Conexion.COLUMNAS_NOTA[7],objNota.getFechaRegistro());

        //insertamos
        //si inserta devuelve el id insertado si no devuelve -1
        return adaptador.insert(Conexion.TABLES_DB[0],null,content);

    }

    public long Eliminar(Nota n){
        return adaptador.delete(
                Conexion.TABLES_DB[0],
                Conexion.COLUMNAS_NOTA[0]+"='"+n.getTitulo()+"'&&" +
                        Conexion.COLUMNAS_NOTA[1]+"='"+n.getDescripcion()+"'&&" +
                        Conexion.COLUMNAS_NOTA[2]+"='"+n.getArchivoMultimedia()+"'&&" +
                        Conexion.COLUMNAS_NOTA[3]+"='"+n.getClasificacion()+"'&&" +
                        Conexion.COLUMNAS_NOTA[4]+"='"+n.getFechaTerminacion()+"'&&" +
                        Conexion.COLUMNAS_NOTA[5]+"='"+n.getRecordatorio()+"'&&" +
                        Conexion.COLUMNAS_NOTA[6]+"='"+n.getCumplida()+"'&&" +
                        Conexion.COLUMNAS_NOTA[7]+"='"+n.getFechaRegistro()+"'&&",
                null);
    }

    public Cursor Buscar(String Titulo){
        String sql="nombre='"+ Titulo+"'";
        Cursor c = adaptador.query(Conexion.TABLES_DB[0],
                Conexion.COLUMNAS_NOTA,
                sql,
                null,
                null,
                null,
                null);
        return c;
    }
}
