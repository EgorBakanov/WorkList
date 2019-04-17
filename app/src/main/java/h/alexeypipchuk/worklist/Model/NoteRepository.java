package h.alexeypipchuk.worklist.Model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;

@Singleton
public class NoteRepository {

    private final NoteDao noteDao;

    @Inject
    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<Note> getNote(int taskId) {
        return noteDao.load(taskId);
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteDao.loadAll();
    }

    public void saveNote(Note task) {
        noteDao.save(task);
    }
}
