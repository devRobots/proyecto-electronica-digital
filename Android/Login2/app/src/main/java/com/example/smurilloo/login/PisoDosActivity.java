package com.example.smurilloo.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PisoDosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piso_dos);

        int piso = getIntent().getIntExtra("piso", 2);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference().child("piso" + piso);

        TextView indicator1 = findViewById(R.id.txtEstadoMagnetico);
        TextView indicator2 = findViewById(R.id.txtEstadoMovimiento);

        LedStateIndicator indicadorClase1 = new LedStateIndicator(indicator1);
        ref.child("magnetico").addValueEventListener(indicadorClase1);

        LedStateIndicator indicadorClase2 = new LedStateIndicator(indicator2);
        ref.child("movimiento").addValueEventListener(indicadorClase2);

        indicator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("movimiento").setValue(1);
            }
        });
    }
}
