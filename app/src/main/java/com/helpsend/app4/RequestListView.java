package com.helpsend.app4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RequestListView extends AppCompatActivity {

    private RequestListHandler db;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list_view);

        db = new RequestListHandler(this);

        /*
        Request request1 = new Request("CHEN RS", "91238212", "Primary 4",
                "English", "Sengkang cc", "29 MAR 19, 7PM");
        Request request2 = new Request("LI LIN", "97641242", "Secondary 2",
                "Chinese", "Yishun cc", "25 JUN 19, 5PM");

        db.addRequest(request1);
        db.addRequest(request2);
        */


        // db.deleteAll();

        final List<Request> requests = db.allRequests();

        if (requests != null) {
            String[] itemsNames = new String[requests.size()];

            for (int i = 0; i < requests.size(); i++) {
                itemsNames[i] = requests.get(i).toString();
            }

            // display like string instances
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, itemsNames));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(RequestListView.this, RequestProfile.class);
                    intent.putExtra("REQUEST", (Request) requests.get(position));
                    startActivity(intent);
                }
            });

        }

    }
}
