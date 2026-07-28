package com.matusalenalves.workshopmongo.services;

import com.matusalenalves.workshopmongo.domain.Post;
import com.matusalenalves.workshopmongo.repository.PostRepository;
import com.matusalenalves.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    public List<Post> findByTitle(String title){
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}