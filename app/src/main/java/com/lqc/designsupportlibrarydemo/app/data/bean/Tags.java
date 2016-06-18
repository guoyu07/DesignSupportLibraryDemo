package com.lqc.designsupportlibrarydemo.app.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by albert on 16-6-18.
 */
@DatabaseTable(tableName = "tb_tags")
public class Tags {

    public static final String ID_FIELD_NAME = "tagId";
    public static final String TAGNAME_FIELD_NAME = "tagName";
    public static final String NOTES_FIELD_NAME = "notes";

    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private int id;
    @DatabaseField(columnName = TAGNAME_FIELD_NAME)
    private String tagName;
    @ForeignCollectionField(columnName = NOTES_FIELD_NAME)
    private Collection<Notes> notes;

    public Tags(){
        //默认
    }
    public Tags(String tagName){
        this();
        this.tagName = tagName;
    }
    public Tags(String tagName, Collection<Notes> notes){
        this(tagName);
        this.notes = notes;
    }


    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setTagName(String tagName){
        this.tagName = tagName;
    }
    public String getTagName(){
        return tagName;
    }
    public void setNotes(Collection<Notes> notes){
        this.notes = notes;
    }
    public Collection<Notes> getNotes(){
        return notes;
    }

    @Override
    public String toString() {
        return "tags : [name="+tagName+"]";
    }
}
