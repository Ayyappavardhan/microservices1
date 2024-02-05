package com.accenture.repository;

import com.accenture.entity.EmailSaveInDb;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ayyappa on 2/3/2024
 *
 * @author : Ayyappa
 * @date : 2/3/2024
 * @project : microservices1
 */
public interface EmailSendRepository  extends JpaRepository<EmailSaveInDb,Integer> {
}
