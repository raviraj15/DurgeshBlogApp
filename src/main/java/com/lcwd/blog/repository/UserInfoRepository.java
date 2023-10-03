package com.lcwd.blog.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.blog.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUname(String username);

}
