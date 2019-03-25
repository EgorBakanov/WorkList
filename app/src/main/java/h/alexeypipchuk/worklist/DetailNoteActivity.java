package h.alexeypipchuk.worklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailNoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_NO = "noteNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        int noteNo = (Integer) getIntent().getExtras().get(EXTRA_NOTE_NO);

//        TextView caption = (TextView) findViewById(R.id.captionD);
//        caption.setText(Note.notes.get(noteNo).getmCaption());
//        TextView date = (TextView) findViewById(R.id.dateD);
//        date.setText(Note.notes.get(noteNo).getmDate());
//        TextView importance = (TextView) findViewById(R.id.importanceD);
//        importance.setText(Note.notes.get(noteNo).getmImportance());
//        TextView status = (TextView) findViewById(R.id.statusD);
//        status.setText(Note.notes.get(noteNo).getmStatus());
//        TextView description = (TextView) findViewById(R.id.descriptionD);
//        description.setText(Note.notes.get(noteNo).getmDescription());
    }
}
