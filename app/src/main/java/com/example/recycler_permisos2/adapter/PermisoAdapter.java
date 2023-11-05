package com.example.recycler_permisos2.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_permisos2.R;
import com.example.recycler_permisos2.models.Permiso;

import java.util.List;
//



public class PermisoAdapter extends RecyclerView.Adapter <PermisoAdapter.ViewHolder> {


    private List<Permiso> LP;

    public PermisoAdapter(List<Permiso> lp) {
        this.LP = lp;
    }


    @NonNull
    @Override
    public PermisoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v= layoutInflater.inflate(R.layout.item, parent, false) ;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PermisoAdapter.ViewHolder holder, int position) {
        Permiso p= LP.get(position);
        holder.SetData(p);

        Context context = holder.itemView.getContext();


        holder.swPermiso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(p.getNombre().equals("Llamada")){

                        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_DENIED){

                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1987);

                        }else{
                            Intent intentcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:871-521-5680"));
                            context.startActivity(intentcall);
                        }

                    }

                } else {
                    // El Switch está desactivado (apagado)
                    // Realiza acciones relacionadas con el Switch desactivado aquí
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return LP.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        Permiso PermisoH;

        TextView txtPermiso;
        TextView txtDesc;
        Switch swPermiso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPermiso=itemView.findViewById(R.id.tvPermiso);
            txtDesc=itemView.findViewById(R.id.tvDesc);

        }

        public void SetData(Permiso p){
            this.PermisoH= p;
            txtPermiso.setText(p.getNombre());
            txtDesc.setText(p.getDescripcion());


        }
    }
}
