package com.home.dshapkin.tracking.things.data;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ActivityHandler {

    private final Database database;

    public List<Activity> addActivity(UserLocal user, Activity activity) {
        return database.addActivity(user, activity);

    }

    public List<Activity> addActivities(UserLocal user, List<Activity> activities) {
        return database.addActivities(user, activities);
    }

    public List<Activity> getActivities(UserLocal user) {
        return database.getActivities(user);
    }
}
