package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Member;
import com.example.repository.NijidbRepository;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
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

    public List<Channel> getChannelInfo(List<Member> list) throws IOException {
        YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer(){
        public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName("youtube-cmdline-search-sample").build();

        //APIキー
        String key = "AIzaSyBQ333HQ0n75Ab6FAM53Y8Z-7UwJE45yAQ";
        // チャンネルID
        String chId = "";
        //検索実行
        ChannelListResponse channelsResponse;
        List<Channel> channelsList = new ArrayList<Channel>();

        for(Member member : list){
            chId = member.getChannel_id();
            YouTube.Channels.List channelInfo = youtube.channels().list("id,snippet,statistics");
            channelInfo.setKey(key);
            channelInfo.setId(chId);

            channelsResponse = channelInfo.execute();
            Channel channel = channelsResponse.getItems().get(0);
            channelsList.add(channel);
        }
        for(Channel aaa : channelsList){
            System.out.println(aaa);
        }
        return channelsList;
    }

    public void saveChannelInfo(List<Channel> channelInfoList, List<Member> member){
        int i = 0;
        for(Channel channel : channelInfoList){
            String channelId = channel.getId();
            String channelName = channel.getSnippet().getTitle();
            String subscriver = channel.getStatistics().getSubscriberCount().toString();
            //entityに記録されているid
            String id = member.get(i).getChannel_id();
            if(channelId.equals(id)){
                System.out.println("aaa");
                member.get(i).setChannel_name(channelName);
                member.get(i).setSubscriber(subscriver);
            }
            i++;
        }
    }
}
