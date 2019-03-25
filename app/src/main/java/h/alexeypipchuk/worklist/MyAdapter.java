package h.alexeypipchuk.worklist;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mCaptions;
    private String[] mStatuses;
    private String[] mDescriptions;
    private String[] mDates;
    private String[] mImportances;

    private List<Note> notes;

    private Listener mListener;

    public MyAdapter() {
        this.notes = new ArrayList<>();
    }

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, new FrameLayout(parent.getContext()), false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        CardView cardView = holder.cardView;
//        TextView caption = (TextView) cardView.findViewById(R.id.caption);
//        caption.setText(mCaptions[position]);
//        TextView importance = (TextView) cardView.findViewById(R.id.importance);
//        importance.setText(mImportances[position]);
//        TextView status = (TextView) cardView.findViewById(R.id.status);
//        status.setText(mStatuses[position]);

        Note dataModel = notes.get(position);
        holder.setViewModel(new ItemVievModel(dataModel));

//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    mListener.onClick(position);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<Note> data) {
        if (data == null || data.isEmpty()) {
            this.notes.clear();
        } else {
            this.notes.addAll(data);
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
