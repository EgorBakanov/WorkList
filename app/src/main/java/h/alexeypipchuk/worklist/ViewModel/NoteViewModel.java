package h.alexeypipchuk.worklist.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.Model.NoteRepository;

public class NoteViewModel extends ViewModel {

    private NoteRepository noteRepository;
    private LiveData<Note> note;

    @Inject
    NoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void init(int id) {
        if (note != null && note.getValue().getId() == id) {
            return;
        }

        note = noteRepository.getNote(id);
    }

    public LiveData<Note> getNote() {
        return note;
    }

    public void saveNote(Note note){
        noteRepository.saveNote(note);
    }
}
