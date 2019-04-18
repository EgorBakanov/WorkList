package h.alexeypipchuk.worklist.Utility;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.cardview.widget.CardView;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.R;

@Singleton
public class BackgroundColorHelper {

    private final Context context;

    @Inject
    public BackgroundColorHelper(Context context) {
        this.context = context;
    }

    public void setBackground(CardView view, Note note) {

        int color = context.getResources().getColor(R.color.design_default_color_background);

        if (note.getStatus() == 0) {
            color = context.getResources().getColor(R.color.colorDone);
        } else {
            int[] colors = context.getResources().getIntArray(R.array.color_importance_array);
            if (note.getImportance() >= 0 && note.getImportance() < colors.length)
                color = colors[note.getImportance()];
        }

        view.setCardBackgroundColor(color);
    }
}
