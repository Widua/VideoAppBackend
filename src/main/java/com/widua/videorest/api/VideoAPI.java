package com.widua.videorest.api;

import com.widua.videorest.manager.VideoManager;
import com.widua.videorest.models.ResponseBodyModel;
import com.widua.videorest.models.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class VideoAPI {

    private final VideoManager manager;

    @Autowired
    public VideoAPI(VideoManager manager){
        this.manager = manager;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseBodyModel> findAll(){
        return ResponseEntity.ok(new ResponseBodyModel(
                "Find all videos",
                HttpStatus.ACCEPTED,
                manager.findAll()));
    }

    @GetMapping("/{title}")
    public ResponseEntity<ResponseBodyModel> findByTitle(@PathVariable String title){
        if (manager.findByTitle(title) == null ){
            return ResponseEntity.status(HttpStatus.GONE).body(new ResponseBodyModel(
                    "Video not exist",
                    HttpStatus.GONE
            ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyModel(
                "Video found",
                HttpStatus.OK,
                manager.findByTitle(title)
        ));
    }

    @DeleteMapping("/del/{title}")
    public ResponseEntity<ResponseBodyModel> delModel(@PathVariable String title){
        if (manager.findByTitle(title) == null ){
            return ResponseEntity.status(HttpStatus.GONE).body(new ResponseBodyModel(
                    "Video not exist!",
                    HttpStatus.GONE
            ));
        }
        return ResponseEntity.ok(new ResponseBodyModel(
                "Successfully deleted",
                HttpStatus.ACCEPTED));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseBodyModel> addModel(@RequestBody @Valid VideoModel video, Errors errors){
            if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBodyModel(
                    getErrors(errors),
                    HttpStatus.BAD_REQUEST
            ));
            }
        try{
            manager.addVideo(video);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseBodyModel(
                    "Not Valid Video",
                    HttpStatus.BAD_REQUEST
            ));
        }
        return ResponseEntity.ok().body(new ResponseBodyModel(
                "Successfully add video",
                HttpStatus.OK
        ));
    }





    public String getErrors(Errors errors){
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return errorMessages.toString();
    }



}
