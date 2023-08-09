package com.nott.image.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author nott
 * @since 2023-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMovieInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String pubName;

    private String otherName;

    private String year;

    private String desc;

    private String countryRegion;

    private BigDecimal rateDb;

    private BigDecimal rateImdb;

    private String actors;


}
