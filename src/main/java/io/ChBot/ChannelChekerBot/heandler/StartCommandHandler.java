package io.ChBot.ChannelChekerBot.heandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

@Component
public class StartCommandHandler implements CommandHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public SendMessage handle(Update update){
        String chatId = update.getMessage().getChatId().toString();
        String messageText = messageSource.getMessage("command.welcome",null, new Locale("ru"));
        return new SendMessage(chatId,messageText);
    }
}
