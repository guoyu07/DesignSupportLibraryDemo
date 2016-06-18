package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BulletSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.widget.EditText;
import com.lqc.designsupportlibrarydemo.app.R;

import java.util.ArrayList;
import java.util.List;

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
    private int bulletColor = 0;
    private int bulletRadius = 0;
    private int bulletGapWidth = 0;


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
        bulletColor = array.getColor(R.styleable.RichLqcEditor_bulletColor, 0);
        bulletRadius = array.getDimensionPixelSize(R.styleable.RichLqcEditor_bulletRadius, 0);
        bulletGapWidth = array.getDimensionPixelSize(R.styleable.RichLqcEditor_bulletGapWidth, 0);

        array.recycle();
    }

    // 样式==============================================================================================

    public void bold(boolean valid){
        if (valid){
            styleValid(Typeface.BOLD, getSelectionStart(), getSelectionEnd());
        }else {
            styleInvalid(Typeface.BOLD, getSelectionStart(), getSelectionEnd());
        }
    }

    public void italic(boolean valid){
        if (valid){
            styleValid(Typeface.ITALIC, getSelectionStart(), getSelectionEnd());
        }else {
            styleInvalid(Typeface.ITALIC, getSelectionStart(), getSelectionEnd());
        }
    }

    protected void styleValid(int style, int start, int end){
        switch (style){
            case Typeface.NORMAL:
            case Typeface.BOLD:
            case Typeface.ITALIC:
            case Typeface.BOLD_ITALIC:
                break;
            default:
                return;
        }
        if (start>=end){
            return;
        }
        //设置文本Span
        getEditableText().setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    protected void styleInvalid(int style, int start, int end){
        switch (style){
            case Typeface.NORMAL:
            case Typeface.BOLD:
            case Typeface.ITALIC:
            case Typeface.BOLD_ITALIC:
                break;
            default:
                return;
        }
        if (start>=end){
            return;
        }
        StyleSpan[] spans = getEditableText().getSpans(start, end, StyleSpan.class);
        List<LqcPart> list = new ArrayList<LqcPart>();

        for (StyleSpan span:spans){
            if (span.getStyle() == style){
                list.add(new LqcPart(getEditableText().getSpanStart(span), getEditableText().getSpanEnd(span)));
                getEditableText().removeSpan(span);
            }
        }

        for (LqcPart part:list){
            if(part.isValid()){
                if (part.getStart()<start){
                    styleValid(style, part.getStart(), start);
                }
                if (part.getEnd()>end){
                    styleValid(style, end, part.getEnd());
                }
            }
        }
    }
    protected boolean containStyle(int style, int start, int end){
        switch (style){
            case Typeface.NORMAL:
            case Typeface.BOLD:
            case Typeface.ITALIC:
            case Typeface.BOLD_ITALIC:
                break;
            default:
                return false;
        }
        if (start>end){
            return false;
        }
        if (start == end){
            if (start-1<0||start+1>getEditableText().length()){
                return false;
            }else {
                StyleSpan[] before = getEditableText().getSpans(start-1, start, StyleSpan.class);
                StyleSpan[] after = getEditableText().getSpans(start, start+1, StyleSpan.class);
                return before.length>0 && after.length>0 && before[0].getStyle()==style && after[0].getStyle()==style;
            }
        }else {
            StringBuffer builder = new StringBuffer();
            for (int i=start;i<end;i++){
                StyleSpan[] spans = getEditableText().getSpans(i, i+1, StyleSpan.class);
                for(StyleSpan span:spans){
                    if (span.getStyle()==style){
                        builder.append(getEditableText().subSequence(i, i+1).toString());
                        break;
                    }
                }
            }
            return getEditableText().subSequence(start, end).toString().equals(builder.toString());
        }
    }

    // UnderlineSpan==================================================================================

    public void underline(boolean valid){
        if (valid){
            underlineValid(getSelectionStart(), getSelectionEnd());
        }else {
            underlineInvalid(getSelectionStart(), getSelectionEnd());
        }
    }
    protected void underlineValid(int start, int end){
        if (start>=end){
            return;
        }
        //设置文本Span
        getEditableText().setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    protected void underlineInvalid(int start, int end){
        if (start>=end){
            return;
        }

        UnderlineSpan[] spans = getEditableText().getSpans(start, end, UnderlineSpan.class);
        List<LqcPart> list = new ArrayList<LqcPart>();

        for (UnderlineSpan span:spans){
            list.add(new LqcPart(getEditableText().getSpanStart(span), getEditableText().getSpanEnd(span)));
            getEditableText().removeSpan(span);
        }

        for (LqcPart part:list){
            if (part.isValid()){
                if(part.getStart()<start){
                    underlineValid(part.getStart(), start);
                }
                if (part.getEnd()>end){
                    underlineValid(end, part.getEnd());
                }
            }
        }
    }

    protected boolean containUnderline(int start, int end){
        if (start>end){
            return false;
        }
        if (start == end){
            if (start-1<0||start+1>getEditableText().length()){
                return false;
            }else {
                UnderlineSpan[] before = getEditableText().getSpans(start-1, start, UnderlineSpan.class);
                UnderlineSpan[] after = getEditableText().getSpans(start, start+1, UnderlineSpan.class);
                return before.length>0 && after.length>0;
            }
        }else {
            StringBuffer builder = new StringBuffer();
            for (int i=start;i<end;i++){
                if (getEditableText().getSpans(i, i+1, UnderlineSpan.class).length>0){
                    builder.append(getEditableText().subSequence(i, i+1).toString());
                }
            }
            return getEditableText().subSequence(start, end).toString().equals(builder.toString());
        }
    }


    // StrikethroughSpan ===============================================================================
    public void strikethrough(boolean valid){
        if (valid){
            strikethroughValid(getSelectionStart(), getSelectionEnd());
        }else {
            strikethroughInValid(getSelectionStart(), getSelectionEnd());
        }
    }

    protected void strikethroughValid(int start, int end){
        if (start>=end){
            return;
        }
        getEditableText().setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    protected void strikethroughInValid(int start, int end){
        if (start>=end){
            return;
        }
        StrikethroughSpan[] spans = getEditableText().getSpans(start, end, StrikethroughSpan.class);
        List<LqcPart> list = new ArrayList<LqcPart>();

        for (StrikethroughSpan span:spans){
            list.add(new LqcPart(getEditableText().getSpanStart(span), getEditableText().getSpanEnd(span)));
            getEditableText().removeSpan(span);
        }

        for (LqcPart part:list){
            if (part.isValid()){
                if (part.getStart()<start){
                    strikethroughValid(part.getStart(), start);
                }
                if (part.getEnd()>end){
                    strikethroughValid(end, part.getEnd());
                }
            }
        }
    }

    protected boolean containStrikethrough(int start, int end){
        if (start>end){
            return false;
        }
        if (start == end){
            if (start-1>0||start+1>getEditableText().length()){
                return false;
            }else {
                StrikethroughSpan[] before = getEditableText().getSpans(start-1, start, StrikethroughSpan.class);
                StrikethroughSpan[] after = getEditableText().getSpans(start, start+1, StrikethroughSpan.class);
                return before.length>0 && after.length>0;
            }
        }else {
            StringBuffer builder = new StringBuffer();
            for (int i=start;i<end;i++){
                if (getEditableText().getSpans(i, i+1, StrikethroughSpan.class).length>0){
                    builder.append(getEditableText().subSequence(i, i+1).toString());
                }
            }
            return getEditableText().subSequence(start, end).toString().equals(builder.toString());
        }
    }

    //UlistSpan =======================================================================================

    protected void uListValid(){
        String[] lines = TextUtils.split(getEditableText().toString(), "\n");

        for (int i = 0;i<lines.length;i++){
            if (containUlist(i)){
                continue;
            }

            int lineStart = 0;
            for (int j=0;j<i;j++){
                lineStart = lineStart + lines[j].length()+1; // \n
            }

            int lineEnd = lineStart+lines[i].length();
            if (lineStart>=lineEnd){
                continue;
            }

            //find selection area inside
            int bulletStart = 0;
            int bulletEnd = 0;
            if (lineStart<=getSelectionStart()&&getSelectionEnd()<=lineEnd){
                bulletStart = lineStart;
                bulletEnd = lineEnd;
            }else  if (getSelectionStart()<=lineStart&&lineEnd<=getSelectionEnd()){
                bulletStart = lineStart;
                bulletEnd = lineEnd;
            }

            if (bulletStart<bulletEnd){
                getEditableText().setSpan(new LqcBulletSpan(bulletColor, bulletRadius, bulletGapWidth),
                        bulletStart, bulletEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    protected void UlistInvalid(){
        String[] lines = TextUtils.split(getEditableText().toString(), "\n");

        for (int i=0;i<lines.length;i++){
            if (!containUlist(i)){
                continue;
            }
            int lineStart = 0;
            for (int j=0;j<i;j++){
                lineStart = lineStart+lines[j].length()+1;
            }
            int lineEnd = lineStart + lines[i].length();
            if (lineStart>=lineEnd){
                continue;
            }

            int bulletStart = 0;
            int bulletEnd = 0;
            if (lineStart<=getSelectionStart()&&getSelectionEnd()<=lineEnd){
                bulletStart = lineStart;
                bulletEnd = lineEnd;
            }else if (getSelectionStart()<=lineStart&&lineEnd<=getSelectionEnd()){
                bulletStart = lineStart;
                bulletEnd = lineEnd;
            }

            if (bulletStart<bulletEnd){
                BulletSpan[] spans = getEditableText().getSpans(bulletStart, bulletEnd, BulletSpan.class);
                for (BulletSpan span:spans){
                    getEditableText().removeSpan(span);
                }
            }
        }
    }

    protected boolean containUlist(){
        String[] lines = TextUtils.split(getEditableText().toString(), "\n");
        List<Integer> list = new ArrayList<Integer>();

        for (int i=0;i<lines.length;i++){
            int lineStart = 0;
            for (int j=0;j<i;j++){
                lineStart = lineStart + lines[j].length() + 1;
            }
            int lineEnd = lineStart = lines[i].length();
            if (lineStart>=lineEnd){
                continue;
            }
            if (lineStart<=getSelectionStart()&&getSelectionEnd()<=lineEnd){
                list.add(i);
            }else if (getSelectionStart()<=lineStart&&lineEnd<=getSelectionEnd()){
                list.add(i);
            }
        }
        for (Integer i:list){
            if (!containUlist(i)){
                return false;
            }
        }
        return true;
    }

    protected boolean containUlist(int index){
        String[] lines = TextUtils.split(getEditableText().toString(), "\n");
        if (index<0||index>=lines.length){
            return false;
        }
        int start = 0;
        for (int i=0;i<index;i++){
            start = start + lines[i].length()+1;
        }
        int end = start+lines[index].length();
        if (start>=end){
            return false;
        }

        BulletSpan[] spans = getEditableText().getSpans(start, end, BulletSpan.class);
        return spans.length>0;
    }

    // QuoteSpan =========================================================================================


    //没有绘制前调用
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        addTextChangedListener(this);
    }

    //view销毁之后调用
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        removeTextChangedListener(this);
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
        // s:之后的文字
    }
}
