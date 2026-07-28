package com.matusalenalves.workshopmongo.configuration;

import com.matusalenalves.workshopmongo.domain.Post;
import com.matusalenalves.workshopmongo.domain.User;
import com.matusalenalves.workshopmongo.dto.AuthorDTO;
import com.matusalenalves.workshopmongo.dto.CommentDTO;
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

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Post p1 = new Post(null, sdf.parse("18/03/2026"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, sdf.parse("19/03/2026"), "Bom dia.", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2026"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite.", sdf.parse("23/03/2026"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("22/03/2026"), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);
    }

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
}