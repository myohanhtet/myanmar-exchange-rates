package com.myohanhtet.webcrawler.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bank;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

    private Buy buy;

    private Sell sell;

}
