package com.example.demo.repository;

import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<FriendRequest,Integer> {
    public List<FriendRequest> findAllBySource(User source);
    public List<FriendRequest> findAllByDestination(User destination);
    @Transactional
    @Modifying
    @Query(value= "delete from user_request where (messenger.user_request.source_id=? and messenger.user_request.destination_id=?) or (messenger.user_request.destination_id=? and messenger.user_request.source_id=?)",nativeQuery = true)
    public void deleteOurRequest(int source,int dest,int source1,int dest1);
}
