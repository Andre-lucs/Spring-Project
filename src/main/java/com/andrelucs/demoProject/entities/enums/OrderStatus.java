package com.andrelucs.demoProject.entities.enums;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT",1),
    PAID("PAID",2),
    SHIPPED("SHIPPED",3),
    DELIVERED("DELIVERED",4),
    CANCELED("CANCELED",5);

    private final Integer code;
    private final String name;

    private OrderStatus(String name, Integer code){
        this.code = code;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }
    public String getName(){ return name;}

    public static Optional<OrderStatus> valueByCode(Integer code){
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getCode().equals(code))
                .findFirst();
    }

    public static Optional<OrderStatus> valueByName(String name){
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getName().equals(name.toUpperCase()))
                .findFirst();
    }

}
