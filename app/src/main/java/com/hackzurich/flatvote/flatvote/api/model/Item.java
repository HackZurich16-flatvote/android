
package com.hackzurich.flatvote.flatvote.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("sellingPrice")
    @Expose
    private Integer sellingPrice;
    @SerializedName("pictures")
    @Expose
    private List<String> pictures = new ArrayList<String>();
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("advertisementId")
    @Expose
    private Integer advertisementId;

    @SerializedName("numberRooms")
    @Expose
    private Float numberRooms;

    @SerializedName("travelTimes")
    @Expose
    private ArrayList<Float> travelTimes;

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }
    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumberRooms(Float numberRooms) {
        this.numberRooms = numberRooms;
    }

    public Float getNumberRooms() {
        return numberRooms;
    }

    /**
     * 
     * @return
     *     The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *     The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The sellingPrice
     */
    public Integer getSellingPrice() {
        return sellingPrice;
    }

    /**
     * 
     * @param sellingPrice
     *     The sellingPrice
     */
    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * 
     * @return
     *     The pictures
     */
    public List<String> getPictures() {
        return pictures;
    }

    /**
     * 
     * @param pictures
     *     The pictures
     */
    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The advertisementId
     */
    public Integer getAdvertisementId() {
        return advertisementId;
    }

    /**
     * 
     * @param advertisementId
     *     The advertisementId
     */
    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    /**
     * 
     * @return
     *     The travelTimes
     */
    public ArrayList<Float> getTravelTimes() {
        return travelTimes;
    }

    /**
     *
     * @param travelTimes
     *     The travelTimes
     */
    public void setTravelTimes(ArrayList<Float> travelTimes) {
        this.travelTimes = travelTimes;
    }

}
