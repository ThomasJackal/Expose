package fr.eql.ai116.duron.thomas.art.connect;

import fr.eql.ai116.duron.thomas.art.connect.entity.Address;
import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;
import fr.eql.ai116.duron.thomas.art.connect.entity.Image;
import fr.eql.ai116.duron.thomas.art.connect.entity.Programation;
import fr.eql.ai116.duron.thomas.art.connect.entity.Ticketing;
import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchInputDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchOutputDto;
import fr.eql.ai116.duron.thomas.art.connect.repository.EventRepository;
import fr.eql.ai116.duron.thomas.art.connect.repository.UserRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class DatabaseTests {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Test
    void addUser() {
        User user = new User(
                "thomasd",
                "allo",
                null,
                "t.d@g.c",
                "Thomas",
                "Duron"
        );

        User fetchedUser = userRepo.save(user);
        System.out.println(fetchedUser);
        Assertions.assertEquals(user.getUsername(),fetchedUser.getUsername());
    }

    @Test
    void searchEvents() {

        Event event = new Event(
                "1",
                "",
                new Programation(LocalDate.now(), List.of()),
                EventType.EXPOSITION,
                null,
                new Address(0,0),
                null,
                null
        );
        eventRepository.save(event);

        event = new Event(
                "2",
                "",
                new Programation(LocalDate.now(), List.of()),
                EventType.EXPOSITION,
                null,
                new Address(1,0),
                null,
                null
        );
        eventRepository.save(event);

        event = new Event(
                "3",
                "",
                new Programation(LocalDate.now(), List.of()),
                EventType.EXPOSITION,
                null,
                new Address(0,1),
                null,
                null
        );
        eventRepository.save(event);

        event = new Event(
                "4",
                "",
                new Programation(LocalDate.now(), List.of()),
                EventType.EXPOSITION,
                null,
                new Address(1,1),
                null,
                null
        );
        eventRepository.save(event);

        List<SearchOutputDto> results = eventService.searchEvent(new SearchInputDto(1,1,"",200,null,null));

        System.out.println(results);
    }
}
