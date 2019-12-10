package com.example.smurilloo.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PisoUnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piso_uno);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference().child("piso1");

        TextView indMagnetico1 = findViewById(R.id.txtEstadoMagnetico1);
        TextView indMagnetico2 = findViewById(R.id.txtEstadoMagnetico2);
        TextView indMagnetico3 = findViewById(R.id.txtEstadoMagnetico3);
        TextView indMagnetico4 = findViewById(R.id.txtEstadoMagnetico4);
        TextView indMagnetico5 = findViewById(R.id.txtEstadoMagnetico5);
        TextView indMovimiento1 = findViewById(R.id.txtEstadoMovimiento1);
        TextView indMovimiento2 = findViewById(R.id.txtEstadoMovimiento2);
        TextView indMovimiento3 = findViewById(R.id.txtEstadoMovimiento3);

        LedStateIndicator indicadorMagnetico1 = new LedStateIndicator(indMagnetico1);
        ref.child("magnetico1").addValueEventListener(indicadorMagnetico1);
        LedStateIndicator indicadorMagnetico2 = new LedStateIndicator(indMagnetico2);
        ref.child("magnetico2").addValueEventListener(indicadorMagnetico2);
        LedStateIndicator indicadorMagnetico3 = new LedStateIndicator(indMagnetico3);
        ref.child("magnetico3").addValueEventListener(indicadorMagnetico3);
        LedStateIndicator indicadorMagnetico4 = new LedStateIndicator(indMagnetico4);
        ref.child("magnetico4").addValueEventListener(indicadorMagnetico4);
        LedStateIndicator indicadorMagnetico5 = new LedStateIndicator(indMagnetico5);
        ref.child("magnetico5").addValueEventListener(indicadorMagnetico5);

        LedStateIndicator indicadorMovimiento1 = new LedStateIndicator(indMovimiento1);
        ref.child("movimiento1").addValueEventListener(indicadorMovimiento1);
        LedStateIndicator indicadorMovimiento2 = new LedStateIndicator(indMovimiento2);
        ref.child("movimiento2").addValueEventListener(indicadorMovimiento2);
        LedStateIndicator indicadorMovimiento3 = new LedStateIndicator(indMovimiento3);
        ref.child("movimiento3").addValueEventListener(indicadorMovimiento3);

        indMovimiento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("movimiento1").setValue(1);
            }
        });
        indMovimiento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("movimiento2").setValue(1);
            }
        });
        indMovimiento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("movimiento3").setValue(1);
            }
        });
    }
}
