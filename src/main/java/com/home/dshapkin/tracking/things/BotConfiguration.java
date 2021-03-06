package com.home.dshapkin.tracking.things;

import com.home.dshapkin.tracking.things.command.CreateActivityCommand;
import com.home.dshapkin.tracking.things.command.GetActivityCommand;
import com.home.dshapkin.tracking.things.data.ActivityHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;

import java.util.List;

@Configuration
public class BotConfiguration {

    @Bean
    public MyBot myBot(@Value("${telegram.api.username}") String username,
                       @Value("${telegram.api.token}") String token,
                       List<IBotCommand> customCommands) {
        return new MyBot(username, token, customCommands);
    }

    @Bean
    public List<IBotCommand> customCommands(ActivityHandler activityHandler) {
        return List.of(
                new CreateActivityCommand("create", "Создания нового занятия, которое будете отслеживать", activityHandler),
                new GetActivityCommand("get", "Получение всех мероприятий", activityHandler),
                new HelpCommand("help", "Помощь", "extendDescription"));
    }
}
