package com.example.maana.p08_contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maana.p08_contentprovider.clientesprovider.ClienteContactosProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContactos = (ListView)findViewById(R.id.lvContactors);
    }

    public void mostrarContactos(View v){
        // Here, thisActivity is the current activity
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        100);

            } else {
                pedirContactos();
            }
        }else{
            pedirContactos();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pedirContactos();
                } else {
                    Toast.makeText(this,"No se pueden mostrar los contactos",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void pedirContactos(){

        ClienteContactosProvider ccp = new ClienteContactosProvider(this);
        ArrayList<String> listaContactos = ccp.obtenerContactos();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listaContactos
        );

        lvContactos.setAdapter(arrayAdapter);
    }

}
