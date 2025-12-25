package com.sixoneseven.blocktrain.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderResponse {

    // 数据id dataId
    private Integer id;

    // 文件路径
    private String path;

    // 大小
    private String size;

    // 分辨率
    private String resolution;

    // 数据描述
    private String detail;

    // 上传时间
    private Timestamp uploadTime;

    // 数据名称
    private String name;

    // 数据提供者
    private String from;

    // 数据类型
    private String form;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;

    // 卫星名字
    private String lite;

    // 拍摄时间
    private Timestamp shootTime;

    // 订单号
    public Integer orderId;

    public Timestamp buyTime;

}
