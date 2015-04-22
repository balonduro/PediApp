package lamca.software.com.pediapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegUsuario extends ActionBarActivity {

    private EditText usuario;
    private EditText password;
    private EditText rePassword;
    private Button registrar;
    private AdministraSql usuarios;
    private AdministraSql us1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_usuario);

        usuario = (EditText) findViewById(R.id.etUsReg);
        password = (EditText) findViewById(R.id.etPassReg);
        rePassword = (EditText) findViewById(R.id.etRePassReg);
        registrar = (Button) findViewById(R.id.btnRegistrar);
        usuarios = new AdministraSql(this);
        us1 = new AdministraSql(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us, pass, repass;
                us = usuario.getText().toString();
                pass = password.getText().toString();
                repass = rePassword.getText().toString();
                String queryUs = "SELECT nomUs FROM usuarios WHERE nomUs = "+us;
                String query = "SELECT * FROM usuarios";
                Cursor c2 = us1.selectQuery(queryUs);
                Cursor c1 = usuarios.selectQuery(query);
                if(us.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(RegUsuario.this, "Dejaste campos en blanco", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pass.equals(repass)){
                    Toast.makeText(RegUsuario.this, "Las Contraseñas No Coinciden!", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    rePassword.setText("");
                    return;
                }else{
                    if (c1 != null && c1.getCount() != 0) {
                        if (c1.moveToFirst()) {
                            do {
                                    String queryInUs = "INSERT INTO usuarios(idUs,nomUs,passUs) values (null,'"+ us + "','" + pass + "')";
                                    usuarios.executeQuery(queryInUs);
                                    Toast.makeText(RegUsuario.this, "Creado Correctamente",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegUsuario.this, Login.class);
                                    startActivity(intent);
                            } while (c1.moveToNext());
                        }
                    }
                }
            }
        });
    }

}
