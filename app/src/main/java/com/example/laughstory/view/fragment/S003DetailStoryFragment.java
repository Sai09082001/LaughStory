package com.example.laughstory.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.laughstory.App;
import com.example.laughstory.R;
import com.example.laughstory.entity.Story;
import com.example.laughstory.view.adapter.DetailStoryAdapter;
import com.example.laughstory.viewmodel.DetailViewModel;
import com.example.laughstory.viewmodel.MainViewModel;

import java.util.List;

public class S003DetailStoryFragment extends BaseFragment<DetailViewModel> {
    private ViewPager vpStory;
    private List<Story> listStory;
    private Story story;
    private TextView tvIndex;

    @Override
    protected void initViews() {
        tvIndex = rootView.findViewById(R.id.tv_index);
        vpStory = rootView.findViewById(R.id.vp_story);
        initData();
    }

    @Override
    protected Class<DetailViewModel> getClassViewModel() {
        return DetailViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m003_act_main;
    }


    public List<Story> getListStory() {
        return listStory;
    }

    public void setListStory(List<Story> listStory) {
        this.listStory = listStory;
    }

    private void initData() {
        DetailStoryAdapter adapter
                = new DetailStoryAdapter(listStory, getContext());

        vpStory.setAdapter(adapter);

        int pos = listStory.indexOf(story);
        vpStory.setCurrentItem(pos, true);
        tvIndex.setText(String.format("%s/%s", (pos + 1), listStory.size()));

        vpStory.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                Story story = listStory.get(pos);
                App.getInstance().getStorage().setM002Story(story);

                tvIndex.setText(String.format("%s/%s", (pos + 1), listStory.size()));
            }
        });
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
