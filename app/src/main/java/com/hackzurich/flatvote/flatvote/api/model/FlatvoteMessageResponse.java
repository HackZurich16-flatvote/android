
package com.hackzurich.flatvote.flatvote.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FlatvoteMessageResponse {

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("itemsPerPage")
    @Expose
    private Integer itemsPerPage;
    @SerializedName("hasNextPage")
    @Expose
    private Boolean hasNextPage;
    @SerializedName("hasPreviousPage")
    @Expose
    private Boolean hasPreviousPage;

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The resultCount
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * 
     * @param resultCount
     *     The resultCount
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * 
     * @return
     *     The start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 
     * @return
     *     The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 
     * @param pageCount
     *     The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @return
     *     The itemsPerPage
     */
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * 
     * @param itemsPerPage
     *     The itemsPerPage
     */
    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * 
     * @return
     *     The hasNextPage
     */
    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    /**
     * 
     * @param hasNextPage
     *     The hasNextPage
     */
    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    /**
     * 
     * @return
     *     The hasPreviousPage
     */
    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * 
     * @param hasPreviousPage
     *     The hasPreviousPage
     */
    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

}
