package com.home.dshapkin.tracking.things.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Activity {

    private String name;

    private LocalTime timePerDay;

    private LocalTime timePerWeek;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return name.equals(activity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", timePerDay=" + timePerDay +
                ", timePerWeek=" + timePerWeek +
                '}';
    }
}
