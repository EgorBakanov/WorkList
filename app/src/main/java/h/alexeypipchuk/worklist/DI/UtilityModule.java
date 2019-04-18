package h.alexeypipchuk.worklist.DI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import h.alexeypipchuk.worklist.Utility.DataValidator;
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
    StringsHelper provideStringsHelper() {
        return new StringsHelper();
    }
}
