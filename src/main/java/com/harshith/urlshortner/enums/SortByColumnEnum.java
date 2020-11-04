package com.harshith.urlshortner.enums;

public enum SortByColumnEnum {

  CREATED_DATE("createdDate");

  String tableColumnName;


  private SortByColumnEnum(String tableColumnName) {
    this.tableColumnName = tableColumnName;
  }

  public String getTableColumn() {
    return tableColumnName;
  }

}
