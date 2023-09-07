package com.example.pastebin.dao;

import com.example.pastebin.entity.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PasteDao extends JpaRepository<Paste, String> {
}
