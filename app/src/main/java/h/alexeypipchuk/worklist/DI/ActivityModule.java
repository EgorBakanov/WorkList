package h.alexeypipchuk.worklist.DI;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import h.alexeypipchuk.worklist.View.MainActivity;
import h.alexeypipchuk.worklist.View.NoteActivity;

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract NoteActivity contributeNoteActivity();
}
