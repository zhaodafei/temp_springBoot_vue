package com.example.fei.service.impl;

import com.example.fei.domain.File;
import com.example.fei.service.FileService;
import org.springframework.stereotype.Service;

@Service("FileService")
public class FileServiceImpl implements FileService {
    @Override
    public int insertFile(File fileForm) {
        return 0;
    }
}
