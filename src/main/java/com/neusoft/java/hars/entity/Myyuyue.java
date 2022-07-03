package com.neusoft.java.hars.entity;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  lwf
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Myyuyue extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8165248754364345263L;

    private String name;

    private String value;

    private String type;

}
