package h.alexeypipchuk.worklist.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Note.class},version = 4)
@TypeConverters({h.alexeypipchuk.worklist.Utility.TypeConverters.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
}
