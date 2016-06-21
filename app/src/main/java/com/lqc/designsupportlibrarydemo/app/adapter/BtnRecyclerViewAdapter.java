package com.lqc.designsupportlibrarydemo.app.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;

import java.util.List;

/**
 * Created by albert on 16-6-16.
 */
public class BtnRecyclerViewAdapter extends RecyclerView.Adapter<BtnRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mSrcBtnImgs;

    @Override
    public void onClick(View v) {
        if (mOnBtnRecyclerViewClickListener!=null){
            mOnBtnRecyclerViewClickListener.OnItemClick(v, (Integer)v.getTag());
        }
    }

    public static interface OnBtnRecyclerViewClickListener{
        void OnItemClick(View v, int position);
        void OnIntemLongClick(View v, int position);
    }

    private OnBtnRecyclerViewClickListener mOnBtnRecyclerViewClickListener=null;

    public void setOnBtnRecyclerViewClickListener(OnBtnRecyclerViewClickListener listener){
        this.mOnBtnRecyclerViewClickListener = listener;
    }

    public BtnRecyclerViewAdapter(Context context, List<Integer> srcBtnImgs){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mSrcBtnImgs = srcBtnImgs;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.edit_btn, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final View view = holder.mView;
        view.setTag(position);
        view.setBackgroundResource(mSrcBtnImgs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSrcBtnImgs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
