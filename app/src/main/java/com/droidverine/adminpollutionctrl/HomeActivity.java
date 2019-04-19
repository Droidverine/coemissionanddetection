package com.droidverine.adminpollutionctrl;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.droidverine.adminpollutionctrl.SpeedView.ProgressiveGauge;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    TextView co2su,carsu,co2shi,carshi,avgtxt;
    FirebaseDatabase database;
    ProgressiveGauge speedo;
    float one,two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        speedo=(ProgressiveGauge) findViewById(R.id.avgvalgraph);
        avgtxt=(TextView)findViewById(R.id.avgtxt);
        co2su=(TextView)findViewById(R.id.cocount1);
        co2shi=(TextView) findViewById(R.id.cocount2);
        carsu=(TextView)findViewById(R.id.carnum1);
        carshi=(TextView)findViewById(R.id.carnum2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        getco2ref1( database.getReference("mh43b4151"),"co2");
        getcarref1(database.getReference("mh43b4151"),"carno");
        getco2ref2( database.getReference("rj20b1234"),"co2");
        getcarref2(database.getReference("rj20b1234"),"carno");





    }

    public void getco2ref2(DatabaseReference databaseReference,String child)
    {

        databaseReference.child(child).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                DataSnapshot contactSnapshot = dataSnapshot.child("mq7");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                // whenever data at this location is updated.

                for (DataSnapshot contact : contactChildren) {
                    Log.d("co2hrre",contact.getValue().toString());
                    co2shi.setText("CO count : "+contact.getValue().toString());
                    if (contact.getValue().toString()!=null) {
                        one = Float.parseFloat(contact.getValue().toString());
                        speedo.setSpeedAt(one + two);
                        avgtxt.setText(one+two+"");
                    }

                }
               /* Long value = dataSnapshot.getValue(Long.class);
                Log.d("yeto", "Value is: " + value);
               currenttxt.setText(value.toString());*/
                //  speedometer.setMaxSpeed(value.intValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("nahi", "Failed to read value.", error.toException());
            }
        });
    }
    public void getcarref2(DatabaseReference databaseReference,String child)
    {
        databaseReference.child(child).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                String name=dataSnapshot.getValue(String.class);

                carshi.append(name);

                //  avgtxt.setText(contact.getValue().toString());

               /* Long value = dataSnapshot.getValue(Long.class);
                Log.d("yeto", "Value is: " + value);
               currenttxt.setText(value.toString());*/

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("nahi", "Failed to read value.", error.toException());
            }
        });


    }


    public void getco2ref1(DatabaseReference databaseReference,String child)
    {

        databaseReference.child(child).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                DataSnapshot contactSnapshot = dataSnapshot.child("mq7");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                // whenever data at this location is updated.

                for (DataSnapshot contact : contactChildren) {
                    Log.d("co2",contact.getValue().toString());
                    co2su.setText("CO count : "+contact.getValue().toString());
                    if (contact.getValue().toString()!=null) {
                        two = Float.parseFloat(contact.getValue().toString());
                        speedo.setSpeedAt(one + two);
                        avgtxt.setText(one+two+"");

                    }

                }
               /* Long value = dataSnapshot.getValue(Long.class);
                Log.d("yeto", "Value is: " + value);
               currenttxt.setText(value.toString());*/
                //  speedometer.setMaxSpeed(value.intValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("nahi", "Failed to read value.", error.toException());
            }
        });
    }
    public void getcarref1(DatabaseReference databaseReference,String child)
    {
        databaseReference.child(child).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                String name=dataSnapshot.getValue(String.class);

                carsu.append(name);

                //  avgtxt.setText(contact.getValue().toString());

               /* Long value = dataSnapshot.getValue(Long.class);
                Log.d("yeto", "Value is: " + value);
               currenttxt.setText(value.toString());*/
                //  speedometer.setMaxSpeed(value.intValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("nahi", "Failed to read value.", error.toException());
            }
        });


    }

}
