package com.example.poojanapp;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;
public class MainActivity extends AppCompatActivity {
    Firebase myFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final GradientDrawable shape =new GradientDrawable();
        shape.setCornerRadius(40);
        shape.setStroke(20,Color.BLACK);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView teet= findViewById(R.id.textView10);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        teet.setText(currentDateTimeString);
        TextView textView = findViewById(R.id.textView);
        String text = "RED INDICATES PARKING SLOT IS FULL WHEREAS GREEN INDICATES PARKING SLOT IS EMPTY";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
        ss.setSpan(fcsRed,0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsGreen,43,49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        Toast.makeText(MainActivity.this, "Firebase connection successful", Toast.LENGTH_LONG).show();
        final TextView myTextView = (TextView) findViewById(R.id.textView2);
        final TextView outputt =(TextView) findViewById(R.id.name_view);
        Firebase.setAndroidContext(getApplicationContext());
        myFirebase = new Firebase("https://iot-car-parking-system.firebaseio.com/user/PARKING");
        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String myChildText = dataSnapshot.getValue(String.class);
                if (myChildText.equals("true")) {
                    shape.setColor(Color.RED);
                    myTextView.setBackground(shape);
                    outputt.setText("PARKING IS NOT AVAILABLE AT THE MOMENT SIR, PLEASE WAIT FOR SOMETIME :)");

                } else {

                    shape.setColor(Color.GREEN);
                    myTextView.setBackground(shape);
                    outputt.setText("PARKING IS AVAILABLE SIR, YOU CAN PARK ^.^");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}

