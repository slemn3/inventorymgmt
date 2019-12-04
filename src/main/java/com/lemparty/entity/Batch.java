package com.lemparty.entity;

import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date;

public class Batch{

    public String _id;
    // Dates
    public Date dateOrigin = new Date(); //Date start/purchase
    public Date dateFerment = new Date(); //Date in Fermentation
    public Date dateDry = new Date(); //Date in Chamber
    public Date dateTarget = new Date(); //Date target for pull


    // Metadata
    public String season; //ie Summer 2019
    public String batchName; //ie Habanero/Finnochiona
    public String description;
    public String pathImage; //path to image

    // Weights
    public float weightSource; //source of purchased meat; grams
    public float weightGrind; //weight after trimming and into grind; grams
    public float weightDry; //fresh weight after stuffing-- full batch weight; grams
    public float weightTarget; //target weight for into inventory; grams
    public float weightCurrent;

    public Batch() {
    }

    public Batch(String name, String season) {
        this._id = name+season;
        this.batchName = name;
        this.season = season;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Date getDateOrigin() {
        return dateOrigin;
    }

    public void setDateOrigin(Date dateOrigin) {
        this.dateOrigin = dateOrigin;
    }

    public Date getDateFerment() {
        return dateFerment;
    }

    public void setDateFerment(Date dateFerment) {
        this.dateFerment = dateFerment;
    }

    public Date getDateDry() {
        return dateDry;
    }

    public void setDateDry(Date dateDry) {
        this.dateDry = dateDry;
    }

    public Date getDateTarget() {
        return dateTarget;
    }

    public void setDateTarget(Date dateTarget) {
        this.dateTarget = dateTarget;
    }

    public float getWeightTarget() {
        return weightTarget;
    }

    public void setWeightTarget(float weightTarget) {
        this.weightTarget = weightTarget;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public float getWeightSource() {
        return weightSource;
    }

    public void setWeightSource(float weightSource) {
        this.weightSource = weightSource;
    }

    public float getWeightGrind() {
        return weightGrind;
    }

    public void setWeightGrind(float weightGrind) {
        this.weightGrind = weightGrind;
    }

    public float getWeightDry() {
        return weightDry;
    }

    public void setWeightDry(float weightDry) {
        this.weightDry = weightDry;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public float getWeightCurrent() {
        return weightCurrent;
    }

    public void setWeightCurrent(float weightCurrent) {
        this.weightCurrent = weightCurrent;
    }

}
