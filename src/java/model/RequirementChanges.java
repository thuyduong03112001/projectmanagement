/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class RequirementChanges {

    int id, requirementId;
    String title;
    int typeId;
    String detail, version;
    int editorId;
    Setting type;
    Account editor;
     
    public RequirementChanges() {
    }

    public RequirementChanges(int id, int requirementId, String title, int typeId, String detail, String version, int editorId) {
        this.id = id;
        this.requirementId = requirementId;
        this.title = title;
        this.typeId = typeId;
        this.detail = detail;
        this.version = version;
        this.editorId = editorId;
    }

    public Setting getType() {
        return type;
    }

    public void setType(Setting type) {
        this.type = type;
    }

    public Account getEditor() {
        return editor;
    }

    public void setEditor(Account editor) {
        this.editor = editor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeId() {
        return type.getId();
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getEditorId() {
        return editor.getId();
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

}
