package h.alexeypipchuk.worklist.DI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import h.alexeypipchuk.worklist.Model.NoteDao;
import h.alexeypipchuk.worklist.Model.NoteRepository;

@Module
class RepositoryModule {

    @Provides
    @Singleton
    NoteRepository bindNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }
}
