package model;

public class Slider {
    private int id;
    private String title;
    private String image;
    private int statusId;
    private String backlink;
    private String note;

    private String status;
    
    public Slider() {
    }

    public Slider(int id, String title, String image, int statusId, String backlink, String note) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.statusId = statusId;
        this.backlink = backlink;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
