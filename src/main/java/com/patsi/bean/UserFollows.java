package com.patsi.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@Table(name="user-follows")
@IdClass(UserFollows.UserFollowsId.class)
public class UserFollows {
    @Id
    @MapsId("uid")
    @ManyToOne
    private SocialMediaUser uid;

    @Id
    @MapsId("uid")
    @ManyToOne
    private SocialMediaUser followingUid;
    private Timestamp followDate;

    @Data
    public class UserFollowsId implements Serializable {
        @Column(name = "uid")
        private SocialMediaUser uid;
        @Column(name = "followingUid")
        private SocialMediaUser followingUid;
    }


}
