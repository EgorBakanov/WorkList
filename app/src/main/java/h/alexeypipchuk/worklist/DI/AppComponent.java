package h.alexeypipchuk.worklist.DI;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

import h.alexeypipchuk.worklist.WorklistApp;

@Singleton
@Component(modules = {
        ActivityModule.class,
        DBModule.class,
        RepositoryModule.class,
        ViewModelModule.class,
        UtilityModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(WorklistApp app);
}
