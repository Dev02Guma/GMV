package com.guma.desarrollo.gmv.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.guma.desarrollo.core.Clientes;
import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.Facturas;
import com.guma.desarrollo.core.Indicadores;
import com.guma.desarrollo.core.Clientes_model;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.gmv.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class IndicadoresClienteActivity extends AppCompatActivity {

    private float[] yData = {90f, 10f};
    private String[] xData = {"Mitch", "Mohammad" };

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private float TotalPuntos;

    PieChart pieChart;

    TextView mpVenta,mItemFact,mLimite,mCredito,mNombre,mPuntos;

    TextView textView;

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicadores_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        textView = (TextView) findViewById(R.id.idTimer);
        mpVenta = (TextView) findViewById(R.id.txtPromedio);
        mItemFact = (TextView) findViewById(R.id.txtCantItem);
        final Button btnOK = (Button) findViewById(R.id.btnIndicadores);
        timer = new Timer();

        mLimite = (TextView) findViewById(R.id.txtLimite);
        mCredito = (TextView) findViewById(R.id.txtCredito);
        mNombre = (TextView) findViewById(R.id.txtIdNombre);
        mPuntos = (TextView) findViewById(R.id.txtPuntos);

        List<Indicadores> obj = Clientes_model.getIndicadores(ManagerURI.getDirDb(), IndicadoresClienteActivity.this,preferences.getString("ClsSelected"," --ERROR--"));
        setTitle(" [ PASO 3 - Pedido ] " + preferences.getString("NameClsSelected"," --ERROR--"));
        if (obj.size()>0) {
            mNombre.setText(obj.get(0).getmNombre() );
            mpVenta.setText(obj.get(0).getmPromedioVenta3M());
            mItemFact.setText(obj.get(0).getmCantidadItems3M());
            Log.d("", "mMetas: " + obj.get(0).getmMetas() + " > " + obj.get(0).getmVentasActual());
        }

        final List<Clientes> obClientes = Clientes_model.getInfoCliente(ManagerURI.getDirDb(), IndicadoresClienteActivity.this,preferences.getString("ClsSelected","0"));
        if (obClientes.size()>0){
            //setTitle("PASO 2 [ Cobro ] - " + obClientes.get(0).getmNombre());
            mCredito.setText("C$ " + obClientes.get(0).getmCredito());
            mLimite.setText("C$ " + obClientes.get(0).getmDisponible());
        }



        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obClientes.get(0).getmMoroso().equals("S")){
                    btnOK.setText("CLIENTE MOROSO");
                    btnOK.setBackgroundResource(R.drawable.button_danger_rounded);
                }else{
                    startActivity(new Intent(IndicadoresClienteActivity.this,PedidoActivity.class));
                    timer.cancel();
                    finish();
                }
            }
        });





        //mLimite.setText(obj.get(0).getmLimite());
        //mCredito.setText(obj.get(0).getmCredito());


        pieChart = (PieChart) findViewById(R.id.chart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);
        addDataSet();


        for(Facturas objFactura: Clientes_model.getFacturas(ManagerURI.getDirDb(),IndicadoresClienteActivity.this,preferences.getString("ClsSelected"," --ERROR--"))){
            TotalPuntos += Float.parseFloat(objFactura.getmRemanente());

        }

        mPuntos.setText(String.valueOf(TotalPuntos));
        mPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndicadoresClienteActivity.this,FacturasActivity.class));
            }
        });
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler.obtainMessage(1).sendToTarget();
            }
        }, 0, 1000);

    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            textView.setText(Clock.getDiferencia(Clock.StringToDate(preferences.getString("iniTimer","0000-00-00 00:00:00"),"yyyy-mm-dd HH:mm:ss"),Clock.StringToDate(Clock.getNow(),"yyyy-mm-dd HH:mm:ss"),"Timer"));
        }
    };
    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }
        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#"+Integer.toHexString(getResources().getColor(R.color.grfGris))));
        colors.add(Color.parseColor("#"+Integer.toHexString(getResources().getColor(R.color.grfCeleste))));
        colors.add(Color.parseColor("#"+Integer.toHexString(getResources().getColor(R.color.grfNaranja))));
        pieDataSet.setColors(colors);
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.invalidate();
    }

}
