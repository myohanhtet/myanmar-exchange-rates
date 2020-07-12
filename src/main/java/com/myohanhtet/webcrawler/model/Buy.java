package com.myohanhtet.webcrawler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Buy  {

//        private static final long serialVersionUID = 1L;

        private String USD;
        private String EUR;
        private String SGD;
        private String JPY;
        private String MYR;
        private String THB;
}
