package aiss.peertubeminer.etl;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transformer {

    public static VM_Channel toVMChannel(Channel channel){
        VM_Channel vm_channel = new VM_Channel(
                UUID.randomUUID().toString(),
                channel.getName(),
                channel.getDescription(),
                String.valueOf(channel.getCreatedAt()),
                new ArrayList<>()
        );

        List<VM_Video> vm_videos = new ArrayList<>();
        for(Video video: channel.getVideos()){
            vm_videos.add(toVMVideo(video));
        }
        vm_channel.setVideos(vm_videos);

        return vm_channel;
    }

    public static VM_Video toVMVideo(Video video) {
        VM_Video vm_video = new VM_Video(
                UUID.randomUUID().toString(),
                video.getName(),
                video.getDescription(),
                String.valueOf(video.getPublishedAt()),
                toVMUser(video),
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

    public static VM_User toVMUser(Video video){
        return new VM_User(
                UUID.randomUUID().toString(),
                video.getAccount().getName(),
                video.getAccount().getUrl(),
                video.getAccount().getAvatars().stream().findFirst().isPresent()
                        ? video.getAccount().getAvatars().stream().findFirst().get().getFileUrl()
                        : ""
        );
    }

    public static VM_Comment toVMComment(Comment comment){
        return new VM_Comment(
                UUID.randomUUID().toString(),
                comment.getText(),
                comment.getCreatedAt()
        );
    }

    public static VM_Caption toVMCaption(Caption caption){
        return new VM_Caption(
                UUID.randomUUID().toString(),
                caption.getFileUrl(),
                caption.getLanguage().getLabel()
        );
    }
}
