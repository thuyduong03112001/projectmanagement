package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private int id;
    private String title;
    private String date;
    private String content;
    private String brief;
    private String thumbnail;
    private int hrId;
    private String hrName;
    private int statusId;
    private int cateId;
    private int flag;

    private String fullName;
    private String status;
    private String category;

    public Post() {
    }

    public Post(int id, String title, String date, String content, String brief, String thumbnail, int hrId, String hrName, int statusId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.brief = brief;
        this.thumbnail = thumbnail;
        this.hrId = hrId;
        this.hrName = hrName;
        this.statusId = statusId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRealDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(date);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        String b = formatter.format(a);
        return b;
    }
}
