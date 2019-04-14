package h.alexeypipchuk.worklist.Model;

import java.util.ArrayList;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    // просто моделька со сеттерами и геттерами
    @PrimaryKey(autoGenerate = true)
    protected int id;
    private String mCaption;
    private String mStatus;
    private String mDescription;
    private String mDate;
    private String mImportance;
    public static final ArrayList<Note> notes = new ArrayList<Note>();

    public Note(String mCaption, String mStatus, String mDescription, String mDate, String mImportance) {
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

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
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

    public String getImportance() {
        return mImportance;
    }

    public void setImportance(String mImportance) {
        this.mImportance = mImportance;
    }
}
