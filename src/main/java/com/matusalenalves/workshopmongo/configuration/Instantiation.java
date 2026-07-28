package com.matusalenalves.workshopmongo.configuration;

import com.matusalenalves.workshopmongo.domain.Post;
import com.matusalenalves.workshopmongo.domain.User;
import com.matusalenalves.workshopmongo.repository.PostRepository;
import com.matusalenalves.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Post p1 = new Post(null, sdf.parse("21/03/2026"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", maria);
        Post p2 = new Post(null, sdf.parse("23/03/2026"), "Bom dia.", "Acordei feliz hoje!", maria);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(p1, p2));
    }

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
}