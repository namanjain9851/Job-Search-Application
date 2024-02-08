package com.JobListing.jobArtificat.repository;

import com.JobListing.jobArtificat.model.Post;

import java.util.List;


public interface SearchRepository {

    List<Post> findByText(String text);
}
