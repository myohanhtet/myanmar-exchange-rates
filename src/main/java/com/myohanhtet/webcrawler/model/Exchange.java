package com.myohanhtet.webcrawler.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exchange{

    private String bank;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

    private Buy buy;

    private Sell sell;

}
