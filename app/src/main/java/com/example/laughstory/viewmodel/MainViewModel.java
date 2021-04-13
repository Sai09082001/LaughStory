package com.example.laughstory.viewmodel;

import android.util.Log;


import androidx.lifecycle.ViewModel;

import com.example.laughstory.App;
import com.example.laughstory.CommonUtils;
import com.example.laughstory.entity.Story;
import com.example.laughstory.entity.Topic;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getName() ;
    private List<Topic> listTopic;
    private List<Story> listStory;

    public void initData() {
        listTopic = new ArrayList<>();
        try {
            String[] listPath = App.getInstance().getAssets().list("icon");
            for (String fileName : listPath) {
                String iconName = "icon/" + fileName;
                String title = fileName.replace(".png", "");
                listTopic.add(new Topic(iconName, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initListStory(String fileName){
        String story = CommonUtils.getInstance().getTextAs("data/" + fileName + ".txt");
        // Toast.makeText(this,""+story,Toast.LENGTH_LONG).show();
        if (story == null) return;

        String[] items = story.split("','0'\\);");
        if (items.length == 0) return;
        listStory  =new ArrayList<>();

        for (String text : items) {
            int start = text.indexOf("\n", 2);
            if(start==-1){
                continue;
            }
            String name = text.substring(0, start).trim();
            String content = text.substring(start+1);
            //  Toast.makeText(this,"Name"+name,Toast.LENGTH_LONG).show();
            listStory.add(new Story(name, content));
        }
        Log.i(TAG, "Get story success: "+listStory.size());
    }

    public List<Topic> getListTopic() {
        return listTopic;
    }
    public List<Story> getListStory() {
        return listStory;
    }
}
