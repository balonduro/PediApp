package lamca.software.com.pediapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Productos extends ActionBarActivity {
    private AutoCompleteTextView nomProd;
    private EditText cantProd;
    private EditText precProd;
    private TextView nomProd1;
    private Button agregar;
    private Button modificar;
    private Button finalizar;
    private ListView listaProd;
    private AdministraSql productos;
    ArrayList<Productos1> productosList;
    ArrayAdapter<Productos1> adapter;
    private int pos = 0;
    private double total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        productos = new AdministraSql(this);
        nomProd = (AutoCompleteTextView) findViewById(R.id.actvNomProd);
        /*
        AdapterProductos adapterProd = this.new AdapterProductos(productos);
        nomProd.setAdapter(adapterProd);
        nomProd.setOnItemClickListener(adapterProd);
        //*/
        listaProd = (ListView) findViewById(R.id.lvProd);
        cantProd = (EditText) findViewById(R.id.etCant);
        precProd = (EditText) findViewById(R.id.etPrec);
        agregar = (Button) findViewById(R.id.btnAgregar);
        nomProd1 = (TextView) findViewById(R.id.txtProducto);
        modificar = (Button) findViewById(R.id.btnModificar);
        finalizar = (Button) findViewById(R.id.btnFinalizar);
        final String idUs = getIntent().getStringExtra("idUs");
        final String idEmp = getIntent().getStringExtra("idEmp");
        productosList = new ArrayList<Productos1>();
        adapter = new ArrayAdapter<Productos1>(this, android.R.layout.simple_list_item_1, productosList);
        listaProd.setAdapter(adapter);
        nomProd.requestFocus();
        //showList();

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomProd.getText().toString().equals("") || cantProd.getText().toString().equals("") || precProd.getText().toString().equals("")){
                    Toast.makeText(Productos.this, "No puedes dejar campos en blanco", Toast.LENGTH_SHORT).show();
                }else{
                    int cantidad = Integer.parseInt(cantProd.getText().toString());
                    double precio = Double.parseDouble(precProd.getText().toString());
                    Double totalCP = cantidad * precio;
                    Productos1 p = new Productos1();
                    p.setNomProd(nomProd.getText().toString());
                    p.setPrecProd(precProd.getText().toString());
                    p.setCantProd(cantProd.getText().toString());
                    p.setTotal(totalCP);
                    productosList.add(p);
                    ProductosBaseAdapter prodAdapter = new ProductosBaseAdapter(Productos.this, productosList);
                    listaProd.setAdapter(prodAdapter);
                    nomProd.setText("");
                    cantProd.setText("");
                    precProd.setText("");
                    nomProd.requestFocus();
                    total = total + totalCP;
                    //showList();
                }
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomProd.getText().toString().equals("") || cantProd.getText().toString().equals("") || precProd.getText().toString().equals("")){
                    Toast.makeText(Productos.this, "No puedes dejar campos en blanco", Toast.LENGTH_SHORT).show();
                }else {
                    //Borramos el contenido de la lista
                    productosList.remove(pos);
                    adapter.notifyDataSetChanged();
                    //Agregamos la modificacion a la lista
                    int cantidad = Integer.parseInt(cantProd.getText().toString());
                    double precio = Double.parseDouble(precProd.getText().toString());
                    Double totalCP = cantidad * precio;
                    total = total - totalCP;
                    Productos1 p = new Productos1();
                    p.setNomProd(nomProd.getText().toString());
                    p.setPrecProd(precProd.getText().toString());
                    p.setCantProd(cantProd.getText().toString());
                    p.setTotal(totalCP);
                    productosList.add(p);
                    //Le notificamos al adapter para que aplique los cambios
                    adapter.notifyDataSetChanged();
                    //Borramos el contenido del EditText
                    nomProd.setText("");
                    cantProd.setText("");
                    precProd.setText("");
                    //Deshabilitamos el boton de modificar
                    modificar.setEnabled(false);
                    agregar.setEnabled(true);
                    nomProd.requestFocus();
                }
            }
        });

        listaProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Extraemos la posicion de la lista, del item que hallamos pulsado.
                Productos1 listChoice = (Productos1)(listaProd.getItemAtPosition(position));
                //Imprimimos un mensaje que nos diga que fue lo que oprimimos y lo ponemos en el editText
                nomProd.setText(listChoice.getNomProd());
                cantProd.setText(listChoice.getCantProd());
                precProd.setText(listChoice.getPrecProd());
                modificar.setEnabled(true);
                agregar.setEnabled(false);
                //Recuperamos la posicion del elemento de la lista y deshabilitamos el boton de modificar
                pos=position;
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productosList.isEmpty()){
                    Toast.makeText(Productos.this, "No hay productos para agregar",Toast.LENGTH_SHORT).show();
                }else{
                    /*
                    Dim i As Variant;
                    For i = 1 To ListView1.ListItems.Count
                            sumarGastos = Round(sumarGastos + CDbl(ListView1.ListItems(i).SubItems(10)), 2)
                    Next i
                    */
                String query = "INSERT INTO ventas(total,idEmp,idUs) values ("+ total +"," + idEmp + "," + idUs + ")";
                productos.executeQuery(query);
                Toast.makeText(Productos.this, "Venta Realizada",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Productos.this, Historial.class);
                    i.putExtra("idEmp",idEmp);
                    i.putExtra("idUs",idUs);
                startActivity(i);
                finish();
                }
            }
        });

        SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(listaProd,
                new SwipeListViewTouchListener.OnSwipeCallback() {
                    @Override
                    public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
                        //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                        productosList.remove(reverseSortedPositions[0]);
                        adapter.notifyDataSetChanged();
                        nomProd.setText("");
                        precProd.setText("");
                        cantProd.setText("");
                        modificar.setEnabled(false);
                        agregar.setEnabled(true);
                    }
                    @Override
                    public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
                        //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                        productosList.remove(reverseSortedPositions[0]);
                        adapter.notifyDataSetChanged();
                        nomProd.setText("");
                        precProd.setText("");
                        cantProd.setText("");
                        modificar.setEnabled(false);
                        agregar.setEnabled(true);
                    }
                },true, false);

        listaProd.setOnTouchListener(touchListener);
        listaProd.setOnScrollListener(touchListener.makeScrollListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_productos, menu);
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
/*
    public void showList(){
        productosList.clear();
        String query = "SELECT * FROM productos ";
        Cursor c1 = productos.selectQuery(query);
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    Productos1 productosListItems = new Productos1();
                    double precProd = Double.parseDouble(c1.getString(c1.getColumnIndex("precProd")));
                    int cant = Integer.parseInt(cantProd.getText().toString());
                    productosListItems.setNomProd(c1.getString(c1.getColumnIndex("nomProd")));
                    productosListItems.setCantProd(cantProd.getText().toString());
                    productosListItems.setPrecProd(String.valueOf(cant*precProd));
                    productosList.add(productosListItems);
                } while (c1.moveToNext());
            }
        }
        c1.close();
        ProductosBaseAdapter contactListAdapter = new ProductosBaseAdapter(Productos.this, productosList);
        listaProd.setAdapter(contactListAdapter);
    }
    */

