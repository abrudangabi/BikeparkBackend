package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
