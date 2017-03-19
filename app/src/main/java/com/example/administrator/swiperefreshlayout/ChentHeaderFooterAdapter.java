package com.example.administrator.swiperefreshlayout;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/31.
 */

public class ChentHeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_HEAD = 0;
    private int ITEM_COUNT = 1;
    private int ITEM_FOOT = 2;
    private List<NewslistBean> newslistBeanList;
    private Context context;
    private final LayoutInflater mInflater;
    private View itemView;
//    private int headSize = 0;
//    private int footSize;
//    private boolean isAddHead;
//    private boolean isAddFoot;


    private List<View> headViews;
    private List<View> footViews;
    //这个是父类那个RecyCleViw.ViewHoloder
    RecyclerView.ViewHolder viewHolder = null;
    private int headSize1;
    private int currentPosition;

    public ChentHeaderFooterAdapter(Context context) {
        this.context = context;
        newslistBeanList = new ArrayList<>();
        headViews = new ArrayList<>();
        footViews=new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 实例化ViewHolder
     * @param parent
     * @param viewType
     * @return
     */

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ITEM_HEAD){
            itemView= headViews.get(currentPosition);
            viewHolder= new HeadViewHolder(itemView);
        }else if (viewType==ITEM_COUNT){
            itemView = mInflater.inflate(R.layout.item_recycleview,parent,false);
            viewHolder=new ViewHolder(itemView);
        }else  if (viewType==ITEM_FOOT){
            itemView=footViews.get(currentPosition-headViews.size()-newslistBeanList.size());
            viewHolder=new FootViewHolder(itemView);
        }
        return viewHolder;
    }

    /**
     * 绑定ViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

//        if (holder instanceof HeadViewHolder) {
//            Log.i("position", "headposition=" + position);
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setUri(Uri.parse("http://7mno4h.com2.z0.glb.qiniucdn.com/%E8%8F%9C%E9%B8%9F%E6%96%B0%E9%97%BB.jpg"))
//                    .build();
//            //视图绑定
//            //img
//            ((HeadViewHolder) holder).simpleDraweeView.setController(controller);
//        }
        if (holder instanceof ViewHolder) {
            Log.i("position", "itemposition=" + position);
            Log.i("chent","position="+position);
            position=position-headViews.size();
            String imgUrl = newslistBeanList.get(position).getPicUrl();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(imgUrl))
                    .build();
            //视图绑定
            //img
            ((ViewHolder) holder).simpleDraweeView.setController(controller);
            ((ViewHolder) holder).itemTitle.setText(newslistBeanList.get(position).getTitle());
            ((ViewHolder) holder).itemContent.setText(newslistBeanList.get(position).getCtime());
        }
        //         else if (holder instanceof FootViewHolder) {
//            Log.i("position", "footposition=" + position);
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setUri(Uri.parse("http://7mno4h.com2.z0.glb.qiniucdn.com/cfafa69ae71b4c3aa491778301e1f1cc.jpg"))
//                    .build();
//            //视图绑定
//            //img
//            ((FootViewHolder) holder).simpleDraweeView.setController(controller);
//        }
    }

    /**
     * 返回长度
     *
     * @return
     */
    @Override
    public int getItemCount() {

        if (newslistBeanList.size()==0){
            return 0;
        }
        Log.i("chent","totalLength="+newslistBeanList.size() + headViews.size() + footViews.size());
        Log.i("chent","totalLength="+newslistBeanList.size() +"hhh"+ headViews.size() + "fff"+footViews.size());
        return newslistBeanList.size() + headViews.size() + footViews.size();
    }

    /**
     * 获取某一项的视图类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        currentPosition = position;
        int headSize = headViews.size();
        int footSize = footViews.size();
        if (headSize > 0 && position < headSize) {

            return ITEM_HEAD;
        } else if (footSize>0&& position >= headSize + newslistBeanList.size()) {// 3  5     ===== 0   ---7
            return ITEM_FOOT;
        } else {
            return ITEM_COUNT;
        }

        //  Log.i("chent","itemType"+position);
      /*  if (position==0){//bukeneng
            return ITEM_HEAD;
        }else if (position>0&&position<newslistBeanList.size()+1){
            return ITEM_COUNT;
        }else if(position>=newslistBeanList.size()+1){
            Log.i("chent","itemPosition"+position);
            return ITEM_FOOT;
        }*/
        // return super.getItemViewType(position);
    }

    /**
     * 加载数据
     */

    public void addList(List<NewslistBean> newslist) {
        newslistBeanList.addAll(newslist);//集合添加集合
        Log.i("chent","data="+newslistBeanList.toString());
        //刷新数据
        this.notifyDataSetChanged();
    }

    public void addHeadView(View headView) {
        headViews.add(headView);
        //让它的size加1
        //headSize++;
        //booolean
        //isAddHead=true;
        this.notifyDataSetChanged();
    }

    public void addFootView(View footView) {
        footViews.add(footView);
        //isAddFoot=true;
        notifyDataSetChanged();
    }

    /**
     * ViewHoloder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView itemTitle;
        TextView itemContent;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdView);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemContent = (TextView) itemView.findViewById(R.id.itemContent);
        }
    }

    /**
     * 头部
     */
    static class HeadViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView simpleDraweeView;

        public HeadViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdView);
        }
    }

    /**
     * 底部
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView simpleDraweeView;

        public FootViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView=(SimpleDraweeView) itemView.findViewById(R.id.sdView);
        }
    }
}
