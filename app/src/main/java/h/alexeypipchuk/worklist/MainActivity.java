package h.alexeypipchuk.worklist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import h.alexeypipchuk.worklist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();
        initRecyclerView(view);
        //setContentView(R.layout.activity_main);

//        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.setEmployee(employee);

        myAdapter = new MyAdapter();

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        myAdapter.setListener(new MyAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailNoteActivity.class);
                intent.putExtra(DetailNoteActivity.EXTRA_NOTE_NO, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setUp();
    }

    private View bind(){
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModel();
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    /////////////////////////////////////////////////// just menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_plan:
                Intent intent = new Intent(this, NewNoteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
