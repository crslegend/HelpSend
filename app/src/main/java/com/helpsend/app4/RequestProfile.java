package com.helpsend.app4;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RequestProfile extends AppCompatActivity {

    private TextView tvName, tvContact, tvLevel, tvSubject, tvLocation, tvTimeDate;
    // private Button btDelete;
    private RequestListHandler db;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_profile);

        db = new RequestListHandler(this);
        tvName = findViewById(R.id.tvName);
        tvContact = findViewById(R.id.tvContact);
        tvLevel = findViewById(R.id.tvLevel);
        tvSubject = findViewById(R.id.tvSubject);
        tvLocation = findViewById(R.id.tvLocation);
        tvTimeDate = findViewById(R.id.tvTimeDate);
        // btDelete = findViewById(R.id.btDelete);

        Intent intent = getIntent();
        final Request request = intent.getParcelableExtra("REQUEST");
        String name = request.getName();
        String contact = request.getContact();
        String level = request.getLevel();
        String subject = request.getSubject();
        location = request.getLocation();
        String timedate = request.getTimedate();

        tvName.setText(name);
        tvContact.setText(contact);
        tvLevel.setText(level);
        tvSubject.setText(subject);
        tvLocation.setText(location);
        tvTimeDate.setText(timedate);

        /*btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRequest(request);
                Toast.makeText(getApplicationContext(),"Request Accepted!",Toast.LENGTH_SHORT).show();
            }
        });*/

        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestProfile.this, RequestLocation.class);
                intent.putExtra("GMAP_STRING", location);
                startActivity(intent);
            }
        });

    }
}
