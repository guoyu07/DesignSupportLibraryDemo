package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.interfaces.BaseEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 16-6-15.
 */
public class RichLqcEditor extends RecyclerView implements BaseEditor<String>{

    //默认排版布局
    public static final int ARTICLE_DEFAULT = 0;
    public static final int ARTICLE_CARD = 1;

    //attrs
    private int defaultLayout;

    //组件内部需要的零件
    private OnKeyListener keyListener;
    private View mItemView;
    private Context mContext;

    //layoutManager和adapter
    private LinearLayoutManager mLayoutmanager;
    private RERcyclerViewAdapter mAdapter;


    //构造函数
    public RichLqcEditor(Context context) {
        this(context, null);
    }

    public RichLqcEditor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, context.obtainStyledAttributes(attrs, R.styleable.RichLqcEditor)
                .getInt(R.styleable.RichLqcEditor_defaultLayout, 0));
    }

    public RichLqcEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init(attrs);    //初始化布局的参数
        mLayoutmanager = new LinearLayoutManager(context);  //初始化布局器
        mLayoutmanager.setOrientation(OrientationHelper.VERTICAL);

        this.setLayoutManager(mLayoutmanager);
        mAdapter = new RERcyclerViewAdapter(context, defStyle);   //初始化适配器
        this.setAdapter(mAdapter);

    }

    //通过xml文件初始化的设置初始化
    private void init(AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RichLqcEditor);
        defaultLayout = array.getInt(R.styleable.RichLqcEditor_defaultLayout, 0);
        array.recycle();
    }

    private int testPos = 2;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
//            mItemView = LayoutInflater.from(mContext).inflate(R.layout.rl_content,this ,false);
//            try{
//                EditText et = (EditText)mItemView.findViewById(R.id.rl_content_edit);
//                et.setHint("");
//                mItemView.findViewById(R.id.rl_content_iv).setVisibility(View.GONE);
//                mAdapter.addItem(testPos++, mItemView);
//            }catch (NullPointerException e){
//                e.printStackTrace();
//            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void insertImage() {
        this.mAdapter.insertImage();
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.rl_content,this ,false);
        try{
            EditText et = (EditText)mItemView.findViewById(R.id.rl_content_edit);
            et.setHint("");
            mItemView.findViewById(R.id.rl_content_iv).setVisibility(View.GONE);
            mAdapter.addItem(testPos++, mItemView);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
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
    public void storeNote() {

    }

    @Override
    public void loadNote(String editData) {

    }
}
