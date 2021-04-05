package com.example.laughstory.view.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laughstory.App;
import com.example.laughstory.OnActionCallBack;
import com.example.laughstory.R;
import com.example.laughstory.entity.Story;
import com.example.laughstory.entity.Topic;
import com.example.laughstory.view.adapter.StoryAdapter;
import com.example.laughstory.viewmodel.MainViewModel;

public class S002MainFragment extends BaseFragment<MainViewModel> implements StoryAdapter.OnItemClick {
    public static final String KEY_SHOW_DETAIL_STORY = "KEY_SHOW_DETAIL_STORY";
    private DrawerLayout drawer;
    private RecyclerView rvStory;

    private OnActionCallBack callBack;

    @Override
    protected void initViews() {
        findViewById(R.id.iv_menu, this);
        rvStory = findViewById(R.id.rv_story);
        rvStory.setLayoutManager(new LinearLayoutManager(getContext()));

        drawer = findViewById(R.id.drawer);
        LinearLayout lnTopic = findViewById(R.id.ln_topic);
        lnTopic.removeAllViews();

        mModel.initData();
        for (Topic item : mModel.getListTopic()) {
            View v = initTopicView(item);
            v.setOnClickListener(v1 -> clickItem(item));
            lnTopic.addView(v);
        }
        initAdapter();
        // test observe by live data
        getStorage().getM002Story().observe(this, story -> {
//            Toast.makeText(mContext,story.getName(),Toast.LENGTH_SHORT).show();
            ((StoryAdapter)rvStory.getAdapter()).setSelectedStory(story);
            int pos=mModel.getListStory().indexOf(story);
            rvStory.scrollToPosition(pos);
        });
    }

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m002_act_main;
    }


    private void initAdapter() {
        if (mModel.getListStory() == null) return;

        StoryAdapter storyAdapter = new StoryAdapter(mModel.getListStory(), getContext());
        storyAdapter.setOnItemClick(this);
        rvStory.setAdapter(storyAdapter);
    }

    private void clickItem(Topic item) {
        Toast.makeText(getContext(), "Topic: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        //Get list story
        mModel.initListStory(item.getTitle());

        initAdapter();

        drawer.closeDrawers();
    }

    private View initTopicView(Topic item) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_topic, null, false);
        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        TextView tvName = view.findViewById(R.id.tv_name);

        try {
            Glide.with(this).load(BitmapFactory.decodeStream(App.getInstance().getAssets().open(item.getIdName()))).into(ivIcon);
            tvName.setText(item.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_menu) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Story story=App.getInstance().getStorage().getM002Story();
//        if(story!=null){
//            StoryAdapter adapter=(StoryAdapter) rvStory.getAdapter();
//            if(adapter==null) return;
//            adapter.setSelectedStory(story);
//            int index=mModel.getListStory().indexOf(story);
//            rvStory.scrollToPosition(index+5);
//        }
//    }

    @Override
    public void onItemClick(Story story) {
        callBack.onCallBack(KEY_SHOW_DETAIL_STORY, story, mModel.getListStory());
    }
}
