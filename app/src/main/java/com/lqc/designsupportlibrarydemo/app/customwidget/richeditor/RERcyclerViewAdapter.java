package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.finalteam.galleryfinal.*;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.utils.UILImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
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

    //配置GalleryFinal
    private ThemeConfig theme ;
    private FunctionConfig functionConfig;
    private ImageLoader imageLoader;
    private CoreConfig coreConfig;

    /*
    自定义的每一项点击与长点击事件的接口,外部调用必须实现
     */
//    public interface OnItemClickListener{
//        void onItemClick(View view, int position);
//        void onItemLongClick(View view, int position);
//    }
//
//    private OnItemClickListener mOnItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
//        this.mOnItemClickListener = mOnItemClickListener;
//    }

    public RERcyclerViewAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        initGalleryFinal(); //初始化GalleryFinal
        initImageLoader(context);
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
            final View view = ((HeaderViewHolder) holder).mView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,
                            "click header",
                            Toast.LENGTH_SHORT)
                            .show();
                    GalleryFinal.openGallerySingle(1, new GalleryFinal.OnHanlderResultCallback() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onHanlderSuccess(int i, List<PhotoInfo> list) {
                            Toast.makeText(mContext, list.get(0).getPhotoPath(), Toast.LENGTH_SHORT).show();
                            Bitmap photo = BitmapFactory.decodeFile(list.get(0).getPhotoPath());

//                                view.setBackground(new BitmapDrawable(photo));
                                View v = view.findViewById(R.id.edit_default_v1_cover);
                                LinearLayout.LayoutParams layoutParams =
                                        (LinearLayout.LayoutParams)v
                                                .getLayoutParams();
                                layoutParams.setMargins(0, 0, 0, 22);
                                layoutParams.height = 350;
                                v.setLayoutParams(layoutParams);
                                v.setBackground(new BitmapDrawable(photo));
                                v.findViewById(R.id.edit_cover_iv).setVisibility(View.INVISIBLE);
                                v.findViewById(R.id.edit_cover_tv).setVisibility(View.INVISIBLE);
//                                v.setVisibility(View.INVISIBLE);

//                                view.findViewById(R.id.edit_default_title_cover).setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onHanlderFailure(int i, String s) {
                            Toast.makeText(mContext, "打开相册失败", Toast.LENGTH_SHORT).show();
                        }
                    });
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


    /*
    配置图像编辑器===============================================================
     */

    //init imageLoader
    private void initImageLoader(Context context){
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(mContext);
        config.threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(50*1024*1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs();

        ImageLoader.getInstance().init(config.build());

    }

    //初始化GalleryFinal
    private void initGalleryFinal(){
        theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0x67, 0x3A, 0xB7)).build();
        //功能
        functionConfig = new FunctionConfig.Builder()
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableCamera(true)
                .setForceCrop(true)
                .setCropWidth(800)
                .setCropHeight(600)
                .build();
        ImageLoaderConfiguration.createDefault(mContext);
        cn.finalteam.galleryfinal.ImageLoader imageLoader = new UILImageLoader();

        coreConfig = new CoreConfig.Builder(mContext, imageLoader, theme)
                .setFunctionConfig(functionConfig)
                .setEditPhotoCacheFolder(new File("/sdcard/DICM/LittleHorse/tmp/"))
                .setTakePhotoFolder(new File("/sdcard/DICM/LittleHorse/"))
                .build();
        GalleryFinal.init(coreConfig);
    }

    //配置图像编辑器===============================================================



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
