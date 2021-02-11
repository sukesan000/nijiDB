package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.entity.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NijidbRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Member> getChannelId(){
        String sql = "select id, channel_id from member_info";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Member> list = new ArrayList<Member>();
        for(Map<String, Object> result : resultList){
            Member member = new Member();
            member.setId((int)result.get("id"));
            member.setChannel_id((String)result.get("channel_id"));
            list.add(member);
        }
        return list;
    }
}