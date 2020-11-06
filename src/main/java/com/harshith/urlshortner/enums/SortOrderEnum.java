package com.harshith.urlshortner.enums;

/**
 * Order enum to hold the Ordered by clause options
 *
 */
public enum SortOrderEnum {

  ASC("ASC"), DESC("DESC");

  String sortBy;

  private SortOrderEnum(String sortBy) {
    this.sortBy = sortBy;
  }

}
