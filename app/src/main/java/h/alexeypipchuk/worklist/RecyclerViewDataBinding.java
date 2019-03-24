package h.alexeypipchuk.worklist;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class RecyclerViewDataBinding {
        @BindingAdapter({"app:adapter", "app:data"})
        public void bind(RecyclerView recyclerView, MyAdapter adapter, List<Note> data) {
            recyclerView.setAdapter(adapter);
            adapter.updateData(data);
        }
}
