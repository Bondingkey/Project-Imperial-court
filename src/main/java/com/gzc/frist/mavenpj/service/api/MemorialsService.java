package com.gzc.frist.mavenpj.service.api;

import com.gzc.frist.mavenpj.entity.Memorials;

import java.util.List;

public interface MemorialsService {
    List<Memorials> getAllMemorialsDigest();

    public Memorials getMemorialsDetailById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
