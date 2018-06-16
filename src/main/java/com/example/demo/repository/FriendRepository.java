package com.example.demo.repository;

import com.example.demo.model.Friend;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Integer> {
    public List<Friend> findAllByMyself(User mySelf);
    @Transactional
    @Modifying
    @Query(value = "delete from messenger.user_friend where (messenger.user_friend.myself_id=? and messenger.user_friend.friend_id=?) or (messenger.user_friend.friend_id=? and messenger.user_friend.myself_id=?)",nativeQuery = true)
    public void deleteMyFriend(int s,int d,int s1,int d1);
    @Query(value = "select * from messenger.user_friend ORDER by id desc limit 1",nativeQuery = true)
    public Friend lastFriend();
}
