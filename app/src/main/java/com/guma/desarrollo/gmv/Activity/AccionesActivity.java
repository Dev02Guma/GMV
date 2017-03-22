package com.guma.desarrollo.gmv.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        setTitle("PASO 2 [ Acciones ]");

        mName = (TextView) findViewById(R.id.txtNameCliente);
        mName.setText(preferences.getString("NameClsSelected"," --ERROR--"));

        findViewById(R.id.btnCBR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccionesActivity.this,CobroInActivity.class));
            }
        });

        findViewById(R.id.btnPD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccionesActivity.this,IndicadoresClienteActivity.class));
            }
        });
        findViewById(R.id.btnRZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AccionesActivity.this,IndicadoresClienteActivity.class));
            }
        });
        findViewById(R.id.btnCV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AccionesActivity.this,AgendaActivity.class));
                finish();
            }
        });
    }
}
