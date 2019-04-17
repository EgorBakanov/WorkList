package h.alexeypipchuk.worklist.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.StringsHelper;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Listener mListener;

    private List<Note> notes;
    private final Context context;

    MyAdapter(final Context context) {

        this.context = context;

        setListener(new Listener() {
            @Override
            public void onClick(int position) {

                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra(NoteActivity.ID_KEY, notes.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    public interface Listener {
        void onClick(int position);
    }

    private void setListener(Listener listener) {
        this.mListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @NonNull
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
