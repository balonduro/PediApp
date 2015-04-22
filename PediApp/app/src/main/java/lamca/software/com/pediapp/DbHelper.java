package lamca.software.com.pediapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String USUARIOS = "usuarios";
    public static final String u1 = "idUs";
    public static final String u2 = "nomUs";
    public static final String u3 = "passUs";
    private static final String CREAUSUARIOS = "create table " + USUARIOS + " ("
            + u1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + u2 + " text not null, "
            + u3 + " text not null);";

    public static final String insertarUs = "insert into usuarios values (null, 'admin', 'admin');";

    public static final String EMPRESA = "empresa";
    public static final String e1="idEmp";
    public static final String e2="nomEmp";
    private static final String CREAEMPRESA ="create table " + EMPRESA +" ("
            + e1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + e2 +" text not null);";

    public static final String insertarEmp = "insert into empresa values (null, 'Pizzeta');";
    public static final String insertarEmp2 = "insert into empresa values (null, 'Abuelitas Pizza');";
    public static final String insertarEmp3 = "insert into empresa values (null, 'Dominos Pizza');";

    public static final String PRODUCTOS = "productos";
    public static final String p1 = "idProd";
    public static final String p2 = "nomProd";
    public static final String p3 = "precProd";
    public static final String p4 = "idEmp";
    private static final String CREAPRODUCTOS = "create table "+ PRODUCTOS + " ("
            + p1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + p2 + " text not null,"
            + p3 + " text not null,"
            + p4 + " text not null);";

    public static final String insertaProd = "insert into productos values (null, 'Pizza Familiar + 1refresco','120','1');";
    public static final String insertaProd1 = "insert into productos values (null, 'Pizza Familiar + papas','121','1');";
    public static final String insertaProd2 = "insert into productos values (null, 'Pizza Familiar + helado','122','1');";
    public static final String insertaProd3 = "insert into productos values (null, 'Pizza Familiar + galletas','123','2');";
    public static final String insertaProd4 = "insert into productos values (null, 'Pizza Familiar + baguette','124','2');";
    public static final String insertaProd5 = "insert into productos values (null, 'Pizza Familiar + spagetti','125','2');";
    public static final String insertaProd6 = "insert into productos values (null, 'Pizza Familiar + ensalada','126','3');";
    public static final String insertaProd7 = "insert into productos values (null, 'Pizza Familiar + bollitos','127','3');";


    public static final String VENTAS = "ventas";
    public static final String v1 = "idVenta";
    public static final String v2 = "total";
    public static final String v3 = "idEmp";
    public static final String v4 = "idUs";
    private static final String CREAVENTA = "create table " + VENTAS + " ("
            + v1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + v2 + " text not null,"
            + v3 + " text not null,"
            + v4 + " text not null);";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAEMPRESA);
        db.execSQL(CREAUSUARIOS);
        db.execSQL(CREAPRODUCTOS);
        db.execSQL(CREAVENTA);
        db.execSQL(insertarUs);
        db.execSQL(insertarEmp);
        db.execSQL(insertarEmp2);
        db.execSQL(insertarEmp3);
        db.execSQL(insertaProd);
        db.execSQL(insertaProd1);
        db.execSQL(insertaProd2);
        db.execSQL(insertaProd3);
        db.execSQL(insertaProd4);
        db.execSQL(insertaProd5);
        db.execSQL(insertaProd6);
        db.execSQL(insertaProd7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS "+ EMPRESA);
        db.execSQL("DROP TABLE IF EXISTS "+ PRODUCTOS);
        db.execSQL("DROP TABLE IF EXISTS "+ VENTAS);
        onCreate(db);
    }
}
