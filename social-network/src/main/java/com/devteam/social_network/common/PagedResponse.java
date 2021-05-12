package com.devteam.social_network.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;

public class PagedResponse<T> implements Serializable {
    @JsonProperty("data")
    Collection<T> data;
    @JsonProperty("page_meta")
    PageMeta pageMeta;

    public PagedResponse(Collection<T> data,PageMeta pageMeta){
        this.data = data;
        this.pageMeta = pageMeta;
    }

    public static PagedResponse.PagedResponseBuilder builder(){
        return new PagedResponse.PagedResponseBuilder();
    }

    @Override
    public String toString() {
        return "PagedResponse{" +
                "data=" + this.getData() +
                ", pageMeta=" + this.getPageMeta() +
                '}';
    }

    public Collection<T> getData(){
        return this.data;
    }

    public PageMeta getPageMeta(){
        return this.pageMeta;
    }



    public static class PagedResponseBuilder<T>{
        private Page<T> page;

        public PagedResponseBuilder(){}

        public PagedResponse.PagedResponseBuilder page(final Page<T> page){
            this.page = page;
            return this;
        }

        public PagedResponse<T> build(){
            return new PagedResponse<T>(this.page.getContent(),new PageMeta(this.page.getNumber(),this.page.getSize(),this.page.getTotalElements(),this.page.getTotalPages()));
        }
        public String toString(){
            return "PagedResponse.PagedResponseBuilder(page="+this.page+")";
        }
    }
}
