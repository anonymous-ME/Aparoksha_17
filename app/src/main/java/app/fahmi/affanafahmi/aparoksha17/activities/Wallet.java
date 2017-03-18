package app.fahmi.affanafahmi.aparoksha17.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

import app.fahmi.affanafahmi.aparoksha17.R;
import app.fahmi.affanafahmi.aparoksha17.model.Ticket;
import app.fahmi.affanafahmi.aparoksha17.utils.CodeScaner;
import app.fahmi.affanafahmi.aparoksha17.viewholder.ViewHolder;

public class Wallet extends AppCompatActivity {
    public static RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet);
        setTitle("IIITA Pay");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null ){
            Intent intent = new Intent(Wallet.this, Login.class);
            startActivity(intent);
        }else if((!user.isEmailVerified())){
            Intent intent = new Intent(Wallet.this, Login.class);
            startActivity(intent);
        }


        final ProgressDialog Dialog = new ProgressDialog(Wallet.this,ProgressDialog.THEME_HOLO_DARK);
        Dialog.setMessage("Loading your wallet...");
        Dialog.show();


        Query query = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("Tickets");



        final FirebaseRecyclerAdapter firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Ticket,ViewHolder> (
                        Ticket.class,
                        R.layout.tran_det,
                        ViewHolder.class,
                        query) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder,
                                                      Ticket model, int position) {
                        viewHolder.event_name.setText(model.getEventName());
                        viewHolder.amt.setText(model.getAmt()+" ₹");
                        viewHolder.dot.setText(new SimpleDateFormat("dd/MM/yyyy").format(model.getDate()));
                    }
                };
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.tran_det);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(user.getUid()).child("wallet_balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                (((TextView) findViewById(R.id.bal))).setText(snapshot.getValue().toString()+ "  ₹");
                Dialog.hide();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_nav, menu);
        return true;
    }


    public void logout(MenuItem item) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(Wallet.this, Login.class)); //Go back to home page
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent intent = new Intent(Wallet.this, Login.class);
            startActivity(intent);
        }else if((!user.isEmailVerified())){
            Intent intent = new Intent(Wallet.this, Login.class);
            startActivity(intent);
        }
    }
}
