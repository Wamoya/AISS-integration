package aiss.dailymotionminer.etl;

import aiss.dailymotionminer.model.dailymotion.Caption;
import aiss.dailymotionminer.model.dailymotion.Channel;
import aiss.dailymotionminer.model.dailymotion.Comment;
import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transformer {

    public static VM_Channel toVMChannel(Channel channel){
        VM_Channel vm_channel = new VM_Channel(
                null, //id
                channel.getScreenname(),
                channel.getDescription(),
                String.valueOf(channel.getCreatedTime()),
                new ArrayList<>()
        );
        VM_User vm_user = toVMUser(channel);

        List <VM_Video> vm_videos = new ArrayList<>();
        for(Video video: channel.getVideos()){
            vm_videos.add(toVMVideo(vm_user, video));
        }
        vm_channel.setVideos(vm_videos);

        return vm_channel;
    }

    public static VM_User toVMUser(Channel channel){
        return new VM_User(
                null, //id
                channel.getScreenname(),
                channel.getUrl(),
                channel.getAvatar240url()
        );
    }

    public static VM_Video toVMVideo(VM_User vm_user, Video video) {
        VM_Video vm_video = new VM_Video(
                null, //id
                video.getTitle(),
                video.getDescription(),
                String.valueOf(video.getCreatedTime()),
                vm_user,
                null, //Comments
                null //Captions
        );

        List <VM_Comment> vm_comments = new ArrayList<>();
        for(Comment comment: video.getComments()){
            vm_comments.add(toVMComment(comment));
        }
        vm_video.setComments(vm_comments);

        List <VM_Caption> vm_captions = new ArrayList<>();
        for(Caption caption: video.getCaptions()){
            vm_captions.add(toVMCaption(caption));
        }
        vm_video.setCaptions(vm_captions);

        return vm_video;
    }

    public static VM_Comment toVMComment(Comment comment){
        return new VM_Comment(
                null, //id
                comment.getText(),
                comment.getCreatedOn()
        );
    }

    public static VM_Caption toVMCaption(Caption caption){
        return new VM_Caption(
                null, //id
                caption.getUrl(),
                caption.getLanguage()
        );
    }

}
