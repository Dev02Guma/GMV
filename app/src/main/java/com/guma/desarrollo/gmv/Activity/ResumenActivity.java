package com.guma.desarrollo.gmv.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.Pedidos;
import com.guma.desarrollo.core.Pedidos_model;
import com.guma.desarrollo.core.SQLiteHelper;
import com.guma.desarrollo.gmv.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ResumenActivity extends AppCompatActivity {
    TextView lblNombreClliente,lblNombreVendedor,countArti,SubTotal,ivaTotal,Total,Atendio,txtidPedido;
    private static ListView listView;
    float vLine = 0,subValor=0,vFinal=0;
    ArrayList<Pedidos> mPedido = new ArrayList<>();
    ArrayList<Pedidos> mDetallePedido = new ArrayList<>();
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    String CodCls,idPedido,bandera = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("RESUMEN");
        Intent ints = getIntent();
        listView = (ListView) findViewById(R.id.ListView1);

        final List<Map<String, Object>> list = (List<Map<String, Object>>) ints.getSerializableExtra("LIST");
        listView.setAdapter(
                new SimpleAdapter(this, list,R.layout.list_item_resumen,
                new String[] {"ITEMNAME", "ITEMCANTI","ITEMCODIGO","ITEMVALOR","BONIFICADO","PRECIO" },
                new int[] { R.id.tvListItemName,R.id.Item_cant,R.id.Item_descr,R.id.Item_valor,R.id.tvListBonificado,R.id.tvListItemPrecio })
        );
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        CodCls =  preferences.getString("ClsSelected","");

        Total = (TextView) findViewById(R.id.Total);
        Atendio = (TextView) findViewById(R.id.NombreVendedor);
        txtidPedido = (TextView) findViewById(R.id.IdPedido);

        lblNombreClliente = (TextView) findViewById(R.id.NombreCliente);
        lblNombreVendedor = (TextView) findViewById(R.id.NombreVendedor);
        lblNombreVendedor.setText("");
        lblNombreClliente.setText(ints.getStringExtra("NombreCliente"));
        countArti = (TextView) findViewById(R.id.txtCountArti);

        Toast.makeText(ResumenActivity.this, preferences.getString("ClsSelected",""), Toast.LENGTH_SHORT).show();
        idPedido = preferences.getString("IDPEDIDO", "");
        if (!idPedido.equals("")){
            //Toast.makeText(this, "La edidion del pedido: "+preferences.getString("IDPEDIDO", ""), Toast.LENGTH_SHORT).show();
            Atendio.setText(preferences.getString("NOMBRE","").toString());
            txtidPedido.setText(idPedido.toString());
            bandera = "1";
        }else{
            int key = SQLiteHelper.getIdTemporal(ManagerURI.getDirDb(),ResumenActivity.this,"PEDIDOS");
            idPedido = "F09-" + "P"+ Clock.getIdDate()+String.valueOf(key);
            txtidPedido.setText(idPedido.toString());
            Atendio.setText(preferences.getString("NOMBRE","").toString());
        }
        for (Map<String, Object> obj : list){
            vLine     += Float.parseFloat(obj.get("ITEMVALOR").toString());
        }
        Total.setText("TOTAL C$ "+ String.valueOf(vLine));
        countArti.setText(listView.getCount() +" Articulo");
        findViewById(R.id.btnSaveSale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ResumenActivity.this)
                        .setTitle("CONFIRMACION")
                        .setMessage("¿DESEA GUARDAR EL PEDIDO?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (CodCls!="") {
                                        Toast.makeText(ResumenActivity.this, "GUARDANDO CASO: "+bandera, Toast.LENGTH_SHORT).show();
                                        guardar(list);
                                        Pedidos_model.SaveDetallePedido(ResumenActivity.this, mDetallePedido);
                                        finish();
                                }else {
                                    Toast.makeText(ResumenActivity.this, "ERROR AL GUARDAR PEDIDO, INTENTELO MAS TARDE", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();
            }
        });
    }
    public void guardar(List<Map<String, Object>> list){
        if (bandera=="1"){
            SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(),ResumenActivity.this,"DELETE FROM PEDIDO_DETALLE WHERE IDPEDIDO = '"+idPedido+"'");
            for (Map<String, Object> obj2 : list) {
                Pedidos tmpDetalle = new Pedidos();
                tmpDetalle.setmIdPedido(idPedido);
                tmpDetalle.setmArticulo(obj2.get("ITEMCODIGO").toString());
                tmpDetalle.setmDescripcion(obj2.get("ITEMNAME").toString());
                tmpDetalle.setmCantidad(obj2.get("ITEMCANTI").toString());
                tmpDetalle.setmPrecio(obj2.get("PRECIO").toString());
                tmpDetalle.setmBonificado(obj2.get("BONIFICADO").toString());
                mDetallePedido.add(tmpDetalle);
            }
            Pedidos_model.SaveDetallePedido(ResumenActivity.this, mDetallePedido);
        }else{
            int key = SQLiteHelper.getId(ManagerURI.getDirDb(), ResumenActivity.this, "PEDIDOS");
            idPedido = preferences.getString("VENDEDOR", "00") + "P" + Clock.getIdDate() + String.valueOf(key);
            Float nTotal = 0.0f;
            for (Map<String, Object> obj : list) {
                nTotal += Float.parseFloat(obj.get("ITEMVALOR").toString());
            }
            Pedidos tmp = new Pedidos();
            tmp.setmIdPedido(idPedido);
            tmp.setmVendedor("F09");
            tmp.setmCliente(CodCls);
            tmp.setmNombre(preferences.getString("NameClsSelected", " CLIENTE NO ENCONTRADO"));
            tmp.setmFecha(Clock.getNow());
            tmp.setmPrecio(String.valueOf(nTotal));
            tmp.setmEstado("0");
            mPedido.add(tmp);
            Pedidos_model.SavePedido(ResumenActivity.this, mPedido);
            for (Map<String, Object> obj2 : list) {
                Pedidos tmpDetalle = new Pedidos();
                tmpDetalle.setmIdPedido(idPedido);
                tmpDetalle.setmArticulo(obj2.get("ITEMCODIGO").toString());
                tmpDetalle.setmDescripcion(obj2.get("ITEMNAME").toString());
                tmpDetalle.setmCantidad(obj2.get("ITEMCANTI").toString());
                tmpDetalle.setmPrecio(obj2.get("PRECIO").toString());
                tmpDetalle.setmBonificado(obj2.get("BONIFICADO").toString());
                mDetallePedido.add(tmpDetalle);
            }
            Pedidos_model.SaveDetallePedido(ResumenActivity.this, mDetallePedido);
        }
    }
}
