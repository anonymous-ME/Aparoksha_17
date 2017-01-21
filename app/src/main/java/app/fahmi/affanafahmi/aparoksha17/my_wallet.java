package app.fahmi.affanafahmi.aparoksha17;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class my_wallet extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static transaction_adapter adapter;
    public static List<transaction> tran_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet);
        setTitle("IIITA WALLET");
        //Setting font
        Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
        TextView textView = (TextView) findViewById(R.id.lbl_bal);
        textView.setTypeface(tf);
        textView = (TextView) findViewById(R.id.bal);
        textView.setTypeface(tf);
        Button btn = (Button) findViewById(R.id.button);
        btn.setTypeface(tf);
        btn = (Button) findViewById(R.id.button2);
        btn.setTypeface(tf);
        tran_list = new ArrayList<>();;
        for(int i=0;i<100;i++)
            tran_list.add(new transaction("Event "+i,i,new Date(),54.4+((double) i*3)));
        adapter = new transaction_adapter(tran_list,R.layout.tran_det);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.tran_det);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
