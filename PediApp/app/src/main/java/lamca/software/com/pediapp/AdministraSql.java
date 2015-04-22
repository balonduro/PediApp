package lamca.software.com.pediapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class AdministraSql {
    public static final String DATABASE_NAME = "PediApp";
    public static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase sqlDatabase;
    DbHelper dbHelper;

    public AdministraSql(Context context) {

        dbHelper = new DbHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        sqlDatabase = dbHelper.getWritableDatabase();
    }

    public void executeQuery(String query) {
        try {
            if (sqlDatabase.isOpen()) {
                sqlDatabase.close();
            }
            sqlDatabase = dbHelper.getWritableDatabase();
            sqlDatabase.execSQL(query);
        } catch (Exception e) {
            System.out.println("Error de Bd " + e);
        }
    }

    public Cursor selectQuery(String query) {
        Cursor c1 = null;
        try {
            if (sqlDatabase.isOpen()) {
                sqlDatabase.close();
            }
            sqlDatabase = dbHelper.getWritableDatabase();
            c1 = sqlDatabase.rawQuery(query, null);
        } catch (Exception e) {
            System.out.println("Error de Bd " + e);
        }
        return c1;
    }

    public Cursor consultarPorEmpresa(String tabla, String[] campos, String texto) throws SQLException{
        Cursor mCursor = sqlDatabase.query(true, tabla, campos," nomProd " + "like '%" + texto.trim() + "%'",null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
