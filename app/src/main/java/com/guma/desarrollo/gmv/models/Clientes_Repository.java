package com.guma.desarrollo.gmv.models;

import android.util.Log;

import com.guma.desarrollo.core.Articulo;
import com.guma.desarrollo.core.Articulos_model;
import com.guma.desarrollo.core.Clientes;
import com.guma.desarrollo.core.Clientes_model;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.gmv.Activity.ReferenciasContexto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maryan.espinoza on 27/02/2017.
 */

public class Clientes_Repository {
    private static Clientes_Repository repository = new Clientes_Repository();
    private HashMap<String, Clientes> hashMap = new HashMap<>();
    public static Clientes_Repository getInstance() {
        return repository;
    }

    private Clientes_Repository() {
        for(Clientes obj : Clientes_model.getClientes(ManagerURI.getDirDb(), ReferenciasContexto.getContextArticulo())) {
            saveLead(new Clientes(obj.getmCliente(), obj.getmNombre(), "","","","","","",""));
            Log.d("", "Clientes_Repository: " + obj.getmCliente());
        }
    }


    private void saveLead(Clientes clientes) {hashMap.put(clientes.getmCliente(), clientes);   }

    public List<Clientes> getArticulos() {
        List<Clientes> OrderArticulo= new ArrayList<>(hashMap.values());
        Collections.sort(OrderArticulo, new Comparator<Clientes>(){
            public int compare(Clientes obj1, Clientes obj2)
            {
                return obj1.getmNombre().compareToIgnoreCase(obj2.getmNombre());
            }
        });
        return OrderArticulo;
    }
}
