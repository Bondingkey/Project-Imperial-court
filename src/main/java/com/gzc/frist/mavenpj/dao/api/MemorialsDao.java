package com.gzc.frist.mavenpj.dao.api;

import com.gzc.frist.mavenpj.entity.Memorials;

import java.util.List;

public interface MemorialsDao {
    List<Memorials> selectAllMemorialDigest();

    Memorials selectMemorialDetailById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
