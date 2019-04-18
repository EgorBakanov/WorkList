package h.alexeypipchuk.worklist.Utility;

import android.content.Context;

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
}
