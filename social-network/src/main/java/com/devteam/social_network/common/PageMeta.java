package com.devteam.social_network.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PageMeta implements Serializable {
    @JsonProperty("page")
    int page;
    @JsonProperty("size")
    int size;
    @JsonProperty("total_elements")
    long totalElements;
    @JsonProperty("total_pages")
    int totalPages;

    public PageMeta(int page,int size,long totalElements,int totalPages){
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    @JsonProperty("total_elements")
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PageMeta{" +
                "page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                '}';
    }
}
