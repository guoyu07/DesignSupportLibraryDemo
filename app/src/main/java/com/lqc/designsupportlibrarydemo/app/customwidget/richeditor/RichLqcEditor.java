package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.interfaces.BaseEditor;

/**
 * Created by albert on 16-6-15.
 */
public class RichLqcEditor extends RecyclerView implements BaseEditor{

    private int quoteColor = 0;
    private int quoteStripWidth = 0;
    private int quoteGapWidth = 0;

    //构造函数
    public RichLqcEditor(Context context) {
        this(context, null);
    }

    public RichLqcEditor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichLqcEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RichLqcEditor);
        quoteColor = array.getColor(R.styleable.RichLqcEditor_quoteColor, 0);
        quoteStripWidth = array.getDimensionPixelSize(R.styleable.RichLqcEditor_quoteStripeWidth, 0);
        quoteGapWidth = array.getDimensionPixelSize(R.styleable.RichLqcEditor_quoteGapWitdth, 0);

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
    public void insertImage(String imagePath) {

    }

    @Override
    public void storeNote() {

    }

    @Override
    public void loadNote(Object editData) {

    }
}
