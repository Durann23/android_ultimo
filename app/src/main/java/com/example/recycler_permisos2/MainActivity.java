package com.example.recycler_permisos2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.recycler_permisos2.adapter.PermisoAdapter;
import com.example.recycler_permisos2.models.Permiso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Permiso> ListaPermisos = new ArrayList<>();
        ListaPermisos.add(new Permiso("Camara","a"));
        ListaPermisos.add(new Permiso("Llamada","a"));




        PermisoAdapter pa = new PermisoAdapter(ListaPermisos);
        RecyclerView rvPermiso=findViewById(R.id.rvPermiso);
        rvPermiso.setAdapter(pa);
        rvPermiso.setLayoutManager(new LinearLayoutManager(this));
        rvPermiso.setHasFixedSize(true);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1987) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso de llamada otorgado, realiza las acciones necesarias
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:871-521-5680"));
                startActivity(intentCall);
            }
        } else if (requestCode==1988) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso de llamada otorgado, realiza las acciones necesarias
                Intent intentcall = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intentcall);
            }

        }
    }


}