package com.home.dshapkin.tracking.things.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class UserLocal {

    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLocal userLocal = (UserLocal) o;
        return Objects.equals(username, userLocal.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
