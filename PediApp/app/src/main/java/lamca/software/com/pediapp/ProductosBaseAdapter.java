package lamca.software.com.pediapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CarlosAugusto on 19/04/2015.
 */
public class ProductosBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Productos1> productosList;

    public ProductosBaseAdapter(Context context, ArrayList<Productos1> productosList) {
        this.context = context;
        this.productosList = productosList;
    }

    @Override
    public int getCount() {
        return productosList.size();
    }

    @Override
    public Object getItem(int position) {
        return productosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Productos1 prodListItems = productosList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.productoslistrow, null);

        }
        TextView tvNomProd = (TextView) convertView.findViewById(R.id.tvNomProd);
        tvNomProd.setText(prodListItems.getNomProd());
        TextView tvCantProd = (TextView) convertView.findViewById(R.id.tvCantProd);
        tvCantProd.setText(prodListItems.getCantProd());
        TextView tvPrecProd = (TextView) convertView.findViewById(R.id.tvPrecProd);
        tvPrecProd.setText(prodListItems.getPrecProd());
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
        tvTotal.setText(String.valueOf(prodListItems.getTotal()));
        return convertView;
    }
}

