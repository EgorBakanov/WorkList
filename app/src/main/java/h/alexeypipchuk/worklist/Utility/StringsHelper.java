package h.alexeypipchuk.worklist.Utility;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import javax.inject.Inject;
import javax.inject.Singleton;

import h.alexeypipchuk.worklist.R;

@Singleton
public class StringsHelper {

    private Context context;

    @Inject
    public StringsHelper(Context context){
        this.context = context;
    }

    public String getStatus(int id) {

        return chooseString(getAllStatuses(), id);
    }

    public String getImportance(int id) {

        return chooseString(getAllImportances(), id);
    }

    public String[] getAllStatuses(){
        return context.getResources().getStringArray(R.array.statuses_array);
    }

    public String[] getAllImportances(){
        return context.getResources().getStringArray(R.array.importances_array);
    }

    public String chooseString(String[] strings, int id) {

        return (id < 0 || id >= strings.length) ?  "" : strings[id];
    }

    //https://stackoverflow.com/a/25005243
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public String[] getImgPickerOptions(){
        return context.getResources().getStringArray(R.array.img_picker_options_array);
    }
}
