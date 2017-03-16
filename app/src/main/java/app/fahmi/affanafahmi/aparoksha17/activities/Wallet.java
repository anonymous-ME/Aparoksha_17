package app.fahmi.affanafahmi.aparoksha17.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.fahmi.affanafahmi.aparoksha17.R;
import app.fahmi.affanafahmi.aparoksha17.adapters.TicketAdapter;
import app.fahmi.affanafahmi.aparoksha17.model.Ticket;
import app.fahmi.affanafahmi.aparoksha17.utils.CodeScaner;

public class Wallet extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static TicketAdapter adapter;
    public static ArrayList<Ticket> ticket_list;
    private DatabaseReference mDatabase;
    private ChildEventListener mChildEventListener;
    private ChildEventListener BalanceListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet);
        setTitle("IIITA WALLET");
        //Setting font
        Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
        TextView textView = (TextView) findViewById(R.id.lbl_bal);
        //textView.setTypeface(tf);
        textView = (TextView) findViewById(R.id.bal);
        //textView.setTypeface(tf);
        Button btn = (Button) findViewById(R.id.button);
        //btn.setTypeface(tf);
        btn = (Button) findViewById(R.id.button2);
        //btn.setTypeface(tf);
        ticket_list = new ArrayList<>();



        mDatabase = FirebaseDatabase.getInstance().getReference();



        mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ticket_list.add(dataSnapshot.getValue(Ticket.class));
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child("Yjks4IPR4sYyXL2GPyyZ7imyKs82").child("Tickets");
        mDatabase.addChildEventListener(mChildEventListener);

        adapter = new TicketAdapter(ticket_list,R.layout.tran_det);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.tran_det);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child("Yjks4IPR4sYyXL2GPyyZ7imyKs82").child("wallet_balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                (((TextView) findViewById(R.id.bal))).setText(snapshot.getValue().toString()+ "  ₹");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void pay(View view) {
        Wallet.this.startActivity((new Intent(Wallet.this, CodeScaner.class)));
    }

    public void refill(View view) {
        Wallet.this.startActivity((new Intent(Wallet.this, PayID.class)));
    }
}
