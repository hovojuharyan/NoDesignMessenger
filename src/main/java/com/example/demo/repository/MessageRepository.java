package com.example.demo.repository;

import com.example.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(value = "select  * from message where (source_id = ?  and  destination_id = ?) or (destination_id = ? and source_id = ?)",nativeQuery = true)
    public List<Message> ourMessages(int sourceId,int destId,int destId1,int sourceId1);
}
