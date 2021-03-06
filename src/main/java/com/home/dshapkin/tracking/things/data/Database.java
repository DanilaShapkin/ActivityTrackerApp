package com.home.dshapkin.tracking.things.data;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class Database {

    private final Map<UserLocal, List<Activity>> data;

    public List<Activity> getActivities(UserLocal userLocal) {
        return Optional.ofNullable(data.get(userLocal))
                .orElse(Collections.emptyList());
    }

    public List<Activity> addActivity(UserLocal userLocal, Activity activity) {
        return Optional.ofNullable(data.get(userLocal))
                .map(it -> {
                    List<Activity> oldAct = new ArrayList<>(it);
                    oldAct.add(activity);
                    return ImmutableList.copyOf(oldAct);
                })
                .map(it ->  data.put(userLocal, it))
                .orElseGet(() -> data.put(userLocal, ImmutableList.of(activity)));
    }

    public List<Activity> addActivities(UserLocal userLocal, List<Activity> activity) {
        return Optional.ofNullable(data.get(userLocal))
                .map(it -> it.addAll(activity))
                .map(it -> data.get(userLocal))
                .orElseGet(() -> data.put(userLocal, activity));

    }

    public boolean setActivities(UserLocal userLocal, List<Activity> activities) {
        data.put(userLocal, activities);
        return true;
    }
}
