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

        // Load list of events from DB
        DbManager dbManager = new DbManager(this);
        dbManager.insertTemporaryData();
        Event[] events = dbManager.getAllEvents();

        eventsAdapter.setData(events);

        recyclerView.setAdapter(eventsAdapter);

    }

}
