package com.ensah.core.dao;



import com.ensah.core.bo.note;

import com.ensah.core.bo.note1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface NoteRepository extends JpaRepository<note1, Long> {

}
