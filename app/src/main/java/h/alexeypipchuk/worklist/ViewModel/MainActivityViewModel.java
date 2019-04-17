package h.alexeypipchuk.worklist.ViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import h.alexeypipchuk.worklist.Model.Note;
import h.alexeypipchuk.worklist.Model.NoteRepository;

public class MainActivityViewModel extends ViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> notes;

    @Inject
    MainActivityViewModel(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public void init() {
        if (notes != null) {
            return;
        }

        notes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getNotes(){
        return notes;
    }

}
