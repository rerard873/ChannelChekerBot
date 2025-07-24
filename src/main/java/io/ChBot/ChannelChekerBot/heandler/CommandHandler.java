package io.ChBot.ChannelChekerBot.heandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {
    String getCommand();
    SendMessage handle(Update update);
}
