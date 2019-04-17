package h.alexeypipchuk.worklist.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverNewNote;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverSaveNewNote;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.StringsHelper;
import h.alexeypipchuk.worklist.ViewModel.NoteViewModel;

public class NoteActivity extends AppCompatActivity {

    public static final String ID_KEY = "NewNoteActivity_ID_KEY";

    @Inject
    ViewModelProvider.Factory factory;
    NoteViewModel viewModel;
    Note note;

    EditText Caption;
    EditText Date;
    EditText Description;
    RadioGroup StatusGroup;
    RadioGroup ImportantGroup;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        viewModel = ViewModelProviders.of(this, factory).get(NoteViewModel.class);

        initView();

        if (getIntent().hasExtra(ID_KEY)) {
            int id = getIntent().getIntExtra(ID_KEY, 0);
            loadData(id);
        } else note = new Note();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(v);
            }
        });
    }

    private void loadData(int id) {
        viewModel.init(id);
        viewModel.getNote().observe(this, new Observer<Note>() {

            @Override
            public void onChanged(Note note) {
                setInitialData(note);
            }
        });
    }

    private void setInitialData(Note note) {
        this.note = note;
        Caption.setText(note.getCaption());
        Date.setText(note.getDate());
        Description.setText(note.getDescription());
        ((RadioButton) StatusGroup.getChildAt(note.getStatus())).setChecked(true);
        ((RadioButton) ImportantGroup.getChildAt(note.getImportance())).setChecked(true);
    }

    private void handleButtonClick(View v) {
        // простенькая валидация обязательных полей
        if (Caption.getText() == null || StatusGroup.getCheckedRadioButtonId() == -1 || ImportantGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Заполните обязательные поля", Toast.LENGTH_LONG).show();
        } else {
            int StatusState = StatusGroup.getCheckedRadioButtonId();

            int ImportantState = ImportantGroup.getCheckedRadioButtonId();

            // собрали данные и отправляем их наблюдателю, у которого адаптер позже заберет их и создаст новый объект модели

            note.setCaption(Caption.getText().toString());
            note.setDescription(Description.getText().toString());
            note.setDate(Date.getText().toString());
            note.setImportance(ImportantState);
            note.setStatus(StatusState);

            viewModel.saveNote(note);

            this.finish();
            
            /*EventBus.getDefault().post(new ObserverSaveNewNote(Caption.getText().toString(), StatusState,
                    Description.getText().toString(), Date.getText().toString(), ImportantState));*/
        }
    }

    ///////////////// этот наблюдатель ждет разрешения адаптера на переход обратно на главну активность
    // после успешного создания нового объекта модели
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverNewNote event) {
        startActivity(new Intent(NoteActivity.this, MainActivity.class));
    }

    // подписка/отписка
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

    private void initView() {
        Caption = findViewById(R.id.caption);
        Date = findViewById(R.id.date);
        Description = findViewById(R.id.description);
        StatusGroup = findViewById(R.id.StatusGroup);
        ImportantGroup = findViewById(R.id.ImportantGroup);
        btn = findViewById(R.id.floatingActionButton);

        initRadioGroup(StatusGroup, StringsHelper.getAllStatuses(this));
        initRadioGroup(ImportantGroup, StringsHelper.getAllImportances(this));
    }

    private void initRadioGroup(RadioGroup group, String[] values) {
        for (int i = 0; i < values.length; i++) {
            RadioButton b = new RadioButton(this);
            b.setText(values[i]);
            b.setId(i);

            group.addView(b);
        }
    }
}
