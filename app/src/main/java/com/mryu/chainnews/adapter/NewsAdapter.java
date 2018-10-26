package com.mryu.chainnews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mryu.chainnews.R;
import com.mryu.chainnews.entity.Data;
import com.mryu.chainnews.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/15 0015.
 * @author yuyanqing
 * @内容 新闻页面的Adapter
 * @date 2018/10/19
 */
//继承RecyclerView优化listview加载慢的缺点
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int SINGLE=1;
    private final int MORE=2;
    private List<Data> dataList=new ArrayList<>();
    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    //俩个俩个方法综合写入 item_news_single item_news_more
    //SingleViewHolder方法
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==SINGLE){
            View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_single,parent,false);
            return new SingleViewHolder(view1);
        }else {
            //MoreViewHolder方法创建
            View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_more,parent,false);
            return new MoreViewHolder(view2);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //这里是三张图片以及标题主副俩个
        final Data data=dataList.get(position);
        if (holder instanceof SingleViewHolder){
            SingleViewHolder singleViewHolder= (SingleViewHolder) holder;
            //赋值给Json数据id
            singleViewHolder.title.setText(data.getTitle());
            singleViewHolder.author.setText(data.getAuthor_name());
            GlideApp.with(singleViewHolder.singleImage).load(data.getThumbnail_pic_s()).into(singleViewHolder.singleImage);
        }else if (holder instanceof MoreViewHolder){
            MoreViewHolder moreViewHolder= (MoreViewHolder) holder;
            //赋值给Json数据id
            moreViewHolder.title.setText(data.getTitle());
            moreViewHolder.date.setText(data.getDate());
            moreViewHolder.author.setText(data.getAuthor_name());
            if (!TextUtils.isEmpty(data.getThumbnail_pic_s())){
                moreViewHolder.imageView1.setVisibility(View.VISIBLE);
                GlideApp.with(moreViewHolder.imageView1).load(data.getThumbnail_pic_s()).into(moreViewHolder.imageView1);
            }else {
                moreViewHolder.imageView1.setVisibility(View.INVISIBLE);
            }
            if (!TextUtils.isEmpty(data.getThumbnail_pic_s02())){
                moreViewHolder.imageView2.setVisibility(View.VISIBLE);
                GlideApp.with(moreViewHolder.imageView2).load(data.getThumbnail_pic_s02()).into(moreViewHolder.imageView2);
            }else {
                moreViewHolder.imageView2.setVisibility(View.INVISIBLE);
            }
            if (!TextUtils.isEmpty(data.getThumbnail_pic_s03())){
                moreViewHolder.imageView3.setVisibility(View.VISIBLE);
                GlideApp.with(moreViewHolder.imageView3).load(data.getThumbnail_pic_s03()).into(moreViewHolder.imageView3);
            }else {
                moreViewHolder.imageView3.setVisibility(View.INVISIBLE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(data);
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
       Data data= dataList.get(position);
       if (!TextUtils.isEmpty(data.getThumbnail_pic_s())&&TextUtils.isEmpty(data.getThumbnail_pic_s02())&&TextUtils.isEmpty(data.getThumbnail_pic_s03())){
           return SINGLE;
       }else {
           return MORE;
       }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setDatas(List<Data> datas){
        dataList.clear();
        addDatas(datas);
    }
    public void addDatas(List<Data> datas){
        dataList.addAll(datas);
        notifyDataSetChanged();
    }

    //find找到适配的几个id
    class SingleViewHolder extends RecyclerView.ViewHolder{
        private TextView title,author;
        private ImageView singleImage;
        public SingleViewHolder(View itemView) {
            super(itemView);
            //主标题
            title=itemView.findViewById(R.id.title);
            //来源于哪里
            author=itemView.findViewById(R.id.author);
            //图片
            singleImage=itemView.findViewById(R.id.single_image);
        }
    }
    //重写三个imageview
    class MoreViewHolder extends RecyclerView.ViewHolder{
        private TextView title,author,date;
        private ImageView imageView1,imageView2,imageView3;
        public MoreViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            author=itemView.findViewById(R.id.author);
            date=itemView.findViewById(R.id.date);
            imageView1=itemView.findViewById(R.id.image_01);
            imageView2=itemView.findViewById(R.id.image_02);
            imageView3=itemView.findViewById(R.id.image_03);
        }

    }
    public interface OnItemClickListener{
        void onItemClick(Data data);
    }
}
