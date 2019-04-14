package h.alexeypipchuk.worklist.DI;

import android.app.Application;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import h.alexeypipchuk.worklist.Model.NoteDao;
import h.alexeypipchuk.worklist.Model.NoteDatabase;

@Module
class DBModule {

    @Singleton
    @Provides
    NoteDatabase provideDB(@NonNull Application application) {
        return Room.databaseBuilder(application,
                NoteDatabase.class, "Notes.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    NoteDao provideNoteDao(@NonNull NoteDatabase db) {
        return db.noteDao();
    }
}
