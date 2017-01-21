package app.fahmi.affanafahmi.aparoksha17;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Affan A. Fahmi on 19-12-2016.
 */
public class transaction_adapter extends RecyclerView.Adapter<transaction_adapter.ViewHolder> {


    private int itemLayout;
    private List<transaction> items;

    public transaction_adapter(List<transaction> items, int itemLayout) {
        this.itemLayout = itemLayout;
        this.items = items;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
        transaction item = items.get(position);
        holder.event_name.setText(item.getEvent_name());
        holder.amt.setText(item.getAmt()+" ₹");
        holder.dot.setText(new SimpleDateFormat("dd/MM/yyyy").format(item.getDot()));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder

    {
        public TextView event_name;
        public TextView dot;
        public TextView amt;
        public ViewHolder(final View itemView) {
            super(itemView);
            event_name = (TextView) itemView.findViewById(R.id.event_name);
            dot = (TextView) itemView.findViewById(R.id.date);
            amt = (TextView) itemView.findViewById(R.id.amt);
        }
    }
}
