package com.JobListing.jobArtificat.controller;

import com.JobListing.jobArtificat.repository.PostRepository;
import com.JobListing.jobArtificat.model.Post;
import com.JobListing.jobArtificat.repository.SearchRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository repo;
    @Autowired
    SearchRepository searchRepo;

    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {

    }
    @GetMapping("/getPosts")
    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    @GetMapping("/getPosts/{text}")
    public List<Post> search(@PathVariable String text) {
        return searchRepo.findByText(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody  Post post) {
       return  repo.save(post);
    }
}
