package h.alexeypipchuk.worklist;

import java.util.ArrayList;

public class Note {
    private String mCaption;
    private String mStatus;
    private String mDescription;
    private String mDate;
    private String mImportance;

    public Note() {}

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
