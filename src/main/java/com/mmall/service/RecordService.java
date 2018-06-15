package com.mmall.service;

import com.mmall.dao.RecordMapper;
import com.mmall.model.Record;
import com.mmall.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by ZHAOKANG on 2018/6/13.
 */
@Service
@Slf4j
public class RecordService {

    @Resource
    private RecordMapper recordMapper;

    public boolean doUpload(int type, String username, String title, String description, MultipartFile file,int action) {
        Record record = new Record();
        record.setType(type);
        record.setUser1(username);
        record.setTitle(title);
        record.setDiscription1(description);
        record.setAction(action);
        String originalFileName = file.getOriginalFilename(); // 原始文件名
        log.info(originalFileName);
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")); // 图片后缀
        String fileName = UUID.randomUUID().toString() + suffix;
        //      String fileName = originalFileName;
        String filePath = Constants.IMG_PATH + fileName;
        File saveFile = new File(filePath);
        try {
            // 将上传的文件保存到服务器文件系统
            file.transferTo(saveFile);
            // 记录服务器文件系统图片名称
            record.setPic(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    // 持久化 user
        return recordMapper.insertSelective(record) > 0;
    }

    public List<Record> selectAllRecord() {
 //       return recordMapper.selectAllRecord();
        return recordMapper.selectAllRecordWhileActionNotNull();
    }

    public void updateRecord(int action, String user2, String description, String pic) {
        Record record = recordMapper.selectByPic(pic);
        record.setAction(action);
        record.setUser2(user2);
        record.setDiscription2(description);
        recordMapper.updateByPrimaryKeySelective(record);
    }

    public List<Record> selectByAction() {
        return recordMapper.selectByAction();
    }
}
