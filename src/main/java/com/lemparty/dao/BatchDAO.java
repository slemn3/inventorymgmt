package com.lemparty.dao;

import com.google.gson.Gson;
import com.lemparty.entity.Query;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.lemparty.entity.Batch;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class BatchDAO {
    MongoCollection<Batch> collection;

    public BatchDAO(String url){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register("com.lemparty.entity.Batch").automatic(true).build()));

        //"mongodb://localhost:27017"
        MongoClient mongoClient = MongoClients.create(url);
        MongoDatabase database = mongoClient.getDatabase("lemparty");
        collection = database.getCollection("batch", Batch.class).withCodecRegistry(pojoCodecRegistry);
    }

    public boolean updateBatch(Batch batch) {
        Gson gson = new Gson();
        String jsString = gson.toJson(new Query(batch.getBatchName(), batch.getSeason()));
        BsonDocument bson = BsonDocument.parse(jsString);

        collection.findOneAndReplace(bson, batch);
        return true;


    }

    public boolean createBatch(Batch batch) {

        collection.insertOne(batch);
        return true;


    }

    public List<Batch> getBatchesBySeason(String season){
        FindIterable<Batch> obtainedBatches = collection.find(eq("season", season));
        List<Batch> listBatches = obtainedBatches.into(new ArrayList<Batch>());

        System.out.println("Obtained Batch is not Null: "+String.valueOf(obtainedBatches != null));

        return listBatches;
    }

    public List<String> getSeasons(){
        DistinctIterable<String> seasons = collection.distinct("season", String.class);
        List<String> listSeasons = seasons.into(new ArrayList<String>());

        System.out.println("Obtained Batch is not Null: "+String.valueOf(listSeasons != null));

        return listSeasons;
    }

    public List<Batch> getBatchByBatchName(String batchName){
        FindIterable<Batch> obtainedBatches = collection.find(eq("batchName", batchName));
        List<Batch> listBatches = obtainedBatches.into(new ArrayList<Batch>());
        System.out.println("Obtained Batch is not Null: "+String.valueOf(obtainedBatches != null));

        return listBatches;
    }

    public List<Batch> getBatches(){
        FindIterable<Batch> obtainedBatches = collection.find();
        List<Batch> listBatches = obtainedBatches.into(new ArrayList<Batch>());
        System.out.println("Obtained Batch is not Null: "+String.valueOf(obtainedBatches != null));

        return listBatches;
    }

}
