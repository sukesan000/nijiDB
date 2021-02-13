package com.example.service;

import java.io.IOException;
import java.util.List;

import com.example.entity.Member;
import com.example.repository.NijidbRepository;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NijidbService {
    @Autowired
    private NijidbRepository nijidbRepository;
    
    public List<Member> getChannelId(){
        return nijidbRepository.getChannelId();
    }
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final com.google.api.client.json.JsonFactory JSON_FACTORY = new JacksonFactory();

    public String getChannelInfo(String chId) throws IOException {
        YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer(){
        public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName("youtube-cmdline-search-sample").build();

        //ベースとなるYutubeAPIのURL
        String baseYoutubeAPIUrl = "https://www.googleapis.com/youtube/v3/";
        //APIキー
        String key = "AIzaSyBQ333HQ0n75Ab6FAM53Y8Z-7UwJE45yAQ";
        // チャンネルID
        String channelId = chId;
        // リクエストURL
        String youtubeAPIUrl = baseYoutubeAPIUrl + "channels?part=snippet,statistics&id=" + channelId + "&key=" + key;

        YouTube.Channels.List channelInfo = youtube.channels().list("id,snippet,statistics");
        channelInfo.setKey(key);
        channelInfo.setId(chId);

        //検索実行
        ChannelListResponse channelsResponse = channelInfo.execute();
        //検索内容
        System.out.println(channelsResponse.toPrettyString());

        return youtubeAPIUrl;
    }
}
