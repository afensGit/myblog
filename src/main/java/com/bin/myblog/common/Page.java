package com.bin.myblog.common;

import java.util.List;

public class Page<T> {

    private Integer currentPage;//当前页面
    private Integer totalCount;//总数目
    private Integer PageCount;//总页数
    private Integer currentCount;//当前页总数目
    private List<T> beans;//bean对象集合

    public List getBeans() {
        return beans;
    }

    public void setBeans(List beans) {
        this.beans = beans;
    }


    public Page(Integer currentPage, Integer currentCount) {
        if (currentPage<=0){
            this.currentPage=1;
        }else {
            this.currentPage = currentPage;
        }
        if (currentCount<=0){
            this.currentCount=10;
        }else {
            this.currentCount=currentCount;
        }
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        /**
         * 计算出总页面数
         */
        this.PageCount= (totalCount%currentCount)==0?(totalCount/currentCount):(totalCount/currentCount+1);
    }

    public Integer getPageCount() {
        return PageCount;
    }

    private void setPageCount(Integer totalPage) {
        this.PageCount = totalPage;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public Integer getStartIndex(){
        /**
         * 计算出当前页面开始查询的记录索引位置
         */
        return (currentPage-1)*currentCount;
    }


    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", PageCount=" + PageCount +
                ", currentCount=" + currentCount +
                '}';
    }
}
