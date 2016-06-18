package com.lqc.designsupportlibrarydemo.app.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.security.PublicKey;
import java.util.Date;

/**
 * Created by albert on 16-6-18.
 */
@DatabaseTable(tableName = "tb_notes")
public class Notes {

    public static final String ID_FIELD_NAME = "noteId";
    public static final String DATE_FIELD_NAME = "date";
    public static final String ISCOMPLETED_FIELD_NAME = "isCompleted";
    public static final String ISRECYCLED_FIELD_NAME = "isRecycled";
    public static final String TAG_FIELD_NAME = "tags";
    public static final String CON_FIELD_NAME = "noteCon";
    public static final String BRIEF_IMG_FIELD_NAME = "briefImg";
    public static final String BRIEF_TITLE_FIELD_NAME = "briefTitle";
    public static final String BRIEF_CON_FIELD_NAME = "briefCon";
    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private int id;

    //笔记属性：删除了？完成了？
    @DatabaseField(columnName = DATE_FIELD_NAME)
    private Date date;
    @DatabaseField(columnName = ISCOMPLETED_FIELD_NAME)
    private boolean isCompleted;
    @DatabaseField(columnName = ISRECYCLED_FIELD_NAME)
    private boolean isRecycled;
    @DatabaseField(canBeNull = true, foreign = true, columnName = TAG_FIELD_NAME, foreignAutoRefresh = true)
    private Tags tags;

    //简介
    @DatabaseField(columnName = BRIEF_IMG_FIELD_NAME)
    private String briefImg;
    @DatabaseField(columnName = BRIEF_TITLE_FIELD_NAME)
    private String briefTitle;
    @DatabaseField(columnName = BRIEF_CON_FIELD_NAME)
    private String briefCon;

    //主题内容：json数据流
    @DatabaseField(columnName = CON_FIELD_NAME)
    private String noteCon;

    public Notes(){
        //默认的行为
        isCompleted = false;
        isRecycled = false;
        tags = null;
    }
    public Notes(String noteCon){
        this();
        this.noteCon = noteCon;
    }
    public Notes(String noteCon,Date date){
        this(noteCon);
        this.date = date;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
    public boolean getCompleted(){
        return isCompleted;
    }
    public void setRecycled(boolean isRecycled){
        this.isRecycled = isRecycled;
    }
    public boolean getRecycled(){
        return isRecycled;
    }
    public void setBriefImg(String briefImg){
        this.briefImg = briefImg;
    }
    public String getBriefImg(){
        return briefImg;
    }
    public void setBriefTitle(String briefTitle){
        this.briefTitle = briefTitle;
    }
    public String getBriefTitle(){
        return briefTitle;
    }
    public void setBriefCon(String briefCon){
        this.briefCon = briefCon;
    }
    public String getBriefCon(){
        return briefCon;
    }
    public void setNoteCon(String noteCon){
        this.noteCon = noteCon;
    }
    public String getNoteCon(){
        return noteCon;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return date;
    }

    public void setTags(Tags tags){
        this.tags = tags;
    }
    public Tags getTags(){
        return tags;
    }

    @Override
    public String toString() {
        return "note:[name="+briefTitle+"]";
    }
}
