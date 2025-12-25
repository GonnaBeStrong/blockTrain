package com.sixoneseven.blocktrain.repo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {

    public Integer id;

    public Integer userId;

    public Integer dataId;

    public Timestamp buyTime;

}
