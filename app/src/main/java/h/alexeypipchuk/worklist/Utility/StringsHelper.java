package h.alexeypipchuk.worklist.Utility;

import android.content.Context;

import javax.inject.Singleton;

import h.alexeypipchuk.worklist.R;

@Singleton
public class StringsHelper {

    public String getStatus(Context context, int id) {

        return chooseString(getAllStatuses(context), id);
    }

    public String getImportance(Context context, int id) {

        return chooseString(getAllImportances(context), id);
    }

    public String[] getAllStatuses(Context context){
        return context.getResources().getStringArray(R.array.statuses_array);
    }

    public String[] getAllImportances(Context context){
        return context.getResources().getStringArray(R.array.importances_array);
    }

    public String chooseString(String[] strings, int id) {

        return (id < 0 || id >= strings.length) ?  "" : strings[id];
    }
}
