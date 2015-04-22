package lamca.software.com.pediapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity {

    private EditText etUsuario;
    private EditText etPassword;
    private boolean confirmar = false;
    private Button btnAceptar;

    private AdministraSql usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarios = new AdministraSql(this);

        etUsuario = (EditText) findViewById(R.id.etUs);
        etPassword = (EditText) findViewById(R.id.etPass);

        btnAceptar = (Button) findViewById(R.id.btnEntrar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///*
                String us = etUsuario.getText().toString();
                String pass = etPassword.getText().toString();
                String query = "SELECT * FROM usuarios";
                Cursor c1 = usuarios.selectQuery(query);
                if (c1 != null && c1.getCount() != 0) {
                    if (c1.moveToFirst()) {
                        do {
                            if (us.equals(c1.getString(c1.getColumnIndex("nomUs"))) && pass.equals(c1.getString(c1.getColumnIndex("passUs")))) {
                                Toast.makeText(Login.this, "Successfully Conected", Toast.LENGTH_SHORT).show();
                                //Login.this.startActivity(new Intent(Login.this, Tiendas.class));
                                Intent intent = new Intent(Login.this, Tiendas.class);
                                intent.putExtra("idUs", c1.getString(c1.getColumnIndex("idUs")));
                                startActivity(intent);
                                confirmar = true;
                            }
                        } while (c1.moveToNext());
                    }
                }
                /*
                if(confirmar = true){
                    Toast.makeText(Login.this, "Successfully Conected", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Incorrect Us/Pass", Toast.LENGTH_SHORT).show();
                }
                //*/
            }
        });
        findViewById(R.id.btnRegistro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.startActivity(new Intent(Login.this, RegUsuario.class));
            }
        });
    }
}
