package com.test.servicedemo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityStack {
    private static final  ActivityStack instance = new ActivityStack();
    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public static ActivityStack getInstance() {
        return instance;
    }

    public Activity getTopActivity() {
        if (activities.isEmpty()) {
            return null;
        }
        return activities.get(activities.size() - 1);
    }

    public void finishTopActivity() {
        if (!activities.isEmpty()) {
            activities.remove(activities.size() - 1).finish();
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class activityClass) {
        for ( Activity a : activities ){
            if (a.getClass().equals(activityClass)){
                a.finish();
                activities.remove(a);
            }
        }
    }
}
