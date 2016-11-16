package com.example.maana.p08_contentprovider.clientesprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by MAÑANA on 17/10/2016.
 */

public class ClienteContactosProvider {

    private Context context;

    public ClienteContactosProvider(Context context){
        this.context = context;
    }

    public ArrayList<String> obtenerContactos(){
        ArrayList<String> listaContactos = new ArrayList<>();

        ContentResolver cr = context.getContentResolver();

        //La uri esta registrada en el contentResolver y siempre que queramos
        //invocar un contentresolver especifico deberemos hacerlo a traves de su URI
        //La de la agenda es la siguiente
        Uri contactosUri = ContactsContract.Contacts.CONTENT_URI;

        String[] columnasAMostrar = {ContactsContract.Contacts.DISPLAY_NAME};

        //
        Cursor cursor = cr.query(contactosUri,//direccion
                columnasAMostrar,//Columnas a devolver
                null,//condicion(where)
                null,//argumentos condicion
                null);//orden de los resultados

        while (cursor.moveToNext()){
            String nombre = cursor.getString(0);
            listaContactos.add(nombre);
        }

        return listaContactos;
    }
}
