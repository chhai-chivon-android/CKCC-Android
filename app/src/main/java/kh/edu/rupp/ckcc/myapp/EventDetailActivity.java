package kh.edu.rupp.ckcc.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

public class EventDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_detail);

        SimpleDraweeView imgEvent = findViewById(R.id.img_event);
        TextView txtTitle = findViewById(R.id.txt_title);
        TextView txtDate = findViewById(R.id.txt_date);
        TextView txtLocation = findViewById(R.id.txt_location);
        TextView txtDescription = findViewById(R.id.txt_description);

        // Get data from events activity
        Intent intent = getIntent();
        String eventJson = intent.getStringExtra("eventJson");
        Gson gson = new Gson();
        Event event = gson.fromJson(eventJson, Event.class);

        imgEvent.setImageURI(event.getImageUrl());
        txtTitle.setText(event.getTitle());
        txtDate.setText(event.getDate());
        txtLocation.setText(event.getLocation());
        txtDescription.setText(event.getDesc());
    }
}
