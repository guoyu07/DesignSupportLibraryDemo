package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.lqc.designsupportlibrarydemo.app.R;

/**
 * Created by albert on 16-6-15.
 */
public class LqcEditText extends EditText implements TextWatcher{

    //功能类型
    public static final int FORMAT_SIZE = 0x01;
    public static final int FORMAT_QUOTE = 0x02;
    public static final int FORMAT_ULIST = 0x02;
    public static final int FORMAT_ITALIC = 0x02;
    public static final int FORMAT_UNDERLINED = 0x02;
    public static final int FORMAT_LINK = 0x02;
    public static final int FORMAT_STRIKETHROUGH = 0x02;

    //attr
    private int quoteColor = 0;
    private int quoteStripWidth = 0;
    private int quoteGapWidth = 0;


    public LqcEditText(Context context) {
        super(context);
        init(null);
    }
    public LqcEditText(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs);
    }
    public LqcEditText(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LqcEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RichLqcEditor);
        quoteColor = array.getColor(R.styleable.RichLqcEditor_quoteColor, 0);
        quoteStripWidth = array.getDimensionPixelSize(R.styleable.RichLqcEditor_quoteStripeWidth, 0);
        quoteGapWidth = array.getDimensionPixelSize(R.styleable.RichLqcEditor_quoteGapWitdth, 0);

        array.recycle();
    }

    //没有绘制前调用
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    //view销毁之后调用
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // s:之前的文字内容
        // start:添加文字的位置(从0开始)
        // count:不知道 一直是0
        // after:添加的文字总数
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        // s:之后的文字内容
        // start:添加文字的位置(从0开始)
        // before:不知道 一直是0
        // before:添加的文字总数
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // s:之后的文字内容
    }
}
