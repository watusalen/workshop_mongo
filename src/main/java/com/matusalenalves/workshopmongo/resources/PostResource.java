package com.matusalenalves.workshopmongo.resources;

import com.matusalenalves.workshopmongo.domain.Post;
import com.matusalenalves.workshopmongo.resources.util.URL;
import com.matusalenalves.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(postService.findById(id));
    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
        return ResponseEntity.ok().body(postService.findByTitle(URL.decodeParameter(title)));
    }
}