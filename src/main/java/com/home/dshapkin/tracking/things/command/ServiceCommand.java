package com.home.dshapkin.tracking.things.command;

import com.home.dshapkin.tracking.things.data.UserLocal;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class ServiceCommand extends BotCommand {

    /**
     * Construct a command
     *
     * @param commandIdentifier the unique identifier of this command (e.g. the command string to
     *                          enter into chat)
     * @param description       the description of this command
     */
    public ServiceCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void sendAnswer(AbsSender absSender, UserLocal userLocal, Long chatId, String[] arguments) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(createAnswer(userLocal, arguments));
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            sendError(absSender, chatId);
        }
    }

    public void sendError(AbsSender absSender, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText("Извините, что-то пошло нет");
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public abstract String createAnswer(UserLocal userLocal, String[] arguments);

}
