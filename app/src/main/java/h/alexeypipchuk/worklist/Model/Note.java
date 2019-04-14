package h.alexeypipchuk.worklist.Model;

import java.util.ArrayList;

public class Note {

    // просто моделька со сеттерами и геттерами
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

    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmImportance() {
        return mImportance;
    }

    public void setmImportance(String mImportance) {
        this.mImportance = mImportance;
    }
}
