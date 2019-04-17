package h.alexeypipchuk.worklist.Model;

import java.util.ArrayList;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    // просто моделька со сеттерами и геттерами
    @PrimaryKey(autoGenerate = true)
    protected int id;
    private String mCaption;
    private int mStatus;
    private String mDescription;
    private String mDate;
    private int mImportance;
    public static final ArrayList<Note> notes = new ArrayList<Note>();

    @Ignore
    public Note(){}

    public Note(int id, String mCaption, int mStatus, String mDescription, String mDate, int mImportance) {
        this.id = id;
        this.mCaption = mCaption;
        this.mStatus = mStatus;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mImportance = mImportance;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getImportance() {
        return mImportance;
    }

    public void setImportance(int mImportance) {
        this.mImportance = mImportance;
    }

    public int getId() {
        return id;
    }
}
