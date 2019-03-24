package com.helpsend.app4;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterTutee extends AppCompatActivity {

    private EditText etName, etContact, etLevel, etSubject, etLocation, etTimeDate;
    private Button btSubmit;
    private RequestListHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tutee);

        db = new RequestListHandler(this);

        etName = findViewById(R.id.etName);
        etContact = findViewById(R.id.etContact);
        etLevel = findViewById(R.id.etLevel);
        etSubject = findViewById(R.id.etSubject);
        etLocation = findViewById(R.id.etLocation);
        etTimeDate = findViewById(R.id.etDateTime);

        btSubmit = findViewById(R.id.btSubmit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etName.getText().toString().trim().isEmpty()))
                {
                    Request request = new Request();
                    request.setName(etName.getText().toString());
                    request.setContact(etContact.getText().toString());
                    request.setLevel(etLevel.getText().toString());
                    request.setSubject(etSubject.getText().toString());
                    request.setLocation(etLocation.getText().toString());
                    request.setTimedate(etTimeDate.getText().toString());

                    db.addRequest(request);
                    Toast.makeText(getApplicationContext(),"Request Submitted!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
