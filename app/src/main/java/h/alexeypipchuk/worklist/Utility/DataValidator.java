package h.alexeypipchuk.worklist.Utility;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataValidator {

    private StringsHelper stringsHelper;

    @Inject
    public DataValidator(StringsHelper stringsHelper){
        this.stringsHelper = stringsHelper;
    }

    public boolean validateNote(String caption, String description, String date, int status, int importance) {

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

        String[] st = stringsHelper.getAllStatuses();
        String[] im = stringsHelper.getAllImportances();

        if (status < 0 || status >= st.length) return false;

        if (importance < 0 || importance >= im.length) return false;

        return true;
    }
}
