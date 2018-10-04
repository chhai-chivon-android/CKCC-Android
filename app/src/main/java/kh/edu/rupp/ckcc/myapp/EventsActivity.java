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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EventsActivity extends AppCompatActivity {

    private InnerClassEventsAdapter eventsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adapter
        eventsAdapter = new InnerClassEventsAdapter();

        // Load list of events from DB
        DbManager dbManager = new DbManager(this);
        dbManager.insertTemporaryData();
        Event[] events = dbManager.getAllEvents();

        eventsAdapter.setData(events);

        recyclerView.setAdapter(eventsAdapter);

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

        }

        @Override
        public int getItemCount() {
            return (data == null) ? 0 : data.length;
        }

        class InnerClassEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView imgEvent;
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
                intent.putExtra("title", event.getTitle());
                intent.putExtra("date", event.getDate());
                intent.putExtra("location", event.getLocation());
                intent.putExtra("description", event.getDescription());

                startActivity(intent);
            }
        }
    }
}
