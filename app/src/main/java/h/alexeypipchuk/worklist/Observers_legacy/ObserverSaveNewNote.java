package h.alexeypipchuk.worklist.Observers_legacy;

public class ObserverSaveNewNote {

    // наблюдатель, через который проходят данные для создания новой карточки
    public String caption;
    public int importance;
    public int status;
    public String date;
    public String description;

    public ObserverSaveNewNote(String caption, int status, String description, String date, int importance){
        this.caption = caption;
        this.importance = importance;
        this.status = status;
        this.date = date;
        this.description = description;
    }
}
