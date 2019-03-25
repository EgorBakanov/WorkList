package h.alexeypipchuk.worklist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import h.alexeypipchuk.worklist.databinding.ActivityMainBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {
    //private MyAdapter myAdapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();

        //myAdapter = new MyAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapter(myAdapter);

//        myAdapter.setListener(new MyAdapter.Listener() {
//            public void onClick(int position) {
//                Intent intent = new Intent(MainActivity.this, DetailNoteActivity.class);
//                intent.putExtra(DetailNoteActivity.EXTRA_NOTE_NO, position);
//                startActivity(intent);
//            }
//        });
    }

    private View bind(){
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModel();
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    //////////////////////////////////////////////  события жизненного цикла

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setUp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.tearDown();
    }

    /////////////////////////////////////////////////// просто менюха

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
