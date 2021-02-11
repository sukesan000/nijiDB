package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.entity.Member;
import com.example.repository.NijidbRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NijidbService {
    @Autowired
    private NijidbRepository nijidbRepository;
    
    public List<Member> getChannelId(){
        return nijidbRepository.getChannelId();
    }
}
