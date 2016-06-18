package com.lqc.designsupportlibrarydemo.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;

import java.util.List;

/**
 * Created by albert on 16-6-18.
 */
public class MemoRecyclerViewAdapter extends RecyclerView.Adapter<MemoRecyclerViewAdapter.MemoViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<String> mCon;

    /**
     * 自定义每个item点击与长恩点击的监听器接口
     */
    public interface OnItemClickListener{
        void onItemClick(View view, int positon);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MemoRecyclerViewAdapter(Context context){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public MemoRecyclerViewAdapter(Context context, List<String> mCon){
        this(context);
        this.mCon = mCon;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.memo_item, parent, false);
        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {
        TextView view = (TextView)holder.mView.findViewById(R.id.memo_item_tv);
//        ViewGroup.LayoutParams lp = view.getLayoutParams();
        view.setText(mCon.get(position));
//        view.setLayoutParams(lp);

        if (mOnItemClickListener!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    Toast.makeText(context,
                            "点击事件",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context,
                            "点击事件",
                            Toast.LENGTH_SHORT)
                            .show();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCon.size();
    }

    public static class MemoViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        public MemoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
