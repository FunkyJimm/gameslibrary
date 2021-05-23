package com.example.gameslibrary.converters;

import com.example.gameslibrary.commands.PlatformCommand;
import com.example.gameslibrary.model.Platform;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PlatformCommandToPlatform implements Converter<PlatformCommand, Platform> {

    @Synchronized
    @Nullable
    @Override
    public Platform convert(PlatformCommand source) {
        if(source == null) {
            return null;
        }

        final Platform platform = new Platform();
        if(source.getId() != null) {
            platform.setId(source.getId());
        }
        platform.setName(source.getName());
        platform.setType(source.getType());
        platform.setProducer(source.getProducer());
        platform.setGeneration(source.getGeneration());
        return platform;
    }
}
