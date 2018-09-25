package kh.edu.rupp.ckcc.myapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    private Event[] data;

    public void setData(Event[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View viewHolderLayout = layoutInflater.inflate(R.layout.view_holder_event, viewGroup, false);
        EventViewHolder viewHolder = new EventViewHolder(viewHolderLayout);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {

        Event event = data[i];
        eventViewHolder.txtTitle.setText(event.getTitle());
        eventViewHolder.txtDate.setText(event.getDate());

    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.length;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgEvent;
        private TextView txtTitle;
        private TextView txtDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            imgEvent = itemView.findViewById(R.id.img_event);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDate = itemView.findViewById(R.id.txt_date);
        }
    }

}
