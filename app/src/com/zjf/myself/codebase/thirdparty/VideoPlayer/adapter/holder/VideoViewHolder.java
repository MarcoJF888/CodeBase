package com.zjf.myself.codebase.thirdparty.VideoPlayer.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.thirdparty.VideoPlayer.NiceVideoPlayer;
import com.zjf.myself.codebase.thirdparty.VideoPlayer.NiceVideoPlayerController;
import com.zjf.myself.codebase.thirdparty.VideoPlayer.bean.Video;


/**
 * Created by XiaoJianjun on 2017/5/21.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder {

    private NiceVideoPlayerController mController;
    private NiceVideoPlayer mVideoPlayer;

    public VideoViewHolder(View itemView) {
        super(itemView);
        mVideoPlayer = (NiceVideoPlayer) itemView.findViewById(R.id.nice_video_player);
    }

    public void setController(NiceVideoPlayerController controller) {
        mController = controller;
    }

    public void bindData(Video video) {
        mController.setTitle(video.getTitle());
        mController.setImage(video.getImageUrl());
        mVideoPlayer.setController(mController);
        mVideoPlayer.setUp(video.getVideoUrl(), null);
    }
}
