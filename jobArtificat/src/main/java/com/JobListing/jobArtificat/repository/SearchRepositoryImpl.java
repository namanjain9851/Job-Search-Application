package com.JobListing.jobArtificat.repository;

import com.JobListing.jobArtificat.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class SearchRepositoryImpl implements  SearchRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;




    @Override
    public List<Post> findByText(String text) {

        MongoDatabase database = client.getDatabase("JobListingDB");
        MongoCollection<Document> collection = database.getCollection("JobListingCollection");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("text",
                                new Document("query", text).append("path", Arrays.asList("desc", "techs")))),
                                new Document("$sort",
                                        new Document("exp", 1L)),
                                        new Document("$limit", 5L)));
        final List<Post> jobPost = new ArrayList<>();
        result.forEach( doc -> jobPost.add(converter.read(Post.class,doc)));

        return jobPost;
    }
}
