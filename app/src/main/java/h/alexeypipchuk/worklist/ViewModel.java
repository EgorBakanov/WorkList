package h.alexeypipchuk.worklist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends BaseObservable {
    private static final String TAG = "DataViewModel";
    private MyAdapter adapter;
    private List<Note> data;

    ViewModel() {
        data = new ArrayList<>();
        adapter = new MyAdapter();
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        populateData();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public List<Note> getData() {
        return this.data;
    }

    @Bindable
    public MyAdapter getAdapter() {
        return this.adapter;
    }

    private void populateData() {
        // populate the data from the source, such as the database.
        for (int i = 0; i < 50; i++) {
            Note dataModel = new Note();
            dataModel.setmCaption(String.valueOf(i));
            data.add(dataModel);
        }
        notifyPropertyChanged(BR.data);
    }
}
