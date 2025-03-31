package co.za.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.za.contacts.adapters.Adapter;
import co.za.contacts.data.Data;
import co.za.contacts.utils.Sender;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        btnAdd = findViewById(R.id.btnAdd);
        //contacts= new ArrayList<>();


        adapter = new Adapter(this, Data.contacts);

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        btnAdd.setOnClickListener(v ->
        {
            Intent intent = new Intent(getApplicationContext(), ContactCardActivity.class);
            intent.putExtra("sender", Sender.ADD.getDescription());
            startActivity(intent);
        });
    }
}