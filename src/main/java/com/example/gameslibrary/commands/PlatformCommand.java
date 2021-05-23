package com.example.gameslibrary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlatformCommand {
    private Long id;
    private String name;
    private String type;
    private String producer;
    private String generation;
}
