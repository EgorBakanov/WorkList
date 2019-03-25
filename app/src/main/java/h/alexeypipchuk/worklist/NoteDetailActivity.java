package h.alexeypipchuk.worklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NoteDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_NO = "noteNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Bundle bundle = this.getIntent().getExtras();

        TextView caption = (TextView)findViewById(R.id.captionD);
        caption.setText(bundle.getString("caption"));
        TextView date = (TextView)findViewById(R.id.dateD);
        date.setText(bundle.getString("date"));
        TextView importance = (TextView)findViewById(R.id.importanceD);
        importance.setText(bundle.getString("importance"));
        TextView status = (TextView)findViewById(R.id.statusD);
        status.setText(bundle.getString("status"));
        TextView description = (TextView)findViewById(R.id.descriptionD);
        description.setText(bundle.getString("description"));
    }
}
