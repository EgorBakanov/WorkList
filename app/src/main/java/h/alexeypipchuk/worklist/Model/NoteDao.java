package h.alexeypipchuk.worklist.Model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {

    @Insert(onConflict = REPLACE)
    void save(Note task);

    @Query("SELECT * FROM notes WHERE id = :noteId")
    LiveData<Note> load(int noteId);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> loadAll();
}
