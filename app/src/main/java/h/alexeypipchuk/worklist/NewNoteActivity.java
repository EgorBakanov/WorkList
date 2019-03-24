package h.alexeypipchuk.worklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {

    EditText Caption;
    EditText Date;
    EditText Description;
    RadioGroup StatusGroup;
    RadioGroup ImportantGroup;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Caption = (EditText) findViewById(R.id.caption);
        Date = (EditText) findViewById(R.id.date);
        Description = (EditText) findViewById(R.id.description);
        StatusGroup = (RadioGroup) findViewById(R.id.StatusGroup);
        ImportantGroup = (RadioGroup) findViewById(R.id.ImportantGroup);
        btn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Caption.getText() == null || StatusGroup.getCheckedRadioButtonId() == -1 || ImportantGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Заполните обязательные поля", Toast.LENGTH_LONG).show();
                } else {
                    View StatusView = StatusGroup.findViewById(StatusGroup.getCheckedRadioButtonId());
                    RadioButton SattusRb = (RadioButton) StatusGroup.getChildAt(StatusGroup.indexOfChild(StatusView));
                    String StatusState = SattusRb.getText().toString();

                    View ImportantView = ImportantGroup.findViewById(ImportantGroup.getCheckedRadioButtonId());
                    RadioButton ImportantRb = (RadioButton) ImportantGroup.getChildAt(ImportantGroup.indexOfChild(ImportantView));
                    String ImportantState = ImportantRb.getText().toString();

                    Note.notes.add(new Note(Caption.getText().toString(), StatusState,
                            Description.getText().toString(), Date.getText().toString(), ImportantState));
                    startActivity(new Intent(NewNoteActivity.this, MainActivity.class));
                }
            }
        });
    }
}
