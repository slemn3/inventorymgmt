package com.lemparty.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lemparty.dao.BatchDAO;
import com.lemparty.entity.Batch;
import com.mongodb.client.FindIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

import java.text.DateFormat;

public class BatchService {

    /**
     * The service from initializing and managing
     * batches.
     * **/

    @Autowired
    private BatchDAO batchDAO;

    public boolean createBatch(String batchName, String season){
        System.out.println("createBatch");
        Batch newBatch = new Batch(batchName, season);
        boolean added = batchDAO.createBatch(newBatch);
        return added;
    }

    public boolean updateBatch(Batch batch){
        return batchDAO.updateBatch(batch);
    }

    public boolean createBatch(Batch batch){
        return batchDAO.createBatch(batch);
    }



    public String getBatches(String season){
        Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
        if(season != null && !season.equals("")){
            return gson.toJson(batchDAO.getBatchesBySeason(season));
        } else {
            return gson.toJson(batchDAO.getBatches());
        }
    }

    public String getBatchByName(String name){
        Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();

        if(name != null && !name.equals("")){
            return gson.toJson(batchDAO.getBatchByBatchName(name));
        }

        return null;
    }


}
