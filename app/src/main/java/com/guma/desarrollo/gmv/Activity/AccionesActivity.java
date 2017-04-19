package com.guma.desarrollo.gmv.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.gmv.R;

public class AccionesActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    TextView mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        //editor.putString("BANDERA","1").apply();
        String bandera = preferences.getString("BANDERA", "0");
        setTitle("PASO 2 [ Acciones ]");
        findViewById(R.id.btnCV).setVisibility(View.GONE);

        if (bandera.equals("1")){
            findViewById(R.id.btnRZ).setVisibility(View.GONE);
        }if (bandera.equals("1")|| bandera.equals("2")){
            findViewById(R.id.btnCV).setVisibility(View.VISIBLE);
        }


        mName = (TextView) findViewById(R.id.txtNameCliente);
        mName.setText(preferences.getString("NameClsSelected"," --ERROR--"));

        findViewById(R.id.btnCBR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccionesActivity.this,CobroInActivity.class));
                finish();
            }
        });

        findViewById(R.id.btnPD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("IDPEDIDO","").apply();
                startActivity(new Intent(AccionesActivity.this,IndicadoresClienteActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnRZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccionesActivity.this,RazonesActivity.class));
                finish();
            }
        });
        findViewById(R.id.btnCV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarPref();
                startActivity(new Intent(AccionesActivity.this,AgendaActivity.class));
                finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            limpiarPref();
            startActivity(new Intent(AccionesActivity.this,AgendaActivity.class));
            finish();
            return true;
        }
        return false;
    }
    public void limpiarPref(){
        editor.putString("LATITUD", "0.0");
        editor.putString("LONGITUD", "0.0");
        editor.putString("LUGAR_VISITA", "");
        editor.putString("BANDERA","0");
        editor.apply();
    }
}
