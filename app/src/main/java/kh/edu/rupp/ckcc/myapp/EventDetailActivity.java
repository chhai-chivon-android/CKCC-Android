package kh.edu.rupp.ckcc.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_detail);

        TextView txtTitle = findViewById(R.id.txt_title);
        TextView txtDate = findViewById(R.id.txt_date);
        TextView txtLocation = findViewById(R.id.txt_location);
        TextView txtDescription = findViewById(R.id.txt_description);

        // Get data from events activity
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");
        String description = intent.getStringExtra("description");

        txtTitle.setText(title);
        txtDate.setText(date);
        txtLocation.setText(location);
        txtDescription.setText(description);
    }
}
