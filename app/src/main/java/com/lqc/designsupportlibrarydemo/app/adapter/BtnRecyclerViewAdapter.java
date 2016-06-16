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
public class BtnRecyclerViewAdapter extends RecyclerView.Adapter<BtnRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mSrcBtnImgs;

    public BtnRecyclerViewAdapter(Context context, List<Integer> srcBtnImgs){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mSrcBtnImgs = srcBtnImgs;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.edit_btn, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final View view = holder.mView;
        view.setBackgroundResource(mSrcBtnImgs.get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext,
//                        "点击选择功能",
//                        Toast.LENGTH_SHORT)
//                        .show();
            }
        });
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
