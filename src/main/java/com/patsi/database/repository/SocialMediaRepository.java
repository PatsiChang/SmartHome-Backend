package com.patsi.database.repository;

import com.patsi.bean.SocialMediaUser;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SocialMediaRepository extends Repository<SocialMediaUser, UUID> {
    //Post
    void save(SocialMediaUser user);

    //Get
    List<SocialMediaUser> findAll();
    Optional<SocialMediaUser> findByUid(UUID uid);
    Optional<SocialMediaUser> findByUserName(String userName);

    //Delete
    void deleteByUid(UUID uid);
}
