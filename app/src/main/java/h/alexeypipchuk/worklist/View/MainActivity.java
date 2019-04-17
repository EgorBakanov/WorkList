package h.alexeypipchuk.worklist.View;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverItemNote;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.ViewModel.MainActivityViewModel;

import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory factory;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter); //адаптер и modelview в одном

        configViewModel();
    }

    //////////////// передача данных конкретного дела в активити
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverItemNote event) {
        Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_NO, event.position);
        intent.putExtra("caption", event.caption);
        intent.putExtra("date", event.date);
        intent.putExtra("description", event.description);
        intent.putExtra("importance", event.importance);
        intent.putExtra("status", event.status);
        startActivity(intent);
    }

    // подписка/отписка от наблюдателей адаптера

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    ////////////////////////////////////////////// меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_plan:
                Intent intent = new Intent(this, NoteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configViewModel(){
        MainActivityViewModel viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        viewModel.init();
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });
    }
}
