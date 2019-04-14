package h.alexeypipchuk.worklist.View;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverNewNote;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverSaveNewNote;
import h.alexeypipchuk.worklist.R;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

        // собираем все введенные данные
        Caption = (EditText) findViewById(R.id.caption);
        Date = (EditText) findViewById(R.id.date);
        Description = (EditText) findViewById(R.id.description);
        StatusGroup = (RadioGroup) findViewById(R.id.StatusGroup);
        ImportantGroup = (RadioGroup) findViewById(R.id.ImportantGroup);
        btn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // простенькая валидация обязательных полей
                if(Caption.getText() == null || StatusGroup.getCheckedRadioButtonId() == -1 || ImportantGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Заполните обязательные поля", Toast.LENGTH_LONG).show();
                }
                else {
                    View StatusView = StatusGroup.findViewById(StatusGroup.getCheckedRadioButtonId());
                    RadioButton SattusRb = (RadioButton)StatusGroup.getChildAt(StatusGroup.indexOfChild(StatusView));
                    String StatusState = SattusRb.getText().toString();

                    View ImportantView = ImportantGroup.findViewById(ImportantGroup.getCheckedRadioButtonId());
                    RadioButton ImportantRb = (RadioButton)ImportantGroup.getChildAt(ImportantGroup.indexOfChild(ImportantView));
                    String ImportantState = ImportantRb.getText().toString();

                    // собрали данные и отправляем их наблюдателю, у которого адаптер позже заберет их и создаст новый объект модели

                    EventBus.getDefault().post(new ObserverSaveNewNote(Caption.getText().toString(), StatusState,
                            Description.getText().toString(), Date.getText().toString(), ImportantState));
                }
            }
        });
    }

    ///////////////// этот наблюдатель ждет разрешения адаптера на переход обратно на главну активность
    // после успешного создания нового объекта модели
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverNewNote event) {
        startActivity(new Intent(NewNoteActivity.this, MainActivity.class));
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
}
