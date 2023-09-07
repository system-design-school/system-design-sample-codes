package com.example.service.id_generation.controller;

import com.example.service.id_generation.Config;
import com.example.service.id_generation.dao.IDGenerationDao;
import com.example.service.id_generation.entity.IDGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController("")
public class IDGenerationController {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private BlockingQueue<String> generatedIds = new LinkedBlockingQueue<>();

    @Autowired
    private IDGenerationDao idGenerationDao;

    @Autowired
    private Config config;

    @PostConstruct
    public void init() {
        if (!appendIds()) throw new RuntimeException("Can't update Config when initing");
    }

    @PostMapping("/id")
    public String generate() throws InterruptedException {
        if (generatedIds.size() < config.getMinimumThreshold()) {
            appendIds();
        }
        return generatedIds.take();
    }

    @GetMapping("status")
    public String status() {
        IDGeneration idGeneration = idGenerationDao.findById(1L).orElseThrow(() -> new RuntimeException("ID not found"));
        int lastUsedNum = idGeneration.getLastUsedNum();
        return "Remaining IDs: " + generatedIds.size()
                + "\nLast generate time: " + idGeneration.getUpdateTime()
                + "\nLast used num(unused): " + idGeneration.getLastUsedNum();
    }

    private boolean appendIds() {
        IDGeneration idGeneration = idGenerationDao.findById(1L).orElseThrow(() -> new RuntimeException("ID not found"));
        int lastUsedNum = idGeneration.getLastUsedNum();
        int updated = 0;
        if (lastUsedNum + config.getIncrement() > config.getEnd()) {
            updated = idGenerationDao.reset(1L, lastUsedNum, config.getStart());
            idGeneration = idGenerationDao.findById(1L).orElseThrow(() -> new RuntimeException("ID not found"));
            lastUsedNum = idGeneration.getLastUsedNum();
        }
        updated = idGenerationDao.updateLastUsedNumAndTime(1L, lastUsedNum, config.getIncrement());
        if (updated == 0) return false;
        for (int i = 0; i < config.getIncrement(); i++) {
            generatedIds.add(base62Encode(lastUsedNum + i));
        }
        return true;
    }

    public String base62Encode(int num) {
        if (num == 0) {
            return String.valueOf(CHARS.charAt(0));
        }
        StringBuilder encoding = new StringBuilder();
        while (num > 0) {
            int remainder = num % 62;
            num /= 62;
            encoding.insert(0, CHARS.charAt(remainder));
        }
        return encoding.toString();
    }
}
