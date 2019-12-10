package com.example.smurilloo.login;

import android.graphics.Color;
import android.support.annotation.NonNull;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

public class FloorStateIndicator implements ValueEventListener {
    private TextView indicator;

    public FloorStateIndicator(TextView indicator) {
        this.indicator = indicator;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        boolean flag = true;

        Map<String, Long> values = (Map<String, Long>) dataSnapshot.getValue();

        Iterator<String> it = values.keySet().iterator();
        while (it.hasNext() && flag) {
            String key = it.next();
            int tmpValue = values.get(key).intValue();
            if (tmpValue == 0) {
                flag = false;
            }
        }

        if (flag) {
            indicator.setBackgroundColor(Color.GREEN);
        }
        else {
            indicator.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        indicator.setBackgroundColor(Color.YELLOW);
    }
}
