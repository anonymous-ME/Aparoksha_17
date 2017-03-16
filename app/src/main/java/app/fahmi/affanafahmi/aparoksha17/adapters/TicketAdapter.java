package app.fahmi.affanafahmi.aparoksha17.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import app.fahmi.affanafahmi.aparoksha17.R;
import app.fahmi.affanafahmi.aparoksha17.activities.EntryPass;
import app.fahmi.affanafahmi.aparoksha17.model.Ticket;


/**
 * Created by Affan A. Fahmi on 19-12-2016.
 */
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {


    private int itemLayout;
    private List<Ticket> items;

    public TicketAdapter(List<Ticket> items,int itemLayout) {
        this.itemLayout = itemLayout;
        this.items = items;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
        Ticket item = items.get(position);
        holder.event_name.setText(item.getEventName());
        holder.amt.setText(item.getAmt()+" ₹");
        holder.dot.setText(new SimpleDateFormat("dd/MM/yyyy").format(item.getDate()));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder

    {
        public TextView event_name;
        public TextView dot;
        public TextView amt;
        private Context context;
        public ViewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            event_name = (TextView) itemView.findViewById(R.id.event_name);
            dot = (TextView) itemView.findViewById(R.id.date);
            amt = (TextView) itemView.findViewById(R.id.amt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle data = new Bundle();
                    data.putString("event_name",event_name.getText().toString());
                    data.putString("date",dot.getText().toString());
                    data.putString("amt",amt.getText().toString());
                    context.startActivity(new Intent(context,EntryPass.class).putExtras(data));
                }
            });
        }
    }
}
