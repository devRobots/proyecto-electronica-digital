package com.example.smurilloo.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);

        final EditText edt_usuario = findViewById(R.id.txtUsuario);
        final EditText edt_clave = findViewById(R.id.txtContraseña);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = edt_usuario.getText().toString();
                String clave = edt_clave.getText().toString();

                if (usuario.equals("admin") && clave.equals("123")) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Logueado correctamente", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Ingrese un usuario o contraseña validos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
