package lamca.software.com.pediapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class Historial extends ActionBarActivity {

    private ListView lista;
    private AdministraSql ventas;
    private String idUs;
    private String idEmp;
    private Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        idUs = getIntent().getStringExtra("idUs");
        idEmp = getIntent().getStringExtra("idEmp");
        lista = (ListView) findViewById(R.id.lvHistorial);
        regresar = (Button) findViewById(R.id.btnRegresar);
        ventas = new AdministraSql(Historial.this);
        ArrayList<Ventas> historialList = new ArrayList<Ventas>();
        historialList.clear();
        String query = "SELECT * FROM ventas WHERE idUs = " + idUs;
        Cursor c1 = ventas.selectQuery(query);
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    Ventas ventasListItems = new Ventas();
                    ventasListItems.setIdVenta(c1.getString(c1.getColumnIndexOrThrow("idVenta")));
                    ventasListItems.setIdEmp(c1.getString(c1.getColumnIndexOrThrow("idEmp")));
                    ventasListItems.setIdEmp(c1.getString(c1.getColumnIndexOrThrow("idEmp")));
                    ventasListItems.setTotal(c1.getString(c1.getColumnIndex("total")));
                    historialList.add(ventasListItems);
                } while (c1.moveToNext());
            }
        }
        c1.close();
        HistorialBaseAdapter ventasListAdapter = new HistorialBaseAdapter(Historial.this,historialList);
        lista.setAdapter(ventasListAdapter);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Historial.this,Tiendas.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historial, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
