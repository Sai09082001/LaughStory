package com.example.laughstory.view.act;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.laughstory.OnActionCallBack;
import com.example.laughstory.R;
import com.example.laughstory.entity.Story;
import com.example.laughstory.view.fragment.S001SplashFragment;
import com.example.laughstory.view.fragment.S002MainFragment;
import com.example.laughstory.view.fragment.S003DetailStoryFragment;
import com.example.laughstory.viewmodel.MainViewModel;

import java.util.List;

public class M001MainActivity extends BaseAct<MainViewModel> implements OnActionCallBack {
    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.m001_main_act;
    }

    @Override
    protected void initViews() {
        S001SplashFragment splashFragment = new S001SplashFragment();
        splashFragment.setmCallBack(this);
        // chi co activity ms co the show fragment
        showFragment(R.id.container_view, splashFragment, false);
       // bay gio Fragment ms đc gọi khoi tao view
    }


    @Override
    public void onCallBack(String key, Object... obj) {
        switch (key) {
            case S001SplashFragment.KEY_SHOW_MAIN_FRAGMENT:
                S002MainFragment mainFragment = new S002MainFragment();
                mainFragment.setCallBack(this);
                showFragment(R.id.container_view, mainFragment, false);
                break;
            case S002MainFragment.KEY_SHOW_DETAIL_STORY:
                S003DetailStoryFragment detailStoryFragment = new S003DetailStoryFragment();
                Story story = (Story) obj[0];
                List<Story> listStory = (List<Story>) obj[1];
                detailStoryFragment.setListStory(listStory);
                detailStoryFragment.setStory(story);

                showFragment(R.id.container_view, detailStoryFragment, true);
                break;
        }
    }
}
