package com.example.riskanalysis.responsemodel;

import java.util.List;

import lombok.Data;

/**
 * response clas whic is used for get list request respons.

 * @param <T> used for defining type of object.
 */
@Data
public class ListResponse<T> {
    /**
     * entity which store total number of objects present in db.
     */
    private long totalCount;
    /**
     * a entity which contains list of objects.
     */
    private List<T> list;
}
