package com.mchaw.tauruspay.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/31 0031 00:30
 */
public class MovieSubject {

    /**
     * code : 200
     * msg : 成功!
     * data : [{"type":"10","text":"会不会有超级女粉会买","user_id":"22980432","name":"余生请温柔","screen_name":"余生请温柔","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6ae0c3f3_mini.jpg","created_at":"2019-06-03 19:20:01","create_time":null,"passtime":"2019-06-03 19:20:01","love":"94","hate":"6","comment":"25","repost":"3","bookmark":"1","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"6","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg","width":"690","height":"1804","tag":"","t":1559560801,"ding":"94","favourite":"1","top_cmt":null,"themes":null},{"type":"41","text":"理发简直就是一种享受\u2026\u2026太萌了！","user_id":"20983996","name":"搞笑视频回一笑","screen_name":"搞笑视频回一笑","profile_image":"http://wimg.spriteapp.cn/profile/large/2017/09/21/59c2b8b0ccb19_mini.jpg","created_at":"2018-06-18 18:33:01","create_time":null,"passtime":"2018-06-18 18:33:01","love":"482","hate":"44","comment":"115","repost":"41","bookmark":"22","bimageuri":"http://wimg.spriteapp.cn/picture/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"124","theme_name":"萌宠","theme_type":"1","videouri":"http://wvideo.spriteapp.cn/video/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.mp4","videotime":42,"original_pid":"0","cache_version":2,"playcount":"39027","playfcount":"3176","cai":"44","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/0616/91c9fe36-7137-11e8-898e-1866daeb0df1_wpd.jpg","width":"360","height":"640","tag":"","t":1529317981,"ding":"482","favourite":"22","top_cmt":null,"themes":null},{"type":"41","text":"变成了自己小时候讨厌的角色","user_id":"17274977","name":"我是笔记本","screen_name":"我是笔记本","profile_image":"http://wimg.spriteapp.cn/profile/large/2016/08/30/57c5913446a87_mini.jpg","created_at":"2018-06-02 22:46:02","create_time":null,"passtime":"2018-06-02 22:46:02","love":"552","hate":"21","comment":"117","repost":"12","bookmark":"29","bimageuri":"http://wimg.spriteapp.cn/picture/2018/0531/5b0fafe8011ba_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"55163","theme_name":"主版块","theme_type":"1","videouri":"http://wvideo.spriteapp.cn/video/2018/0531/5b0fafe8011ba_wpd.mp4","videotime":20,"original_pid":"0","cache_version":2,"playcount":"39166","playfcount":"9697","cai":"21","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/0531/5b0fafe8011ba_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/0531/5b0fafe8011ba_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/0531/5b0fafe8011ba_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/0531/5b0fafe8011ba_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/0531/5b0fafe8011ba_wpd.jpg","width":"360","height":"640","tag":"","t":1527950762,"ding":"552","favourite":"29","top_cmt":null,"themes":null},{"type":"10","text":"哎，为什么总有人几杯马尿下肚，就不知道自己是老几了呢","user_id":"19889419","name":"天天趣图","screen_name":"天天趣图","profile_image":"http://wimg.spriteapp.cn/profile/large/2017/01/16/587c6b2cee0fe_mini.jpg","created_at":"2017-09-19 09:02:02","create_time":null,"passtime":"2017-09-19 09:02:02","love":"1807","hate":"182","comment":"421","repost":"149","bookmark":"103","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"473","theme_name":"社会新闻","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"182","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2017/09/18/59bf9c80bffee.gif","image2":"http://wimg.spriteapp.cn/ugc/2017/09/18/59bf9c80bffee.gif","is_gif":true,"image0":"http://wimg.spriteapp.cn/ugc/2017/09/18/59bf9c80bffee.gif","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2017/09/18/59bf9c80bffee.gif","width":"330","height":"256","tag":"","t":1505782922,"ding":"1807","favourite":"103","top_cmt":null,"themes":null},{"type":"41","text":"社会我柱子哥被打了！","user_id":"22963328","name":"视频君","screen_name":"视频君","profile_image":"http://wimg.spriteapp.cn/profile/20181020165458962088.jpg","created_at":"2018-11-08 18:20:02","create_time":null,"passtime":"2018-11-08 18:20:02","love":"440","hate":"25","comment":"39","repost":"16","bookmark":"65","bimageuri":"http://wimg.spriteapp.cn/picture/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"0","theme_name":"","theme_type":"0","videouri":"http://tvideo.spriteapp.cn/video/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.mp4","videotime":122,"original_pid":"0","cache_version":2,"playcount":"24159","playfcount":"5280","cai":"25","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/1107/d6501b6ce25211e89dae842b2b4c75ab_wpd.jpg","width":"632","height":"360","tag":"","t":1541672402,"ding":"440","favourite":"65","top_cmt":null,"themes":null},{"type":"10","text":"推荐11部主角虐杀反派的精彩 \u200b\u200b\u200b","user_id":"17588230","name":"神马情况","screen_name":"神马情况","profile_image":"http://wimg.spriteapp.cn/profile/large/2018/03/21/5ab21157ebeaa_mini.jpg","created_at":"2019-03-27 10:58:02","create_time":null,"passtime":"2019-03-27 10:58:02","love":"205","hate":"7","comment":"72","repost":"43","bookmark":"258","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"407","theme_name":"影视分享","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"7","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2019/03/26/5c998ac2935b1_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/03/26/5c998ac2935b1_1.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2019/03/26/5c998ac2935b1_1.jpg","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2019/03/26/5c998ac2935b1_1.jpg","width":"440","height":"8798","tag":"","t":1553655482,"ding":"205","favourite":"258","top_cmt":null,"themes":null},{"type":"41","text":"石榴熟了医院系列： 我亲爱的朋友，你怎么长成了一棵多肉植物","user_id":"17904743","name":"石榴熟了","screen_name":"石榴熟了","profile_image":"http://wimg.spriteapp.cn/profile/20180903115500567894.jpg","created_at":"2018-11-12 22:46:02","create_time":null,"passtime":"2018-11-12 22:46:02","love":"374","hate":"29","comment":"23","repost":"2","bookmark":"8","bimageuri":"http://wimg.spriteapp.cn/picture/2018/1112/5be9016c197f0_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","videouri":"http://wvideo.spriteapp.cn/video/2018/1112/5be9016c197f0_wpd.mp4","videotime":208,"original_pid":"0","cache_version":2,"playcount":"15681","playfcount":"733","cai":"29","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/1112/5be9016c197f0_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/1112/5be9016c197f0_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/1112/5be9016c197f0_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/1112/5be9016c197f0_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/1112/5be9016c197f0_wpd.jpg","width":"1066","height":"600","tag":"","t":1542033962,"ding":"374","favourite":"8","top_cmt":null,"themes":null},{"type":"41","text":"选哪个，在线等！急！","user_id":"21530250","name":"那小子真拽","screen_name":"那小子真拽","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/05/16/5cdd3c501dbc8_mini.jpg","created_at":"2019-05-21 13:56:01","create_time":null,"passtime":"2019-05-21 13:56:01","love":"138","hate":"13","comment":"46","repost":"2","bookmark":"2","bimageuri":"http://wimg.spriteapp.cn/picture/2019/0520/5ce27dd3d9e94_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"55163","theme_name":"主版块","theme_type":"1","videouri":"http://uvideo.spriteapp.cn/video/2019/0520/5ce27dd3d9e94_wpd.mp4","videotime":11,"original_pid":"0","cache_version":2,"playcount":"1757","playfcount":"275","cai":"13","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2019/0520/5ce27dd3d9e94_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0520/5ce27dd3d9e94_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2019/0520/5ce27dd3d9e94_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0520/5ce27dd3d9e94_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0520/5ce27dd3d9e94_wpd.jpg","width":"320","height":"568","tag":"","t":1558418161,"ding":"138","favourite":"2","top_cmt":null,"themes":null},{"type":"41","text":"\n\n日本女子高中生的日常\n\n","user_id":"22980208","name":"来路生云烟","screen_name":"来路生云烟","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6d52d882_mini.jpg","created_at":"2019-06-03 14:57:01","create_time":null,"passtime":"2019-06-03 14:57:01","love":"190","hate":"9","comment":"75","repost":"5","bookmark":"25","bimageuri":"http://wimg.spriteapp.cn/picture/2019/0530/5ceffc40231ee_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","videouri":"http://wvideo.spriteapp.cn/video/2019/0530/5ceffc40231ee_wpd.mp4","videotime":158,"original_pid":"0","cache_version":2,"playcount":"6684","playfcount":"92","cai":"9","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2019/0530/5ceffc40231ee_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0530/5ceffc40231ee_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2019/0530/5ceffc40231ee_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0530/5ceffc40231ee_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0530/5ceffc40231ee_wpd.jpg","width":"600","height":"1066","tag":"","t":1559545021,"ding":"190","favourite":"25","top_cmt":null,"themes":null},{"type":"10","text":"来嘛，我给你看一哈我的LED矩阵大灯，巴适得板","user_id":"15704485","name":"成都李扯火","screen_name":"成都李扯火","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/05/16/5cdd6a5f66dba_mini.jpg","created_at":"2019-07-21 19:04:02","create_time":null,"passtime":"2019-07-21 19:04:02","love":"184","hate":"11","comment":"21","repost":"1","bookmark":"7","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"55163","theme_name":"主版块","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"11","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2019/07/19/5d3179dd0145f.gif","image2":"http://wimg.spriteapp.cn/ugc/2019/07/19/5d3179dd0145f.gif","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2019/07/19/5d3179dd0145f.gif","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2019/07/19/5d3179dd0145f.gif","width":"254","height":"191","tag":"","t":1563707042,"ding":"184","favourite":"7","top_cmt":null,"themes":null},{"type":"41","text":"花式比心锁定你","user_id":"22963340","name":"美女君","screen_name":"美女君","profile_image":"http://wimg.spriteapp.cn/profile/20181020165756209696.jpeg","created_at":"2019-02-10 18:59:02","create_time":null,"passtime":"2019-02-10 18:59:02","love":"338","hate":"92","comment":"154","repost":"9","bookmark":"96","bimageuri":"http://wimg.spriteapp.cn/picture/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"0","theme_name":"","theme_type":"0","videouri":"http://wvideo.spriteapp.cn/video/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.mp4","videotime":15,"original_pid":"0","cache_version":2,"playcount":"15166","playfcount":"2569","cai":"92","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0210/e9652f942cee11e99978842b2b4c75ab_wpd.jpg","width":"600","height":"1066","tag":"","t":1549796342,"ding":"338","favourite":"96","top_cmt":null,"themes":null},{"type":"41","text":"今天妈妈不在家系列最新作！父子俩接到广告了！！","user_id":"22870404","name":"影视大杂烩","screen_name":"影视大杂烩","profile_image":"http://wimg.spriteapp.cn/profile/20180902094723637828.jpg","created_at":"2019-01-22 14:36:02","create_time":null,"passtime":"2019-01-22 14:36:02","love":"217","hate":"10","comment":"12","repost":"5","bookmark":"17","bimageuri":"http://wimg.spriteapp.cn/picture/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"0","theme_name":"","theme_type":"0","videouri":"http://wvideo.spriteapp.cn/video/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.mp4","videotime":73,"original_pid":"0","cache_version":2,"playcount":"5666","playfcount":"576","cai":"10","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0122/e54a5f341df211e98bfa842b2b4c75ab_wpd.jpg","width":"1066","height":"600","tag":"","t":1548138962,"ding":"217","favourite":"17","top_cmt":null,"themes":null},{"type":"41","text":"口臭很尴尬，中医告诉你这种方法只需5分钟轻松告别口臭简单有效\u2026\u2026","user_id":"21393596","name":"搞笑-君","screen_name":"搞笑-君","profile_image":"http://wimg.spriteapp.cn/profile/large/2018/01/13/5a5a0f74c0e14_mini.jpg","created_at":"2018-08-14 11:13:01","create_time":null,"passtime":"2018-08-14 11:13:01","love":"374","hate":"17","comment":"46","repost":"101","bookmark":"341","bimageuri":"http://wimg.spriteapp.cn/picture/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"8466","theme_name":"知识分享","theme_type":"1","videouri":"http://wvideo.spriteapp.cn/video/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.mp4","videotime":94,"original_pid":"0","cache_version":2,"playcount":"7132","playfcount":"1117","cai":"17","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/0813/4dc88d44-9ef3-11e8-a53c-0026b938a8ac_wpd.jpg","width":"606","height":"276","tag":"","t":1534216381,"ding":"374","favourite":"341","top_cmt":null,"themes":null},{"type":"10","text":"用美颜给妈妈拍照","user_id":"23127187","name":"黄鑫雯","screen_name":"黄鑫雯","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d6d6490253_mini.jpg","created_at":"2019-08-18 12:04:01","create_time":null,"passtime":"2019-08-18 12:04:01","love":"91","hate":"10","comment":"6","repost":"1","bookmark":"0","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"10","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2019/08/17/5d5772bb9af69_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/08/17/5d5772bb9af69_1.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2019/08/17/5d5772bb9af69_1.jpg","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2019/08/17/5d5772bb9af69_1.jpg","width":"360","height":"1689","tag":"","t":1566101041,"ding":"91","favourite":"0","top_cmt":null,"themes":null},{"type":"10","text":"当男朋友不在身边的时候，如何给他正确的选择衣服~~","user_id":"16346788","name":"搞笑小村","screen_name":"搞笑小村","profile_image":"http://wimg.spriteapp.cn/profile/large/2018/02/07/5a7a903fb7bcc_mini.jpg","created_at":"2018-04-14 23:57:54","create_time":null,"passtime":"2018-04-14 23:57:54","love":"612","hate":"52","comment":"482","repost":"84","bookmark":"45","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"0","theme_name":"","theme_type":"0","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"52","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2018/03/31/5abf4e91cec27.gif","image2":"http://wimg.spriteapp.cn/ugc/2018/03/31/5abf4e91cec27.gif","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2018/03/31/5abf4e91cec27.gif","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2018/03/31/5abf4e91cec27.gif","width":"262","height":"296","tag":"","t":1523721474,"ding":"612","favourite":"45","top_cmt":null,"themes":null},{"type":"10","text":"为什么我家的橘子总是很酸？","user_id":"19531820","name":"臭宝蛋蛋","screen_name":"臭宝蛋蛋","profile_image":"http://tva4.sinaimg.cn/crop.0.0.996.996.50/e7f9e94djw8f6ursgyso3j20ro0rp0wy.jpg","created_at":"2018-03-03 14:58:01","create_time":null,"passtime":"2018-03-03 14:58:01","love":"566","hate":"68","comment":"115","repost":"10","bookmark":"20","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"57911","theme_name":"就爱看漫画","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"68","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2018/03/03/5a9a13467f3de_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2018/03/03/5a9a13467f3de_1.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2018/03/03/5a9a13467f3de_1.jpg","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2018/03/03/5a9a13467f3de_1.jpg","width":"500","height":"7075","tag":"","t":1520060281,"ding":"566","favourite":"20","top_cmt":null,"themes":null},{"type":"41","text":"逗逼派来的吧？😂😂😂","user_id":"22637945","name":"小妖精kk","screen_name":"小妖精kk","profile_image":"http://wimg.spriteapp.cn/profile/large/2018/09/04/5b8e056042bd8_mini.jpg","created_at":"2018-11-08 21:13:02","create_time":null,"passtime":"2018-11-08 21:13:02","love":"220","hate":"7","comment":"28","repost":"8","bookmark":"6","bimageuri":"http://wimg.spriteapp.cn/picture/2018/1108/5be3a78131338__b.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"56781","theme_name":"情感社区","theme_type":"1","videouri":"http://tvideo.spriteapp.cn/video/2018/1108/5be3a78144957_wpd.mp4","videotime":152,"original_pid":"0","cache_version":2,"playcount":"7477","playfcount":"1532","cai":"7","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/1108/5be3a78131338__b.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/1108/5be3a78131338__b.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/1108/5be3a78131338__b.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/1108/5be3a78131338__b.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/1108/5be3a78131338__b.jpg","width":"640","height":"368","tag":"","t":1541682782,"ding":"220","favourite":"6","top_cmt":null,"themes":null},{"type":"41","text":"夏天快到了，几个动作改善你的虎背熊腰\u2026\u2026","user_id":"18895364","name":"日日曰日","screen_name":"日日曰日","profile_image":"http://wimg.spriteapp.cn/profile/large/2017/07/17/596c4c7c2079f_mini.jpg","created_at":"2018-03-31 16:48:02","create_time":null,"passtime":"2018-03-31 16:48:02","love":"129","hate":"13","comment":"5","repost":"25","bookmark":"42","bimageuri":"http://wimg.spriteapp.cn/picture/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd_12.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"56895","theme_name":"健身部","theme_type":"1","videouri":"http://wvideo.spriteapp.cn/video/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd.mp4","videotime":181,"original_pid":"0","cache_version":2,"playcount":"4772","playfcount":"21","cai":"13","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd_12.jpg","image2":"http://wimg.spriteapp.cn/picture/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd_12.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd_12.jpg","image_small":"http://wimg.spriteapp.cn/picture/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd_12.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2018/0330/24704c56-3414-11e8-8e4e-1866daeb0df1_wpd_12.jpg","width":"640","height":"360","tag":"","t":1522486082,"ding":"129","favourite":"42","top_cmt":null,"themes":null},{"type":"41","text":"Matteo的《Panama》的改编中文版《大哥大姐如果你要骂我就请这样骂》","user_id":"19825277","name":"馒头音乐君","screen_name":"馒头音乐君","profile_image":"http://tva1.sinaimg.cn/crop.10.0.1166.1166.50/50156f31jw8f1eeg29vfaj20xc0xcwgk.jpg","created_at":"2017-09-16 22:28:03","create_time":null,"passtime":"2017-09-16 22:28:03","love":"1589","hate":"92","comment":"75","repost":"461","bookmark":"282","bimageuri":"http://wimg.spriteapp.cn/picture/2017/0916/59bcae13e7ece__b.jpg","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"0","theme_name":"","theme_type":"0","videouri":"http://wvideo.spriteapp.cn/video/2017/0916/59bcae140f689_wpd.mp4","videotime":61,"original_pid":"0","cache_version":2,"playcount":"55198","playfcount":"2770","cai":"92","weixin_url":null,"image1":"http://wimg.spriteapp.cn/picture/2017/0916/59bcae13e7ece__b.jpg","image2":"http://wimg.spriteapp.cn/picture/2017/0916/59bcae13e7ece__b.jpg","is_gif":false,"image0":"http://wimg.spriteapp.cn/picture/2017/0916/59bcae13e7ece__b.jpg","image_small":"http://wimg.spriteapp.cn/picture/2017/0916/59bcae13e7ece__b.jpg","cdn_img":"http://wimg.spriteapp.cn/picture/2017/0916/59bcae13e7ece__b.jpg","width":"640","height":"360","tag":"","t":1505572083,"ding":"1589","favourite":"282","top_cmt":null,"themes":null},{"type":"10","text":"我怎么觉得给自己生了个情敌啊！好不容易等到老公回来，却让她给霸占着\u2026诶，我该么办哟?","user_id":"20540438","name":"风从这边来","screen_name":"风从这边来","profile_image":"http://wimg.spriteapp.cn/profile/large/2017/04/14/58f0c4db27114_mini.jpg","created_at":"2018-12-03 08:26:01","create_time":null,"passtime":"2018-12-03 08:26:01","love":"204","hate":"17","comment":"100","repost":"4","bookmark":"7","bimageuri":"","voiceuri":null,"voicetime":null,"voicelength":null,"status":"4","theme_id":"44289","theme_name":"互动区","theme_type":"1","videouri":"","videotime":0,"original_pid":"0","cache_version":2,"playcount":null,"playfcount":null,"cai":"17","weixin_url":null,"image1":"http://wimg.spriteapp.cn/ugc/2018/12/02/5c02b13e2373c.gif","image2":"http://wimg.spriteapp.cn/ugc/2018/12/02/5c02b13e2373c.gif","is_gif":false,"image0":"http://wimg.spriteapp.cn/ugc/2018/12/02/5c02b13e2373c.gif","image_small":null,"cdn_img":"http://wimg.spriteapp.cn/ugc/2018/12/02/5c02b13e2373c.gif","width":"165","height":"250","tag":"","t":1543796761,"ding":"204","favourite":"7","top_cmt":null,"themes":null}]
     */
    private List<DataBean> data;

