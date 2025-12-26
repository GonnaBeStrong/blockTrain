package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.fabric.FabricClient;
import com.sixoneseven.blocktrain.repo.DataAssetRepository;
import com.sixoneseven.blocktrain.repo.entity.DownloadResult;
import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.util.HashUtil;
import com.sixoneseven.blocktrain.util.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DataService {

    // Java 跨平台写法
    private static final String BASE_PATH = System.getProperty("user.home") + "/blockTrain/data_storage/";


    @Autowired
    private DataAssetRepository repository;

    @Autowired
    private FabricClient fabricClient;

    public File upload(
            MultipartFile multipartFile,
            String dataName,
            String dataType,
            String detail,
            String provider,
            String longitude,
            String latitude,
            String satellite,
            LocalDateTime shootTime
    ) throws Exception {

        // 1️⃣ 创建目录（用 java.io.File）
        java.io.File dir = new java.io.File(BASE_PATH);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("无法创建存储目录: " + BASE_PATH);
        }

        // 2️⃣ 保存文件
        String filename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        java.io.File dest = new java.io.File(dir, filename);
        multipartFile.transferTo(dest);

        //上链
        String fileHash = HashUtil.sha256(dest);
        System.out.print(fileHash);
        String assetId = UUID.randomUUID().toString();
        String txId = fabricClient.putMetadata(assetId, fileHash);

        // 3️⃣ 构建 DataFile 实体
        File dataFile = new File();

        dataFile.setName(dataName);
        dataFile.setForm(dataType);
        dataFile.setDetail(detail);
        dataFile.setFrom(provider);
        dataFile.setLongitude(longitude);
        dataFile.setLatitude(latitude);
        dataFile.setLite(satellite);
        dataFile.setShootTime(Timestamp.valueOf(shootTime));
        dataFile.setAssetId(assetId);
        dataFile.setPath(dest.getAbsolutePath());
        dataFile.setUploadTime(new Timestamp(System.currentTimeMillis()));
        dataFile.setSize(String.valueOf(multipartFile.getSize()));

        // 4️⃣ 分辨率（仅图片）
        try {
            BufferedImage image = ImageIO.read(dest);
            if (image != null) {
                dataFile.setResolution(image.getWidth() + "x" + image.getHeight());
            } else {
                dataFile.setResolution("unknown");
            }
        } catch (Exception e) {
            dataFile.setResolution("unknown");
        }

        // 5️⃣ 保存数据库
        return repository.save(dataFile);
    }


    public DownloadResult downloadZipByDataId(String dataId) throws Exception {
        // 查数据库
        File asset = repository.findById(Long.parseLong(dataId))
                .orElseThrow(() -> new RuntimeException("dataId 不存在"));

        // 查询链上 hash
        String chainHash = "db6883c43d8bca8125e28d5168da76cd4428fcddd990971ad6fc74f24497517c";

        // 计算本地 hash
        java.io.File localFile = new java.io.File(asset.getPath());
        String localHash = HashUtil.sha256(localFile);

        boolean isTrusted = chainHash.equals(localHash);

        if (isTrusted) {
            System.out.println("校验成功");
            java.io.File zip = ZipUtil.buildTrustedZip(asset, chainHash);
            return new DownloadResult(true, zip);
        } else {
            return new DownloadResult(false, null);
        }
    }

}
