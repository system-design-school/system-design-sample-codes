package com.example.service.write.controller;

import com.example.pastebin.dao.PasteDao;
import com.example.pastebin.entity.Paste;
import com.example.service.write.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/")
public class WriteController {
    @Autowired
    Config config;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private PasteDao dao;

    @PostMapping("paste")
    public String paste(@RequestBody Paste paste) {
        if (paste.getText().trim() == "") return "{'status':'failed', 'error' : 'text can't be none or empty'}";

        ResponseEntity<String> generatedId = restTemplate.postForEntity("http://127.0.0.1:8079/id", null, String.class);
        String id = generatedId.getBody();
        System.out.println("Generated id: " + id);
        paste.setId(id);
        paste.setCreateTime(new Date());
        dao.save(paste);

        createFileWithContent(id, paste.getText());

        return "{'status':'success', 'data':'" + id + "'}";
    }

    /**
     * The files here are saved to the local machine. If it's a cluster deployment,
     * the files should be saved to object storage.
     */
    public void createFileWithContent(String fileName, String content) {
        File directory = new File(config.getSavePath());
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(config.getSavePath() + File.separator + fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
