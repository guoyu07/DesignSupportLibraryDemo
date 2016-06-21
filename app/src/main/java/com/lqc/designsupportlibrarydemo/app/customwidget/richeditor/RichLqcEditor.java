package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.interfaces.BaseEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 16-6-15.
 */
public class RichLqcEditor extends RecyclerView implements BaseEditor<String>{

    //layoutManager和adapter
    private LinearLayoutManager mLayoutmanager;
    private RERcyclerViewAdapter mAdapter;

    //attrs
    private int defaultLayoutV1 = 0;

    //构造函数
    public RichLqcEditor(Context context) {
        this(context, null);
    }

    public RichLqcEditor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichLqcEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);    //初始化布局的参数
        mLayoutmanager = new LinearLayoutManager(context);  //初始化布局器
        mLayoutmanager.setOrientation(OrientationHelper.VERTICAL);

        this.setLayoutManager(mLayoutmanager);

        //默认第一种布局
        if (defaultLayoutV1 == 1){
            mAdapter = new RERcyclerViewAdapter(context, R.layout.rl_header);   //初始化适配器
            this.setAdapter(mAdapter);
        }
    }

    private void init(AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RichLqcEditor);
        defaultLayoutV1 = array.getInt(R.styleable.RichLqcEditor_defaultLayoutV1, 0);
        array.recycle();
    }


    @Override
    public void setTextBold(boolean valid) {

    }

    @Override
    public void setTextItalic(boolean valid) {

    }

    @Override
    public void setTextunderline(boolean valid) {

    }

    @Override
    public void strikethrough(boolean valid) {

    }

    @Override
    public void setColor(int valid) {

    }

    @Override
    public void setUlist(boolean valid) {

    }

    @Override
    public void setQuote(boolean valid) {

    }

    @Override
    public void setTextSize(int type) {

    }

    @Override
    public void link(String src, String desc) {

    }

    @Override
    public void devidedDot() {

    }

    @Override
    public void setIntroduction(String intro) {

    }

    @Override
    public void insertImage() {

    }

    @Override
    public void storeNote() {

    }

    @Override
    public void loadNote(String editData) {

    }
}
