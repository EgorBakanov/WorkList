package h.alexeypipchuk.worklist.DI;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import h.alexeypipchuk.worklist.ViewModel.MainActivityViewModel;
import h.alexeypipchuk.worklist.ViewModel.NoteViewModel;
import h.alexeypipchuk.worklist.ViewModel.ViewModelFactory;

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    abstract ViewModel bindNoteViewModel(NoteViewModel mainActivityViewModel);
}
