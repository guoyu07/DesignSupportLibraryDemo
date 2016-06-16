package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.interfaces;

import java.util.List;

/**
 * Created by albert on 16-6-16.
 */
public interface BaseEditor<EditDataType> {

    //选中文本操作
    void setTextBold(boolean valid);   //加粗

    void setTextItalic(boolean valid); //斜体

    void setTextunderline(boolean valid);  //下划线

    void strikethrough(boolean valid);  //中间画线

    void setColor(int valid);   //字体颜色

    //光标行文本操作
    void setUlist(boolean valid);  //无序列表

    void setQuote(boolean valid);  //引用

    void setTextSize(int type); //设置文本样式：大标题，小标题，正文

    void link(String src, String desc);//设置链接

    void devidedDot();  //设置分割线

    void setIntroduction(String intro); //设置导语


    //光标行图像操作
    void insertImage(String imagePath); //插入图像

    //导入导出数据
    void storeNote();   //保存editor的内容，包含格式排版，保存的格式是EditDataTYpe决定的

    void loadNote(EditDataType editData);   //加载已经保存的内容

}
