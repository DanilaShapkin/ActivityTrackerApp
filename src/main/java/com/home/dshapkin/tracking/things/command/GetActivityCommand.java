package com.home.dshapkin.tracking.things.command;

import com.home.dshapkin.tracking.things.data.Activity;
import com.home.dshapkin.tracking.things.data.ActivityHandler;
import com.home.dshapkin.tracking.things.data.UserLocal;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.stream.Collectors;

public class GetActivityCommand extends ServiceCommand {

    private final ActivityHandler activityHandler;

    public GetActivityCommand(String commandIdentifier, String description, ActivityHandler activityHandler) {
        super(commandIdentifier, description);
        this.activityHandler = activityHandler;
    }

    @Override
    public String createAnswer(UserLocal userLocal, String[] arguments) {
        return activityHandler.getActivities(userLocal).stream()
                .map(Activity::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        UserLocal userLocal = UserLocal.builder()
                .username(user.getUserName())
                .build();

        sendAnswer(absSender, userLocal, chat.getId(), arguments);
    }
}
