package h.alexeypipchuk.worklist.Observers_legacy;

public class ObserverItemNote {

    // наблюдатель, через который проходят данные для отображения детального обзора конкретной карточки

    public int position;
    public String caption;
    public String importance;
    public String status;
    public String date;
    public String description;

    public ObserverItemNote(int position, String caption, String importance, String status, String date, String description) {
        this.caption = caption;
        this.importance = importance;
        this.status = status;
        this.date = date;
        this.description = description;
        this.position = position;
    }
}
