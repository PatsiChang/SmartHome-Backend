package com.patsi.database.repository;

import com.patsi.bean.UserFollows;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserFollowsRepository extends Repository<UserFollows, UUID> {
    //Post
    UserFollows save(UserFollows user);

    //Get
    List<UserFollows> findAll();
    Optional<UserFollows> findByUid(UserFollows.UserFollowsId uid);

    //Delete
    void deleteByUid(UserFollows.UserFollowsId uid);
}
