package h.alexeypipchuk.worklist;

public class DataBindingComponent implements android.databinding.DataBindingComponent {
    @Override
    public RecyclerViewDataBinding getRecyclerViewDataBinding() {
        return new RecyclerViewDataBinding();
    }
}