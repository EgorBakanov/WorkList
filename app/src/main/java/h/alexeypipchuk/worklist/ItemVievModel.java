package h.alexeypipchuk.worklist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

public class ItemVievModel extends BaseObservable {
    private Note dataModel;

    public ItemVievModel(Note dataModel) {
        this.dataModel = dataModel;
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public String getTitle() {
        return !TextUtils.isEmpty(dataModel.getmCaption()) ? dataModel.getmCaption() : "";
    }
}
