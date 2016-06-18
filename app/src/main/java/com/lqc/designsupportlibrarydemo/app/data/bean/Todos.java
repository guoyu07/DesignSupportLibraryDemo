package com.lqc.designsupportlibrarydemo.app.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


/**
 * Created by albert on 16-6-18.
 */
@DatabaseTable(tableName = "tb_todos")
public class Todos {

    //
    public static final String ID_FIELD_NAME = "todoId";
    public static final String CON_FIELD_NAME = "todoCon";
    public static final String DATE_FIELD_NAME = "date";
    public static final String ISCOMPLETE_FIELD_NAME = "isCompleted";

    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private int id;
    @DatabaseField(canBeNull = false, columnName = CON_FIELD_NAME)
    private String todoCon;
    @DatabaseField(columnName = DATE_FIELD_NAME)
    private Date  date;
    @DatabaseField(columnName = ISCOMPLETE_FIELD_NAME)
    private boolean isCompleted;

    public Todos(){
        //默认
        isCompleted = false;
    }
    public Todos(String todoCon){
        this();
        this.todoCon = todoCon;
    }
    public Todos(String todoCon, Date date){
        this(todoCon);
        this.date = date;
    }
    public Todos(String todoCon, Date date, boolean isCompleted){
        this(todoCon, date);
        this.isCompleted = isCompleted;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setTodoCon(String todoCon){
        this.todoCon = todoCon;
    }
    public String getTodoCon(){
        return todoCon;
    }
    public  void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return date;
    }
    public void setCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
    public boolean getCompleted(){
        return isCompleted;
    }

    @Override
    public String toString() {
        return "todo:[name="+todoCon+"]";
    }
}
