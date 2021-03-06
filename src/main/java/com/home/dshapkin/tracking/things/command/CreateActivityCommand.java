package com.home.dshapkin.tracking.things.command;

import com.home.dshapkin.tracking.things.data.Activity;
import com.home.dshapkin.tracking.things.data.ActivityHandler;
import com.home.dshapkin.tracking.things.data.UserLocal;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class CreateActivityCommand extends ServiceCommand {

    private final ActivityHandler activityHandler;

    /**
     * Construct a command
     *
     * @param commandIdentifier the unique identifier of this command (e.g. the command string to
     *                          enter into chat)
     * @param description       the description of this command
     * @param activityHandler
     */
    public CreateActivityCommand(String commandIdentifier, String description, ActivityHandler activityHandler) {
        super(commandIdentifier, description);
        this.activityHandler = activityHandler;

    }

    @Override
    public String createAnswer(UserLocal userLocal, String[] arguments) {
        activityHandler.addActivity(userLocal, createActivity(arguments));

        return createActivity(arguments).toString();
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        UserLocal userLocal = UserLocal.builder()
                .username(user.getUserName())
                .build();
        sendAnswer(absSender, userLocal, chat.getId(), arguments);
    }

    private Activity createActivity(String[] arguments) {
        List<String> args = Arrays.asList(arguments);
        String name = args.get(0);
        String timePerDay = args.get(1);
        String timePerWeek = args.get(2);
        return Activity.builder()
                .name(name)
                .timePerDay(LocalTime.of(Integer.parseInt(timePerDay),0))
                .timePerWeek(LocalTime.of(Integer.parseInt(timePerWeek),0))
                .build();
    }

}
