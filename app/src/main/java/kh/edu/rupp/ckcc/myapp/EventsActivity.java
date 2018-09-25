package kh.edu.rupp.ckcc.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class EventsActivity extends AppCompatActivity {

    private EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adapter
        eventsAdapter = new EventsAdapter();

        // Create temporary data
        Event event1 = new Event();
        event1.setTitle("Event 1");
        event1.setDate("11 Decmber 2018");

        Event event2 = new Event();
        event2.setTitle("Event 2");
        event2.setDate("24 September 2018");

        Event event3 = new Event();
        event3.setTitle("Event 3");
        event3.setDate("27 August 2018");

        Event[] data = new Event[3];
        data[0] = event1;
        data[1] = event2;
        data[2] = event3;

        eventsAdapter.setData(data);

        recyclerView.setAdapter(eventsAdapter);

    }

}
