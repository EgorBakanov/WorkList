package h.alexeypipchuk.worklist.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverNewNote;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverSaveNewNote;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.StringsHelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mCaptions;
    private String[] mStatuses;
    private int[] mDescriptions;
    private int[] mDates;
    private String[] mImportances;

    private Listener mListener; // слушатель нажатия на конкретную карточку

    private List<Note> notes;
    private final Context context;

    MyAdapter(final Context context) {
        // подгружаем данные из модели
        mCaptions = new String[Note.notes.size()];
        for (int i = 0; i < mCaptions.length; i++) mCaptions[i] = Note.notes.get(i).getCaption();
        mStatuses = new String[Note.notes.size()];
        for (int i = 0; i < mStatuses.length; i++) mStatuses[i] = Note.notes.get(i).getDate();
        mDescriptions = new int[Note.notes.size()];
        for (int i = 0; i < mDescriptions.length; i++)
            mDescriptions[i] = Note.notes.get(i).getImportance();
        mDates = new int[Note.notes.size()];
        for (int i = 0; i < mDates.length; i++) mDates[i] = Note.notes.get(i).getStatus();
        mImportances = new String[Note.notes.size()];
        for (int i = 0; i < mImportances.length; i++)
            mImportances[i] = Note.notes.get(i).getDescription();

        this.context = context;
        // подписываемся на наблюдателя за новыми карточками(создание нового дела)
        EventBus.getDefault().register(this);
        // устанавливаем слушатель, передающий в активити через наблюдателя подробные данные о карточке
        setListener(new Listener() {
            @Override
            public void onClick(int position) {
                /*EventBus.getDefault().post(new ObserverItemNote(position, Note.notes.get(position).getCaption(),
                        Note.notes.get(position).getImportance(), Note.notes.get(position).getStatus(),
                        Note.notes.get(position).getDate(), Note.notes.get(position).getDescription()));*/

                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra(NoteActivity.ID_KEY, notes.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    // создаем новую записиь модели, взяв данные из наблюдателя за активити создания нового дела
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverSaveNewNote event) {
        Note.notes.add(new Note(0, event.caption, event.status, event.description,
                event.date, event.importance));
        // после создания, разрешаем перейти на главную активити
        EventBus.getDefault().post(new ObserverNewNote());
    }

    // работа с ресайкл вью

    public interface Listener {
        void onClick(int position);
    }

    void setListener(Listener listener) {
        this.mListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        String status = StringsHelper.getStatus(context, notes.get(position).getStatus());
        String importance = StringsHelper.getImportance(context, notes.get(position).getImportance());

        ((TextView) cardView.findViewById(R.id.caption)).setText(notes.get(position).getCaption());
        ((TextView) cardView.findViewById(R.id.importance)).setText(importance);
        ((TextView) cardView.findViewById(R.id.status)).setText(status);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        this.notifyDataSetChanged();
    }
}
