package com.example.gameslibrary.converters;

import com.example.gameslibrary.commands.PublisherCommand;
import com.example.gameslibrary.model.Publisher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublisherCommandToCommand implements Converter<PublisherCommand, Publisher> {

    @Synchronized
    @Nullable
    @Override
    public Publisher convert(PublisherCommand source) {
        if(source == null) {
            return null;
        }

        final Publisher publisher = new Publisher();
        if(source.getId() != null) {
            publisher.setId(source.getId());
        }
        publisher.setName(source.getName());
        publisher.setCountry(source.getCountry());
        return publisher;
    }
}
