package com.JobListing.jobArtificat.repository;

import com.JobListing.jobArtificat.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String> {

}
