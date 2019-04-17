package h.alexeypipchuk.worklist.Utility;

import android.content.Context;

import h.alexeypipchuk.worklist.R;

public class StringsHelper {

    public static String getStatus(Context context, int id) {

        return chooseString(getAllStatuses(context), id);
    }

    public static String getImportance(Context context, int id) {

        return chooseString(getAllImportances(context), id);
    }

    public static String[] getAllStatuses(Context context){
        return context.getResources().getStringArray(R.array.statuses_array);
    }

    public static String[] getAllImportances(Context context){
        return context.getResources().getStringArray(R.array.importances_array);
    }

    public static String chooseString(String[] strings, int id) {

        return (id < 0 || id >= strings.length) ?  "" : strings[id];
    }
}