    public static MovieSubject objectFromData(String str) {

        return new Gson().fromJson(str, MovieSubject.class);
    }

    public static MovieSubject objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MovieSubject.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MovieSubject> arrayMovieSubjectFromData(String str) {

        Type listType = new TypeToken<ArrayList<MovieSubject>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<MovieSubject> arrayMovieSubjectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MovieSubject>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 10
         * text : 会不会有超级女粉会买
         * user_id : 22980432
         * name : 余生请温柔
         * screen_name : 余生请温柔
         * profile_image : http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6ae0c3f3_mini.jpg
         * created_at : 2019-06-03 19:20:01
         * create_time : null
         * passtime : 2019-06-03 19:20:01
         * love : 94
         * hate : 6
         * comment : 25
         * repost : 3
         * bookmark : 1
         * bimageuri :
         * voiceuri : null
         * voicetime : null
         * voicelength : null
         * status : 4
         * theme_id : 58240
         * theme_name : 搞笑图片
         * theme_type : 1
         * videouri :
         * videotime : 0
         * original_pid : 0
         * cache_version : 2
         * playcount : null
         * playfcount : null
         * cai : 6
         * weixin_url : null
         * image1 : http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg
         * image2 : http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg
         * is_gif : false
         * image0 : http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg
         * image_small : null
         * cdn_img : http://wimg.spriteapp.cn/ugc/2019/06/03/5cf48f69ef1b2_1.jpg
         * width : 690
         * height : 1804
         * tag :
         * t : 1559560801
         * ding : 94
         * favourite : 1
         * top_cmt : null
         * themes : null
         */

        private String type;
        private String text;
        private String user_id;
        private String name;
        private String screen_name;
        private String profile_image;
        private String created_at;
        private Object create_time;
        private String passtime;
        private String love;
        private String hate;
        private String comment;
        private String repost;
        private String bookmark;
        private String bimageuri;
        private Object voiceuri;
        private Object voicetime;
        private Object voicelength;
        private String status;
        private String theme_id;
        private String theme_name;
        private String theme_type;
        private String videouri;
        private int videotime;
        private String original_pid;
        private int cache_version;
        private Object playcount;
        private Object playfcount;
        private String cai;
        private Object weixin_url;
        private String image1;
        private String image2;
        private boolean is_gif;
        private String image0;
        private Object image_small;
        private String cdn_img;
        private String width;
        private String height;
        private String tag;
        private int t;
        private String ding;
        private String favourite;
        private Object top_cmt;
        private Object themes;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public String getHate() {
            return hate;
        }

        public void setHate(String hate) {
            this.hate = hate;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getRepost() {
            return repost;
        }

        public void setRepost(String repost) {
            this.repost = repost;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public String getBimageuri() {
            return bimageuri;
        }

        public void setBimageuri(String bimageuri) {
            this.bimageuri = bimageuri;
        }

        public Object getVoiceuri() {
            return voiceuri;
        }

        public void setVoiceuri(Object voiceuri) {
            this.voiceuri = voiceuri;
        }

        public Object getVoicetime() {
            return voicetime;
        }

        public void setVoicetime(Object voicetime) {
            this.voicetime = voicetime;
        }

        public Object getVoicelength() {
            return voicelength;
        }

        public void setVoicelength(Object voicelength) {
            this.voicelength = voicelength;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTheme_id() {
            return theme_id;
        }

        public void setTheme_id(String theme_id) {
            this.theme_id = theme_id;
        }

        public String getTheme_name() {
            return theme_name;
        }

        public void setTheme_name(String theme_name) {
            this.theme_name = theme_name;
        }

        public String getTheme_type() {
            return theme_type;
        }

        public void setTheme_type(String theme_type) {
            this.theme_type = theme_type;
        }

        public String getVideouri() {
            return videouri;
        }

        public void setVideouri(String videouri) {
            this.videouri = videouri;
        }

        public int getVideotime() {
            return videotime;
        }

        public void setVideotime(int videotime) {
            this.videotime = videotime;
        }

        public String getOriginal_pid() {
            return original_pid;
        }

        public void setOriginal_pid(String original_pid) {
            this.original_pid = original_pid;
        }

        public int getCache_version() {
            return cache_version;
        }

        public void setCache_version(int cache_version) {
            this.cache_version = cache_version;
        }

        public Object getPlaycount() {
            return playcount;
        }

        public void setPlaycount(Object playcount) {
            this.playcount = playcount;
        }

        public Object getPlayfcount() {
            return playfcount;
        }

        public void setPlayfcount(Object playfcount) {
            this.playfcount = playfcount;
        }

        public String getCai() {
            return cai;
        }

        public void setCai(String cai) {
            this.cai = cai;
        }

        public Object getWeixin_url() {
            return weixin_url;
        }

        public void setWeixin_url(Object weixin_url) {
            this.weixin_url = weixin_url;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public boolean isIs_gif() {
            return is_gif;
        }

        public void setIs_gif(boolean is_gif) {
            this.is_gif = is_gif;
        }

        public String getImage0() {
            return image0;
        }

        public void setImage0(String image0) {
            this.image0 = image0;
        }

        public Object getImage_small() {
            return image_small;
        }

        public void setImage_small(Object image_small) {
            this.image_small = image_small;
        }

        public String getCdn_img() {
            return cdn_img;
        }

        public void setCdn_img(String cdn_img) {
            this.cdn_img = cdn_img;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public String getDing() {
            return ding;
        }

        public void setDing(String ding) {
            this.ding = ding;
        }

        public String getFavourite() {
            return favourite;
        }

        public void setFavourite(String favourite) {
            this.favourite = favourite;
        }

        public Object getTop_cmt() {
            return top_cmt;
        }

        public void setTop_cmt(Object top_cmt) {
            this.top_cmt = top_cmt;
        }

        public Object getThemes() {
            return themes;
        }

        public void setThemes(Object themes) {
            this.themes = themes;
        }
    }
}
