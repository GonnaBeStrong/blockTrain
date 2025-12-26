package com.sixoneseven.blocktrain.repo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "`file`") // 避免与 SQL 关键字冲突
public class File {

    // 数据库主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 链上数据
    private String assetId;   // 唯一标识符

    // 文件路径
    @Column(name = "path", length = 255)
    private String path;

    // 文件大小
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
    @Column(name = "`from`")
    private String from;

    // 数据类型
    private String form;

    // 经纬度
    private String longitude;
    private String latitude;

    // 卫星名称
    private String lite;

    // 拍摄时间
    private Timestamp shootTime;

}
