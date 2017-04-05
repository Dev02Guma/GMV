package com.guma.desarrollo.gmv.Activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.guma.desarrollo.core.Actividad;
import com.guma.desarrollo.core.Actividades_model;
import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.Razon;
import com.guma.desarrollo.core.RazonDetalle;
import com.guma.desarrollo.core.Razon_model;
import com.guma.desarrollo.core.Row;
import com.guma.desarrollo.core.SQLiteHelper;
import com.guma.desarrollo.gmv.Adapters.ActividadesAdapter;
import com.guma.desarrollo.gmv.Adapters.CustomArrayAdapter;
import com.guma.desarrollo.gmv.Adapters.RazonesAdapter;
import com.guma.desarrollo.gmv.CategoriaInfo;
import com.guma.desarrollo.gmv.R;

import java.util.ArrayList;
import java.util.List;

public class RazonesActivity extends AppCompatActivity {
    List<Row> rows;
    List<Actividad> datos;
    private ListView listView;
    private ArrayList<CategoriaInfo> deptList = new ArrayList<>();

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String CodCls,IdRazon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razones);
        listView = (ListView) findViewById(R.id.list);
        final RazonesAdapter listAdapter;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        setTitle(preferences.getString("NameClsSelected"," --ERROR--"));

        CodCls =  preferences.getString("ClsSelected","");


        //simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListViewRazon);
        //listAdapter = new ActividadesAdapter(RazonActivity.this,deptList);
        //simpleExpandableListView.setAdapter(listAdapter);

        //listAdapter = new RazonesAdapter(RazonesActivity.this,deptList);
        datos = Actividades_model.getActividades(ManagerURI.getDirDb(), RazonesActivity.this);
        /*
        rows = new ArrayList<Row>(30);
        Row row = null;
        for (int i = 1; i<31; i++)
        {
            row = new Row();
            row.setTitle("Title "+i);
            row.setSubtitle("Subtitle " + i);
            rows.add(row);
        }
        rows.get(3).setChecked(true);
        rows.get(6).setChecked(true);
        rows.get(9).setChecked(true);

        listView.setAdapter(new CustomArrayAdapter(this,rows));
        */
        rows = new ArrayList<Row>(datos.size());
        Row row = null;
        for (int i = 0; i < datos.size(); i++) {
            row = new Row();
            row.setTitle(datos.get(i).getmActividad());
            row.setSubtitle(datos.get(i).getmCategoria());
            row.setSubsubtitle(datos.get(i).getmIdAE());
            row.setChecked(false);
            rows.add(row);
        }
        listView.setAdapter(new CustomArrayAdapter(this, rows));
        findViewById(R.id.btnSaveRazones).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //ListView lv = (ListView)findViewById(R.id.listaProductos);
                ListView lv = (ListView)findViewById(R.id.list);
                int count = lv.getAdapter().getCount();

                Razon ra = new Razon();
                ArrayList<RazonDetalle> rd = new ArrayList<RazonDetalle>();
                int key = SQLiteHelper.getId(ManagerURI.getDirDb(), RazonesActivity.this, "RAZON");
                IdRazon = preferences.getString("VENDEDOR", "00") + "R" + Clock.getIdDate() + String.valueOf(key);
                ra.setmIdRazon(IdRazon);
                ra.setmVendedor("F09");
                ra.setmCliente(preferences.getString("ClsSelected",""));
                ra.setmNombre(preferences.getString("NameClsSelected"," --ERROR--"));
                ra.setmFecha(Clock.getNow());
                ra.setmObservacion("Observacion");

                for (int i = 0; i < lv.getAdapter().getCount(); i++)
                {
                    TextView tvActividad = (TextView) lv.getAdapter().getView(i,null,null).findViewById(R.id.textViewTitle);
                    TextView tvCategoria = (TextView) lv.getAdapter().getView(i,null,null).findViewById(R.id.textViewSubtitle);
                    TextView tvIdAE = (TextView) lv.getAdapter().getView(i,null,null).findViewById(R.id.textViewSubSubtitle);
                    CheckBox tvTest  = (CheckBox) lv.getAdapter().getView(i,null,null).findViewById(R.id.checkBox);

                    if (tvTest.isChecked())
                    {
                        rd.add(new RazonDetalle(IdRazon.toString(),tvIdAE.getText().toString(),tvActividad.getText().toString(),tvCategoria.getText().toString()));
                        ra.setRdet(rd);
                    }

                }
                Razon_model.SaveRazon(RazonesActivity.this,ra);
            }
        });

    }
}
