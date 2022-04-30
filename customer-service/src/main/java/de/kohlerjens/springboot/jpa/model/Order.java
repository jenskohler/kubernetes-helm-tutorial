package de.kohlerjens.springboot.jpa.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Order {
    private String id;
    private String name;
    private Date date;
    private String customerId;
}