/*
    class AdapterProductos extends CursorAdapter implements android.widget.AdapterView.OnItemClickListener{
        final String idUs = getIntent().getStringExtra("idUs");
        final String idEmp = getIntent().getStringExtra("idEmp");
        private AdministraSql actvProdAS;

        public AdapterProductos(AdministraSql actvProdAS) {
            super(Productos.this, null);
            this.actvProdAS = actvProdAS;
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if(getFilterQueryProvider() != null){
                return getFilterQueryProvider().runQuery(constraint);
            }
            Cursor cursor = null;
            try {
                cursor = actvProdAS.consultarPorEmpresa("productos",new String[]{"nomProd","precProd"},(constraint != null ? constraint.toString() : "@@@@"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cursor;
        }

        @Override
        public CharSequence convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("nomProd");
            final String str = cursor.getString(columnIndex);
            return str;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.activity_productos, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final int descColumnIndex = cursor.getColumnIndexOrThrow("nomProd");
            TextView text2 = (TextView) view.findViewById(R.id.txtProducto);
            text2.setText(cursor.getString(descColumnIndex));
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) listaProd.getItemAtPosition(position);
            precProd.setText(cursor.getString(cursor.getColumnIndexOrThrow("precProd")));
            cantProd.setText("1");
            nomProd1.setText(nomProd.getText());
            nomProd.setText("");
            nomProd.requestFocus();

        }
    }
    //*/
}
