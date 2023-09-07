package com.example.service.read.controller;

import com.alibaba.fastjson.JSON;
import com.example.pastebin.dao.PasteDao;
import com.example.pastebin.entity.Paste;
import com.example.service.read.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/")
public class ReadController {
    @Autowired
    private Config config;

    @Autowired
    private PasteDao dao;

    @GetMapping("/{id}")
    public String viewText(@PathVariable String id) {
        if (id == null || id.trim() == "") return "{'status': 'failed', 'error': 'Param was not found!'}";
        try {
            Optional<Paste> pasteOpt = dao.findById(id);
            if (pasteOpt.isEmpty()) return "{'status': 'failed', 'error': 'Paste was not found!'}";
            String content = readFile(id);
            Paste paste = pasteOpt.get();
            paste.setText(content);
            return "{'status': 'success', 'data': '" + JSON.toJSONString(paste) + "'}";
        } catch (FileNotFoundException e) {
            return "{'status': 'failed', 'error': 'Paste was not found!'}";
        }
    }

    public String readFile(String fileName) throws FileNotFoundException {
        File file = new File(config.getSavePath() + File.separator + fileName);
        if (!file.exists()) throw new FileNotFoundException();
        StringBuilder fileContent = new StringBuilder();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContent.append(line).append("\n");
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return fileContent.toString();
    }
}
