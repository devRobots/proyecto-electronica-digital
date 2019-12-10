package com.example.smurilloo.login;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.*;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        Button piso1 = findViewById(R.id.btnPiso0);
        TextView indicador1 = findViewById(R.id.txtPiso0);
        Button piso2 = findViewById(R.id.btnPiso1);
        TextView indicador2 = findViewById(R.id.txtPiso1);
        Button piso3 = findViewById(R.id.btnPiso2);
        TextView indicador3 = findViewById(R.id.txtPiso2);
        Button piso4 = findViewById(R.id.btnPiso3);
        TextView indicador4 = findViewById(R.id.txtPiso3);

        final Button alarma = findViewById(R.id.btnAlarma);

        final DatabaseReference ref = db.getReference();

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String fuente = ((Button)v).getText().toString();
                int piso = Integer.parseInt(String.valueOf(fuente.charAt(fuente.length()-1)));

                Intent intent = new Intent(HomeActivity.this, PisoDosActivity.class);
                intent.putExtra("piso", piso);
                startActivity(intent);
            }
        };

        FloorStateIndicator indicadorClase1 = new FloorStateIndicator (indicador1);
        ref.child("piso1").addValueEventListener(indicadorClase1);
        piso1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PisoUnoActivity.class);
                startActivity(intent);
            }
        });

        FloorStateIndicator indicadorClase2 = new FloorStateIndicator (indicador2);
        ref.child("piso2").addValueEventListener(indicadorClase2);
        piso2.setOnClickListener(clickListener);

        FloorStateIndicator indicadorClase3 = new FloorStateIndicator (indicador3);
        ref.child("piso3").addValueEventListener(indicadorClase3);
        piso3.setOnClickListener(clickListener);

        FloorStateIndicator indicadorClase4 = new FloorStateIndicator (indicador4);
        ref.child("piso4").addValueEventListener(indicadorClase4);
        piso4.setOnClickListener(clickListener);

        ref.child("alarma").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int val = dataSnapshot.getValue(Integer.class);

                if(val == 0) {
                    alarma.setBackgroundColor(Color.RED);
                }
                else {
                    alarma.setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        alarma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("alarma").setValue(1);
            }
        });
    }
}
