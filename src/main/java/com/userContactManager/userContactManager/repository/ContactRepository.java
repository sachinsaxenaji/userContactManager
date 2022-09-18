package com.userContactManager.userContactManager.repository;

import com.userContactManager.userContactManager.entity.Contact;
import com.userContactManager.userContactManager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {



    @Query("from Contact as c where c.user.id =:uid")
    public Page<Contact> getContactByUserId(@Param("uid") int uid, Pageable pageable);

    //search
    public List<Contact> findByNameContainingAndUser(String name, User user);
}
