package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.finalteam.galleryfinal.*;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.customUnits.LqcEditText;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.utils.UILImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
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
    private int mContentCount = 1;
    private int mBottomCount = 0;
    private List<View> mLayout_views;
    private View activeConView;

    //配置GalleryFinal
    private ThemeConfig theme ;
    private FunctionConfig functionConfig;
    private ImageLoader imageLoader;
    private CoreConfig coreConfig;
    //GalleryFinal事件类型
    private static final int SET_COVER_EVENT = 1;


    public RERcyclerViewAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        initGalleryFinal(); //初始化GalleryFinal
        initImageLoader(context);
        mLayout_views = new ArrayList<View>();  //新建content容器
    }

    public RERcyclerViewAdapter(Context context, int layout_id){
        this(context);
        //默认为第一种布局
        if (layout_id == RichLqcEditor.ARTICLE_DEFAULT){
            this.mLayout_id = R.layout.rl_header;
        }else {
            this.mLayout_id = layout_id;
        }
    }

    public RERcyclerViewAdapter(Context context, List<View> layout_views){
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
            View view = mInflater.inflate(R.layout.rl_content, parent, false);
            view.findViewById(R.id.rl_content_iv).setVisibility(View.GONE);
            mLayout_views.add(view);//增加header时候增加一个默认的content view
            return new HeaderViewHolder(mInflater.inflate(mLayout_id, parent, false));
        }else if (viewType == ITEM_TYPE_CONTENT){
            ContentViewHolder viewHolder =  new ContentViewHolder(mLayout_views.get(getContentItemCount()-1));
            viewHolder.setIsRecyclable(false);
            return viewHolder;
        }else {
            //
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder){
//            final View view = ((HeaderViewHolder) holder).mView;
            final View view = ((HeaderViewHolder)holder).itemView;
            view.setTag(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GalleryFinal.openGallerySingle(SET_COVER_EVENT, new GalleryFinal.OnHanlderResultCallback() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onHanlderSuccess(int i, List<PhotoInfo> list) {
                            Bitmap photo = BitmapFactory.decodeFile(list.get(0).getPhotoPath());
                            //添加图片后修改布局
                            View v = view.findViewById(R.id.edit_default_v1_cover);
                            LinearLayout.LayoutParams layoutParams =
                                    (LinearLayout.LayoutParams) v
                                            .getLayoutParams();
                            layoutParams.setMargins(0, 0, 0, 22);
                            layoutParams.height = 350;
                            v.setLayoutParams(layoutParams);
                            v.setBackground(new BitmapDrawable(photo));
                            v.findViewById(R.id.edit_cover_iv).setVisibility(View.INVISIBLE);
                            v.findViewById(R.id.edit_cover_tv).setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onHanlderFailure(int i, String s) {
                            Toast.makeText(mContext, "打开相册失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }else if (holder instanceof ContentViewHolder){
            final View view = ((ContentViewHolder) holder).itemView;
            view.setTag(position);
            ((ContentViewHolder) holder).mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Toast.makeText(mContext, "聚焦", Toast.LENGTH_SHORT).show();
                    activeConView = view;
                }
            });
        }else if (holder instanceof BottomViewHolder){
            View view = ((BottomViewHolder) holder).itemView;
            view.setTag(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + mContentCount + mBottomCount;
    }

    //获取内容item长度
    private int getContentItemCount(){
        return mContentCount;
    }

    //更新item
    public void addItem(int position, View viewItem){
        mLayout_views.add(viewItem);
        mContentCount++;
        notifyItemInserted(position);
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

    /**
     * 对外接口===================================================================
     */
    public void insertImage(){
        try {
            activeConView.findViewById(R.id.rl_content_iv).setVisibility(View.VISIBLE);
        }catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
        }
    }




    /**
     * 对外接口===================================================================
     */


    //头部标题组件
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
    //内容组件容器
    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        public LqcEditText mEditText;
        public ImageView mImageView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            mEditText = (LqcEditText)itemView.findViewById(R.id.rl_content_edit);
            mImageView = (ImageView)itemView.findViewById(R.id.rl_content_iv);
        }
    }
    //底部组件容器
    public static class BottomViewHolder extends RecyclerView.ViewHolder{
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

}
