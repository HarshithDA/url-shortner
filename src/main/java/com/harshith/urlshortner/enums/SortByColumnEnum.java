package com.harshith.urlshortner.enums;

/**
 * Enum with fields that could be used to sort the DB tables with
 *
 */
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
