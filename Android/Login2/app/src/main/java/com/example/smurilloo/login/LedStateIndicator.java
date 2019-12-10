package com.example.smurilloo.login;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LedStateIndicator implements ValueEventListener {
    private TextView indicator;

    public LedStateIndicator(TextView indicator) {
        this.indicator = indicator;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        int val = dataSnapshot.getValue(Integer.class);

        if (val == 0) {
            indicator.setBackgroundColor(Color.RED);
        }
        else {
            indicator.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        indicator.setBackgroundColor(Color.YELLOW);
    }
}
