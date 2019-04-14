package h.alexeypipchuk.worklist.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverItemNote;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverNewNote;
import h.alexeypipchuk.worklist.Observers_legacy.ObserverSaveNewNote;
import h.alexeypipchuk.worklist.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private String[] mCaptions;
    private String[] mStatuses;
    private String[] mDescriptions;
    private String[] mDates;
    private String[] mImportances;

    private Listener mListener; // слушатель нажатия на конкретную карточку

    MyAdapter() {
        // подгружаем данные из модели
        mCaptions = new String[Note.notes.size()];
        for (int i = 0; i < mCaptions.length; i++) mCaptions[i] = Note.notes.get(i).getmCaption();
        mStatuses = new String[Note.notes.size()];
        for (int i = 0; i < mStatuses.length; i++) mStatuses[i] = Note.notes.get(i).getmDate();
        mDescriptions = new String[Note.notes.size()];
        for (int i = 0; i < mDescriptions.length; i++) mDescriptions[i] = Note.notes.get(i).getmImportance();
        mDates = new String[Note.notes.size()];
        for (int i = 0; i < mDates.length; i++) mDates[i] = Note.notes.get(i).getmStatus();
        mImportances = new String[Note.notes.size()];
        for (int i = 0; i < mImportances.length; i++) mImportances[i] = Note.notes.get(i).getmDescription();

        // подписываемся на наблюдателя за новыми карточками(создание нового дела)
        EventBus.getDefault().register(this);
        // устанавливаем слушатель, передающий в активити через наблюдателя подробные данные о карточке
        setListener(new Listener() {
            @Override
            public void onClick(int position) {
                EventBus.getDefault().post(new ObserverItemNote(position, Note.notes.get(position).getmCaption(),
                        Note.notes.get(position).getmImportance(), Note.notes.get(position).getmStatus(),
                        Note.notes.get(position).getmDate(), Note.notes.get(position).getmDescription()));
            }
        });
    }

    // создаем новую записиь модели, взяв данные из наблюдателя за активити создания нового дела
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverSaveNewNote event) {
        Note.notes.add(new Note(event.caption, event.status, event.description,
                event.date, event.importance));
        // после создания, разрешаем перейти на главную активити
        EventBus.getDefault().post(new ObserverNewNote());
    }

    // работа с ресайкл вью

    public interface Listener {
        void onClick(int position);
    }

    void setListener(Listener listener){
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
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;
        TextView caption = (TextView)cardView.findViewById(R.id.caption);
        caption.setText(mCaptions[position]);
        TextView importance = (TextView)cardView.findViewById(R.id.importance);
        importance.setText(mImportances[position]);
        TextView status = (TextView)cardView.findViewById(R.id.status);
        status.setText(mStatuses[position]);

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
    public int getItemCount(){
        return Note.notes.size();
    }
}
