package h.alexeypipchuk.worklist.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import dagger.android.AndroidInjection;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.DataValidator;
import h.alexeypipchuk.worklist.Utility.StringsHelper;
import h.alexeypipchuk.worklist.ViewModel.NoteViewModel;

public class NoteActivity extends AppCompatActivity {

    public static final String ID_KEY = "NewNoteActivity_ID_KEY";

    @Inject
    ViewModelProvider.Factory factory;
    @Inject
    StringsHelper stringsHelper;
    @Inject
    DataValidator dataValidator;

    NoteViewModel viewModel;
    Note note;

    EditText Caption;
    EditText Date;
    EditText Description;
    RadioGroup StatusGroup;
    RadioGroup ImportantGroup;
    TextView ImgName;

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
        setImgName(note);
    }

    private void setImgName(Note note) {
        ImgName.setText((note != null && note.getImageUri() != null) ?
                stringsHelper.getFileName(note.getImageUri()) : "");
    }

    private void handleSubmitClick() {
        if (!dataValidator.validateNote(
                Caption.getText().toString(),
                Description.getText().toString(),
                Date.getText().toString(),
                StatusGroup.getCheckedRadioButtonId(),
                ImportantGroup.getCheckedRadioButtonId())) {

            Toast.makeText(getApplicationContext(), "Заполните обязательные поля", Toast.LENGTH_LONG).show();
        } else {
            saveNote();
        }
    }

    private void saveNote() {
        int StatusState = StatusGroup.getCheckedRadioButtonId();

        int ImportantState = ImportantGroup.getCheckedRadioButtonId();

        note.setCaption(Caption.getText().toString());
        note.setDescription(Description.getText().toString());
        note.setDate(Date.getText().toString());
        note.setImportance(ImportantState);
        note.setStatus(StatusState);

        viewModel.saveNote(note);

        this.finish();
    }

    private void initView() {
        Caption = findViewById(R.id.caption);
        Date = findViewById(R.id.date);
        Description = findViewById(R.id.description);
        StatusGroup = findViewById(R.id.StatusGroup);
        ImportantGroup = findViewById(R.id.ImportantGroup);
        ImgName = findViewById(R.id.imgName);

        initRadioGroup(StatusGroup, stringsHelper.getAllStatuses());
        initRadioGroup(ImportantGroup, stringsHelper.getAllImportances());

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmitClick();
            }
        });

        findViewById(R.id.imgBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImgClick();
            }
        });
    }

    private void handleImgClick() {

        String[] options = stringsHelper.getImgPickerOptions();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.ImgDialog));

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(getApplicationContext(),"gallery",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"camera",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        dialog.dismiss();
                }
            }
        });
        builder.show();
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
