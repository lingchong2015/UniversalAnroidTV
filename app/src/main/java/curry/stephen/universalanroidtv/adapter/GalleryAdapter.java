package curry.stephen.universalanroidtv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import curry.stephen.universalanroidtv.R;
import curry.stephen.universalanroidtv.model.MediaItemModel;

/**
 * Created by txt on 2015/11/11.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Integer> mTestDataList;

    /**
     * 按下事件接口.
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    /**
     * 选中事件接口.
     */
    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemSelectListener mOnItemSelectListener;

    /**
     * 设置选中事件接口.
     */
    public void setOnItemSelectListener(OnItemSelectListener listener) {
        mOnItemSelectListener = listener;
    }

    /**
     * 设置按下事件接口.
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GalleryAdapter(Context context, List<Integer> testDataList) {
        mInflater = LayoutInflater.from(context);
        mTestDataList = testDataList;
    }

    public void setTestDataList(List testDataList) {
        mTestDataList = testDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_item, parent, false);//为每个Item生成视图.

        ViewHolder holder = new ViewHolder(view);//构建基于视图的ViewHolder, ViewHolder必须继承自RecyclerView.ViewHolder.
        holder.mImageView = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);//初始化ViewHolder的ImageView.

        return holder;
    }

    private void setItemViewLayoutParams(View itemView) {
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        //Do something ...
        itemView.setLayoutParams(layoutParams);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //绑定视图数据.
        holder.mImageView.setImageResource(mTestDataList.get(position % mTestDataList.size()));//设置ViewHolder的ImageView.

        //设置视图可以获取焦点.
        holder.itemView.setFocusable(true);
//        holder.itemView.setTag(position);//存储数据, position用于当View焦点发生变化时, 获取View当前的位置.

        //设置itemView焦点变化监听器接口, 行为是触发GalleryAdapter的选中事件监听器实现.
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    int position = (int) holder.itemView.getTag();
                    mOnItemSelectListener.onItemSelect(holder.itemView, holder.getLayoutPosition());
                }
            }
        });

        //设置itemView单击监听器接口, 行为是触发GalleryAdapter的按下事件监听器实现.
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(v, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTestDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);//将参数itemView传入的值绑定到ViewHolder的itemView中.
        }
    }
}
