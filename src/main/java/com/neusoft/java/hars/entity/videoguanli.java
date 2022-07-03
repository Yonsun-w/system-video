package com.neusoft.java.hars.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class videoguanli {

    private String user;

    private String video;

    private String name;

    private String isdone;
}
