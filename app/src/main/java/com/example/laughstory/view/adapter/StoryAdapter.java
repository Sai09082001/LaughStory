package com.example.laughstory.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laughstory.R;
import com.example.laughstory.entity.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {
    private final List<Story> listStory;
    private final Context mContext;

    private Story selectedStory;
    private OnItemClick callBack;

    public StoryAdapter(List<Story> listStory, Context context) {
        this.listStory = listStory;
        this.mContext = context;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // tạo một holder ánh xạ từ view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_story, parent, false);

        return new StoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, int position) {
        Story data = listStory.get(position); // lấy vị trí gán data tương ứng cho từng data
        holder.tvName.setText(data.getName());
        holder.lnStory.setBackgroundResource(
                data==selectedStory &&
                data.isSelected() ? R.color.grayLight : R.color.white);
        holder.story = data; // cập nhật dữ liệu cho từng holder chứa từng view của story
    }

    @Override
    public int getItemCount() {
        return listStory.size();
    }

    public void delStory(Story story) {
        listStory.remove(story);
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick event) {
        callBack = event;
    }

    public void setSelectedStory(Story story) {
        if(selectedStory!=null){
            selectedStory.setSelected(false);
        }
        story.setSelected(true);
        selectedStory=story;
        notifyDataSetChanged();
    }

    public interface OnItemClick {
        void onItemClick(Story story);
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        LinearLayout lnStory;
        TextView tvName;
        Story story;

        public StoryHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_click));
                    if (selectedStory != null) {
                        selectedStory.setSelected(false);
                    }
                    story.setSelected(true);

                    notifyDataSetChanged();
                    selectedStory = story;
                    if (callBack != null) {
                        callBack.onItemClick(story);
                    }
                }
            });
            tvName = itemView.findViewById(R.id.tv_name);
            lnStory = itemView.findViewById(R.id.ln_story);
        }
    }
}

