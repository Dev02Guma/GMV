package com.guma.desarrollo.core;


import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by maryan.espinoza on 08/05/2017.
 */

public class Funciones {
    public static String NumberFormat(float Number){
        Log.d("", "NumberFormat: " + Number);

        DecimalFormat formatter = new DecimalFormat("#,###.##");
        Log.d("", "NumberFormat: " + formatter.format(Number));
        return  formatter.format(Number);
    }
}
