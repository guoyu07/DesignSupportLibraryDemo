package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;

import java.util.List;

/**
 * Created by albert on 16-6-17.
 */
public class RERcyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private Context mContext;
    private LayoutInflater mInflater;
    private int mLayout_id = 0 ;    //默认layout方式
    private int mHeaderCount = 1;
    private int mBottomCount = 0;
    private List<Integer> mLayout_views;

    public RERcyclerViewAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public RERcyclerViewAdapter(Context context, int layout_id){
        this(context);
        if (layout_id!=0){
            this.mLayout_id = layout_id;
        }
    }

    public RERcyclerViewAdapter(Context context, List<Integer> layout_views){
        this(context, R.layout.rl_header);
        this.mLayout_views = layout_views;
    }

    //判断item类型
    @Override
    public int getItemViewType(int position) {
        int itemCount = getContentItemCount();
        if (mHeaderCount!=0 && position<mHeaderCount){
            return ITEM_TYPE_HEADER;
        }else if (mBottomCount!=0 && position>(mHeaderCount+itemCount)){
            return ITEM_TYPE_BOTTOM;
        }else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER){
            return new HeaderViewHolder(mInflater.inflate(mLayout_id, parent, false));
        }else if (viewType == ITEM_TYPE_CONTENT){
            return new ContentViewHolder(mInflater.inflate(mLayout_views.get(0), parent, false));
        }else {
            //
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder){
            View view = ((HeaderViewHolder) holder).mView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,
                            "click header",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }else if (holder instanceof ContentViewHolder){
            View view = ((ContentViewHolder) holder).mView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,
                            "click content",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }else if (holder instanceof BottomViewHolder){
            View view = ((BottomViewHolder) holder).mView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount+getContentItemCount()+mBottomCount;
    }

    //获取内容item长度
    private int getContentItemCount(){
        if (mLayout_views == null){
            return 0;
        }else {
            return mLayout_views.size();
        }

    }

    //判断是否为HeaderItem
    private boolean isHeaderView(int position){
        return mHeaderCount!=0 && position<mHeaderCount;
    }
    //判断是否为FooterView
    private boolean isBottomView(int position){
        return mBottomCount!=0 && position>=(mHeaderCount+getContentItemCount());
    }


    //头部标题组件
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
    //内容组件容器
    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
    //底部组件容器
    public static class BottomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public BottomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
