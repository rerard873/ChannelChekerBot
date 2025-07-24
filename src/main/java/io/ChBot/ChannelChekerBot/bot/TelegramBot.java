package io.ChBot.ChannelChekerBot.bot;

import io.ChBot.ChannelChekerBot.config.BotConfig;
import io.ChBot.ChannelChekerBot.heandler.CommandDispatcher;
import io.ChBot.ChannelChekerBot.heandler.StartCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public  class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;
    private final CommandDispatcher dispatcher;

    public TelegramBot(BotConfig config, CommandDispatcher dispatcher) {
        this.config = config;
        this.dispatcher = dispatcher;
    }

    @Override
    public void onUpdateReceived(Update update) {
        dispatcher.dispatch(update).ifPresent(message ->{
            try{
                execute(message);
            }catch (TelegramApiException e) {
                // Изменить обработку
                e.printStackTrace();
            }
        } );
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken() {
        return config.getBotToken();
    }


}
