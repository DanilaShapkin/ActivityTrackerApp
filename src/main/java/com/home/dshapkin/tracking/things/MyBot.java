package com.home.dshapkin.tracking.things;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class MyBot extends TelegramLongPollingCommandBot {

    private final String token;

    private final String username;

    public MyBot(String username, String token, List<IBotCommand> commands) {
        this.token = token;
        this.username = username;
        commands.forEach(this::register);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            Message mes = update.getMessage();
            var request = mes.getText();
            var chatId = mes.getChatId();

            var response = switch (request.toLowerCase()) {
                case "привет" -> "Я добрый бот, привет!";
                case "добрые слова для светы" -> "Не грусти, все будет замечательно :-)";
                case "добрые слова для данилы" -> "Ничего не могу придумать!";
                case "хочу уволиться" -> "если хочешь, то конечно, будем стобой пить и весилиться";
                default -> """
                        я могу ответить только на два сообщения:
                        /привет\s
                        /хочу уволиться\s
                        /добрые слова для светы/данилы\s
                        \s
                        Спрашивай что интересно.
                        """;
            };

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(response);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


}
