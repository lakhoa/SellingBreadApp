package com.example.SellingBreadApp.dto;

import java.util.List;
public class PageResponseDTO <T> {

    private Integer page;
    private Integer pageSize;

    private Integer totalPage;
    private T data;

    public PageResponseDTO(Integer page, Integer pageSize, Integer totalPage, T data) {
      this.page = page;
      this.pageSize = pageSize;
      this.data = data;
      this.totalPage = totalPage;
    }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
