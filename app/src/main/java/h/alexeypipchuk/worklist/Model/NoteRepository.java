package h.alexeypipchuk.worklist.Model;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteRepository {

    private final NoteDao noteDao;

    @Inject
    NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}
