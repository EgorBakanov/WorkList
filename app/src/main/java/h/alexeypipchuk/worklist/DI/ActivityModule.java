package h.alexeypipchuk.worklist.DI;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import h.alexeypipchuk.worklist.View.MainActivity;

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}
