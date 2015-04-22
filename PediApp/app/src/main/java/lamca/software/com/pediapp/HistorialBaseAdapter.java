package lamca.software.com.pediapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CarlosAugusto on 20/04/2015.
 */
public class HistorialBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<Ventas> ventasList;

    public HistorialBaseAdapter(Context context, ArrayList<Ventas> ventasList) {
        this.context = context;
        this.ventasList = ventasList;
    }

    @Override
    public int getCount() {
        return ventasList.size();
    }

    @Override
    public Object getItem(int position) {
        return ventasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ventas ventasListItems = ventasList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.historiallistrow, null);

        }
        TextView tvIdVenta = (TextView) convertView.findViewById(R.id.tvIdVenta);
        tvIdVenta.setText(ventasListItems.getIdVenta());
        TextView tvIdEmp= (TextView) convertView.findViewById(R.id.tvIdEmp);
        tvIdEmp.setText(ventasListItems.getIdEmp());
        TextView tvIdUs = (TextView) convertView.findViewById(R.id.tvIdUs);
        tvIdUs.setText(ventasListItems.getIdUs());
        TextView total = (TextView) convertView.findViewById(R.id.tvTotal);
        total.setText(ventasListItems.getTotal());
        return convertView;
    }
}
