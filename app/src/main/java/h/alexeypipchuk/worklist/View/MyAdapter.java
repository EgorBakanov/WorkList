package h.alexeypipchuk.worklist.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.BackgroundColorHelper;
import h.alexeypipchuk.worklist.Utility.StringsHelper;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Listener mListener;

    private List<Note> notes;
    private final StringsHelper stringsHelper;
    private final BackgroundColorHelper colorHelper;
    private final Context context;

    MyAdapter(final Context context, final StringsHelper helper, final BackgroundColorHelper backgroundColorHelper) {

        this.stringsHelper = helper;
        this.colorHelper = backgroundColorHelper;
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
        String status = stringsHelper.getStatus(notes.get(position).getStatus());
        String importance = stringsHelper.getImportance(notes.get(position).getImportance());
        Uri imgUri = notes.get(position).getImageUri();

        ((TextView) cardView.findViewById(R.id.caption)).setText(notes.get(position).getCaption());
        ((TextView) cardView.findViewById(R.id.importance)).setText(importance);
        ((TextView) cardView.findViewById(R.id.status)).setText(status);

        colorHelper.setBackground(cardView, notes.get(position));

        if (imgUri != null) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ((ImageView) cardView.findViewById(R.id.imageView)).setImageBitmap(bitmap);
        }

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
