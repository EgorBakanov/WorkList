package h.alexeypipchuk.worklist.Utility;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;

public class DataValidator {

    public static boolean validateNote(Context context, String caption, String description, String date, int status, int importance) {

        if (caption == null || caption.isEmpty()) return false;

        if (date != null && !date.isEmpty()) {
            try {
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                df.setLenient(false);
                df.parse(date);
            } catch (ParseException e) {
                return false;
            }
        }

        String[] st = StringsHelper.getAllStatuses(context);
        String[] im = StringsHelper.getAllImportances(context);

        if (status < 0 || status >= st.length) return false;

        if (importance < 0 || importance >= im.length) return false;

        return true;
    }
}
