package com.widua.videorest.repository;

import com.widua.videorest.models.VideoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<VideoModel,String> {

    void deleteByTitle(String title);
    VideoModel findByTitle(String title);

}
