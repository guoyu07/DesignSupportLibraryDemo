package com.lqc.designsupportlibrarydemo.app.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

/**
 * Created by albert on 16-6-8.
 */

@DatabaseTable(tableName = "tb_edit_list")
public class EditList {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "item_num")
    private int item_id;
    @DatabaseField(columnName = "description")
    private String desc;
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = "create_time")
    private Timestamp time;

    public EditList(){}     //默认提供的无参构造函数

    public EditList(String title, String desc, String content, Timestamp time){
        this.title = title;
        this.desc = desc;
        this.content = content;
        this.time = time;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getItemId(){
        return this.item_id;
    }
    public void setItemId(int item_id){
        this.item_id = item_id;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public Timestamp getTime(){
        return time;
    }
    public void setTime(Timestamp time){
        this.time = time;
    }
}
