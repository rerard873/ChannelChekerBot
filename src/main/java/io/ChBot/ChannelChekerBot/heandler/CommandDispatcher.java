package io.ChBot.ChannelChekerBot.heandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandDispatcher {

    Map<String, CommandHandler> handlers;

    public CommandDispatcher(List<CommandHandler> commands_){
        handlers = commands_.stream().collect(Collectors.toMap(CommandHandler::getCommand, h->h));
    }

    public Optional<SendMessage> dispatch(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            String update_text = update.getMessage().getText();
            CommandHandler currentHandler = handlers.get(update_text);
            if (currentHandler!= null){
                return Optional.of(currentHandler.handle(update));
            }
        }
        return  Optional.empty();
    }
}
