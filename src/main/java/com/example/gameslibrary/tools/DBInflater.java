package com.example.gameslibrary.tools;

import com.example.gameslibrary.model.Game;
import com.example.gameslibrary.model.Genre;
import com.example.gameslibrary.model.Platform;
import com.example.gameslibrary.model.Publisher;
import com.example.gameslibrary.repositories.GameRepository;
import com.example.gameslibrary.repositories.GenreRepository;
import com.example.gameslibrary.repositories.PlatformRepository;
import com.example.gameslibrary.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    private GameRepository gameRepository;
    private GenreRepository genreRepository;
    private PlatformRepository platformRepository;
    private PublisherRepository publisherRepository;

    public DBInflater(GameRepository gameRepository, GenreRepository genreRepository, PlatformRepository platformRepository, PublisherRepository publisherRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.platformRepository = platformRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        // Genres
        Genre action = new Genre("Gry akcji", "Bijatyki, Horrory, Platformery, Rytmiczne, Skradanki, Strzelanki");
        Genre adventure = new Genre("Gry przygodowe", "Graficzne, Interaktywne, Tekstowe");
        Genre rpg = new Genre("Gry RPG", "JRPG, MMORPG, RPG Akcji, Taktyczne");
        Genre sport = new Genre("Gry sportowe", "Drużynowe, Walki, Wyścigowe");
        Genre strategy = new Genre("Gry strategiczne", "MOBA, RTS, Tower defense");
        genreRepository.save(action);
        genreRepository.save(adventure);
        genreRepository.save(rpg);
        genreRepository.save(sport);
        genreRepository.save(strategy);

        // Platforms
        Platform nintendoSwitch = new Platform("Nintendo Switch", "Konsola", "Nintendo", "VIII");
        Platform pc = new Platform("PC", "Komputer osobisty", "Nieokreślony", "Nieokreślona");
        Platform ps4 = new Platform("PlayStation 4", "Konsola", "Sony", "VIII");
        Platform ps5 = new Platform("PlayStation 5", "Konsola", "Sony", "IX");
        Platform xboxOne = new Platform("Xbox One", "Konsola", "Microsoft", "VIII");
        platformRepository.save(nintendoSwitch);
        platformRepository.save(pc);
        platformRepository.save(ps4);
        platformRepository.save(ps5);
        platformRepository.save(xboxOne);

        // Publishers
        Publisher activision = new Publisher("Activision", "USA");
        Publisher blizzard = new Publisher("Blizzard Entertainment", "USA");
        Publisher capcom = new Publisher("Capcom", "Japonia");
        Publisher cdProjekt = new Publisher("CD Projekt RED", "Polska");
        Publisher naughty = new Publisher("Naughty Dog", "USA");
        Publisher rockstar = new Publisher("Rockstar Games", "USA");
        Publisher square = new Publisher("Square Enix", "Japonia");
        Publisher ubisoft = new Publisher("Ubisoft", "Francja");
        publisherRepository.save(activision);
        publisherRepository.save(blizzard);
        publisherRepository.save(capcom);
        publisherRepository.save(cdProjekt);
        publisherRepository.save(naughty);
        publisherRepository.save(rockstar);
        publisherRepository.save(square);
        publisherRepository.save(ubisoft);

        // Games
        Game homm7 = new Game(
                "Might & Magic: Heroes VII",
                "Siódma odsłona jednej z najpopularniejszych turowych strategii fantasy, zapoczątkowanej w 1995 roku. Za powstanie gry odpowiada niemieckie studio Limbic Entertainment, które ma na swoim koncie dodatki do poprzedniej części serii oraz RPG-owy Might & Magic X: Legacy.",
                "2015",
                strategy,
                ubisoft
        );
        homm7.getPlatforms().add(pc);
        pc.getGames().add(homm7);
        gameRepository.save(homm7);
        platformRepository.save(pc);

        Game tlsu2 = new Game(
                "The Last of Us: Part II",
                "Kontynuacja przygodowej gry akcji studia Naughty Dog z 2013 roku, będącej jedną z najwyżej ocenianych tytułów na PlayStation. Fabuła The Last of Us 2 kontynuuje wątki z pierwszej części, gdzie epidemia wywołana przez pasożytnicze grzyby doprowadziła do upadku cywilizacji.",
                "2020",
                action,
                naughty
        );
        tlsu2.getPlatforms().add(ps4);
        ps4.getGames().add(tlsu2);
        gameRepository.save(tlsu2);
        platformRepository.save(ps4);

        Game witcher3 = new Game(
                "Wiedźmin 3: Dziki Gon",
                "Gra action RPG, stanowiąca trzecią część przygód Geralta z Rivii. Podobnie jak we wcześniejszych odsłonach cyklu, Wiedźmin 3: Dziki Gon bazuje na motywach twórczości literackiej Andrzeja Sapkowskiego, jednak nie jest bezpośrednią adaptacją żadnej z jego książek.",
                "2015",
                rpg,
                cdProjekt
        );
        witcher3.getPlatforms().add(nintendoSwitch);
        witcher3.getPlatforms().add(pc);
        witcher3.getPlatforms().add(ps4);
        witcher3.getPlatforms().add(xboxOne);
        nintendoSwitch.getGames().add(witcher3);
        pc.getGames().add(witcher3);
        ps4.getGames().add(witcher3);
        xboxOne.getGames().add(witcher3);
        gameRepository.save(witcher3);
        platformRepository.save(nintendoSwitch);
        platformRepository.save(pc);
        platformRepository.save(ps4);
        platformRepository.save(xboxOne);
    }
}
