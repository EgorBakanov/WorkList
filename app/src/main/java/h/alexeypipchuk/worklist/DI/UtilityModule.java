package h.alexeypipchuk.worklist.DI;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import h.alexeypipchuk.worklist.Utility.BackgroundColorHelper;
import h.alexeypipchuk.worklist.Utility.DataValidator;
import h.alexeypipchuk.worklist.Utility.ImageHelper;
import h.alexeypipchuk.worklist.Utility.StringsHelper;

@Module
class UtilityModule {

    @Provides
    @Singleton
    DataValidator provideDataValidator(StringsHelper stringsHelper) {
        return new DataValidator(stringsHelper);
    }

    @Provides
    @Singleton
    StringsHelper provideStringsHelper(Application app) {
        return new StringsHelper(app.getApplicationContext());
    }

    @Provides
    @Singleton
    BackgroundColorHelper provideBackgroundColorHelper(Application app) {
        return new BackgroundColorHelper(app.getApplicationContext());
    }

    @Provides
    @Singleton
    ImageHelper provideImageHelper() {
        return new ImageHelper();
    }
}
