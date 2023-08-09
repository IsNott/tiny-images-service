package com.nott.image.entity;

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
public class SysMovieDet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String parentId;

    private String fileId;


}
