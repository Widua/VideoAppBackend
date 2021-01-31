package com.widua.videorest.manager;

import com.widua.videorest.models.VideoModel;
import com.widua.videorest.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VideoManager {

    private final VideoRepository repo;

    @Autowired
    public VideoManager(VideoRepository videoRepository){
        this.repo = videoRepository;
    }

    public Iterable<VideoModel> findAll(){
        return repo.findAll();
    }

    public VideoModel findByTitle(String title){
        return repo.findByTitle(title);
    }

    public void delVideo(String title){
        repo.deleteByTitle(title);
    }

    public void addVideo(VideoModel model){
        repo.save(model);
    }



    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        if (repo.findAll().isEmpty()){
            repo.save(new VideoModel("Inny Czas", LocalDate.of(2017,5,19),3));
            repo.save(new VideoModel("Joker", LocalDate.of(2019,8,31), 5));
        }
    }


}
