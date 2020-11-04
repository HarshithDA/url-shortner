package com.harshith.urlshortner.enums;

public enum SortOrderEnum {

  ASC("ASC"), DESC("DESC");

  String sortBy;

  private SortOrderEnum(String sortBy) {
    this.sortBy = sortBy;
  }

}
