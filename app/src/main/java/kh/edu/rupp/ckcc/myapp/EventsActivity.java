package kh.edu.rupp.ckcc.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

public class EventsActivity extends AppCompatActivity {

    private InnerClassEventsAdapter eventsAdapter;
    private RecyclerView recyclerView;
    private FrameLayout progressContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);

        recyclerView = findViewById(R.id.recycler_view);
        progressContainer = findViewById(R.id.progress_bar_container);

        // LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adapter
        eventsAdapter = new InnerClassEventsAdapter();

        // Load list of events from DB using build-in API
        /*DbManager dbManager = new DbManager(this);
        dbManager.insertTemporaryData();
        Event[] events = dbManager.getAllEvents();*/

        // Load list of events from DB using library
        /*DbManagerLib dbManagerLib = new DbManagerLib(this);
        Event[] events = dbManagerLib.getAllEvents();
        eventsAdapter.setData(events);*/

        recyclerView.setAdapter(eventsAdapter);

        // Load list events from Web Service
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://test.js-cambodia.com/ckcc/events.php";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Convert response to Event[] using Gson library
                Gson gson = new Gson();
                Event[] events = gson.fromJson(response, Event[].class);
                eventsAdapter.setData(events);
                hideLoading();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideLoading();
                Toast.makeText(EventsActivity.this, "Load data error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);

    }

    private void hideLoading(){
        progressContainer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    // Adapter
    class InnerClassEventsAdapter extends RecyclerView.Adapter<InnerClassEventsAdapter.InnerClassEventViewHolder> {

        private Event[] data;

        public void setData(Event[] data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public InnerClassEventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View viewHolderLayout = layoutInflater.inflate(R.layout.view_holder_event, viewGroup, false);
            InnerClassEventViewHolder viewHolder = new InnerClassEventViewHolder(viewHolderLayout);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull InnerClassEventViewHolder eventViewHolder, int i) {

            Event event = data[i];
            eventViewHolder.txtTitle.setText(event.getTitle());
            eventViewHolder.txtDate.setText(event.getDate());
            eventViewHolder.imgEvent.setImageURI(event.getImageUrl());

        }

        @Override
        public int getItemCount() {
            return (data == null) ? 0 : data.length;
        }

        class InnerClassEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private SimpleDraweeView imgEvent;
            private TextView txtTitle;
            private TextView txtDate;

            public InnerClassEventViewHolder(@NonNull View itemView) {
                super(itemView);

                imgEvent = itemView.findViewById(R.id.img_event);
                txtTitle = itemView.findViewById(R.id.txt_title);
                txtDate = itemView.findViewById(R.id.txt_date);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, EventDetailActivity.class);

                // Pass data
                int index = getAdapterPosition();
                Event event = data[index];
                Gson gson = new Gson();
                String eventJson = gson.toJson(event);
                intent.putExtra("eventJson", eventJson);

                startActivity(intent);
            }
        }
    }
}
