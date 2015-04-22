package lamca.software.com.pediapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Tiendas extends ActionBarActivity {

    private ListView lista;
    private AdministraSql empresas;
    private String idUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas);
        lista = (ListView) findViewById(R.id.lvListaTienda);
        empresas = new AdministraSql(this);
        idUs = getIntent().getStringExtra("idUs");
        ArrayList<Tiendas1> tiendasList = new ArrayList<Tiendas1>();
        tiendasList.clear();
        String query = "SELECT * FROM empresa";
        Cursor c1 = empresas.selectQuery(query);
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    Tiendas1 tiendasListItems = new Tiendas1();
                    tiendasListItems.setIdEmpresa(c1.getString(c1.getColumnIndex("idEmp")));
                    tiendasListItems.setNomEmpresa(c1.getString(c1.getColumnIndex("nomEmp")));
                    tiendasList.add(tiendasListItems);
                } while (c1.moveToNext());
            }
        }
        c1.close();
        TiendasBaseAdapter tiendasListAdapter = new TiendasBaseAdapter(Tiendas.this, tiendasList);
        lista.setAdapter(tiendasListAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tiendas1 t = (Tiendas1) lista.getItemAtPosition(position);
                String idEmp = "";
                idEmp = t.getIdEmpresa();
                Intent intent = new Intent(Tiendas.this, Productos.class);
                intent.putExtra("idEmp",idEmp);
                intent.putExtra("idUs", idUs);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tiendas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(Tiendas.this,Historial.class);
            i.putExtra("idUs",idUs);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
