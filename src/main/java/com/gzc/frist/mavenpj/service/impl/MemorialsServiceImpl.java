package com.gzc.frist.mavenpj.service.impl;

import com.gzc.frist.mavenpj.dao.api.MemorialsDao;
import com.gzc.frist.mavenpj.dao.impl.MemorialDaoImpl;
import com.gzc.frist.mavenpj.entity.Memorials;
import com.gzc.frist.mavenpj.service.api.MemorialsService;

import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/08  15:48  周六
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class MemorialsServiceImpl implements MemorialsService {

    private MemorialsDao memorialsDao = new MemorialDaoImpl();

    public List<Memorials> getAllMemorialsDigest(){
        return memorialsDao.selectAllMemorialDigest();
    }


    public Memorials getMemorialsDetailById(String memorialsId) {

        return memorialsDao.selectMemorialDetailById(memorialsId);
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        memorialsDao.updateMemorialsStatusToRead(memorialsId);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        memorialsDao.updateMemorialsFeedBack(memorialsId,feedbackContent);
    }
}
