package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.entity.Member;
import com.example.service.NijidbService;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.Channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NijidbController{
  private final JdbcTemplate jdbcTemplate;
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final com.google.api.client.json.JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final String API_KEY = "AIzaSyBQ333HQ0n75Ab6FAM53Y8Z-7UwJE45yAQ";
    
  @Autowired
  public NijidbController(JdbcTemplate jdbcTemplate){
      this.jdbcTemplate = jdbcTemplate;
  }
  @Autowired
  private NijidbService njService;

  @GetMapping("/test")
  public String main(Model model){
    String sql = "SELECT id, name, channel_name, subscriber FROM nijiDB WHERE id = 1";
    Map<String, Object> map = jdbcTemplate.queryForMap(sql);
    model.addAttribute("name", map.get("name"));
    model.addAttribute("channel_name", map.get("channel_name"));
    model.addAttribute("subscriber", map.get("subscriber"));
    return "test";
  }

  @GetMapping("/nijiDB")
  public String nijiDB() throws IOException{
    // YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer(){
    //   public void initialize(HttpRequest request) throws IOException{}
    // }).setApplicationName("youtube-cmdline-serch-sample").build();
    List<Member> list = new ArrayList<Member>();
    //チャンネルIDをリストに格納
    list = njService.getChannelId();
    //チャンネルIDから検索
    List<Channel> channelInfo = njService.getChannelInfo(list);
    System.out.println(channelInfo);
    
    return "nijiDB";
  }
}