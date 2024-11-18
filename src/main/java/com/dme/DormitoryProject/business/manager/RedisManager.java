package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IRedisService;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class RedisManager implements IRedisService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setData(Long id) {
        String key = "code-"+id;
        Random random = new Random();
        Object value = 100000 + random.nextInt(900000);
        redisTemplate.opsForValue().set(key, value,180,TimeUnit.SECONDS);
    }

    @Override
    public long getData(Long id) {
        String key = "code-"+id;
        return (Integer) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void waitStudentData(StudentDTO studentDTO){
        String key = "studentData";
        redisTemplate.opsForValue().set(key,studentDTO,180,TimeUnit.SECONDS);
    }

    @Override
    public StudentDTO getStudentData(){
        String key = "studentData";
        return (StudentDTO) redisTemplate.opsForValue().get(key);
    }
    @Override
    public void waitData(Object data){
        String key;
    }
}
