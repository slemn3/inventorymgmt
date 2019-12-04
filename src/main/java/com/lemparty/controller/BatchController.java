package com.lemparty.controller;

import com.lemparty.entity.Batch;
import com.lemparty.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "batch")
public class BatchController {

    @Autowired
    BatchService batchService;

    @GetMapping(value = "/create")
    public ResponseEntity save(@RequestParam String name, @RequestParam String season) {
        boolean success = false;
        try {
            System.out.println("controller create");
            success = batchService.createBatch(name, season);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return new ResponseEntity(success, HttpStatus.OK);
    }


    @GetMapping(value = "/seasons")
    public ResponseEntity seasons() {
        List<String> success = null;
        try {
            System.out.println("controller seasons");
            success = batchService.getSeasons();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody Batch batch) {
        boolean success = false;
        try {
            System.out.println("controller create1");
            success = batchService.createBatch(batch);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Batch batch) {
        boolean success = false;
        try {
            System.out.println("controller create");
            success = batchService.updateBatch(batch);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity retrieve(@RequestParam(required = false) String season) {
        String batchResponse = "";
        try {
            batchResponse = batchService.getBatches(season);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return new ResponseEntity(batchResponse, HttpStatus.OK);
    }
}