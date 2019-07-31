package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

import lombok.Data;

/**
 * The type User app.
 *
 * @author Snowson
 * @since 2019 /3/14 13:59
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserApp implements Serializable {
    private static final long serialVersionUID = -6505306714022918656L;

    private String accessMode;
    private String domainOrIp;
    private String port;
    private String accessPath;

    private String id;

    /**
     * 应用名称为汉字,此字段用于存储：汉字为拼音后的值.
     */
    private String appPinYin;

    private String code;

    @JsonIgnore
    private String name;

    private String type;

    private String label;

    private String frequency;

    private String importance;

    private String createTime;

    private String lastModified;

    private String preferRespTime;
}
