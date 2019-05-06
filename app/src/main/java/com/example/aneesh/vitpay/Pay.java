package com.example.aneesh.vitpay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pay extends AppCompatActivity {
    Button payb;
    EditText amt;
    int am;
    String regno;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        TextView textView = findViewById(R.id.regno);
        payb = findViewById(R.id.payb);

        Bundle bundle = getIntent().getExtras();
        regno = bundle.getString("regno");

        mRef = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void payamt()
    {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                Toast.makeText(Pay.this, "Payment Added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Pay.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getValues() {

        am = Integer.parseInt(amt.getText().toString());
        mRef.child("2LxwbxrnvIy7gen7aVCa\n").child("Amount").setValue(am);
        mRef.child("2LxwbxrnvIy7gen7aVCa\n").child("Reg. No.").setValue(regno);

        Intent in = new Intent(Pay.this, MainActivity.class);
        startActivity(in);

    }
}
