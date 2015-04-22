package lamca.software.com.pediapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CarlosAugusto on 16/04/2015.
 */
public class TiendasBaseAdapter extends BaseAdapter{

    Context context;
    ArrayList<Tiendas1> tiendasList;

    public TiendasBaseAdapter(Context context, ArrayList<Tiendas1> tiendasList) {
        this.context = context;
        this.tiendasList = tiendasList;
    }

    @Override
    public int getCount() {
        return tiendasList.size();
    }

    @Override
    public Object getItem(int position) {
        return tiendasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tiendas1 tiendasListItems = tiendasList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tiendaslistrow, null);

        }
        TextView tvIdEmp= (TextView) convertView.findViewById(R.id.tvIdTienda);
        tvIdEmp.setText(tiendasListItems.getIdEmpresa());
        TextView tvNomEmp = (TextView) convertView.findViewById(R.id.tvNomTienda);
        tvNomEmp.setText(tiendasListItems.getNomEmpresa());
        return convertView;
    }
}
