package com.ljl.example.netty.websocket.handler;

import com.ljl.example.netty.websocket.util.NettyConnectionUtil;
import com.ljl.example.netty.websocket.util.RedisUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import jodd.typeconverter.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Sharable
@Slf4j
public class WebsocketHandler extends SelfHandler<TextWebSocketFrame> {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String msgStr=msg.text();
        if(msgStr.contains(":")){
            String[] userAndContent=msgStr.split(":");
            NettyConnectionUtil.userChannelMap.put(Convert.toLong(userAndContent[0]),ctx);
        }
        //处理不同类型的数据片段约定
        //入站消息 AUTH_token    INROOM_wid   INLIVEROOM_livecode    OUTLIVEROOM_livecode   OUTROOM_wid
        //出站消息 FRESH_goods   BARGAINACTIV_json  ROOMBARGAIN_3  ORDER_text
        /*String[] typeAndContent = msg.text().split("_");
        log.info(msg.text());
        if(typeAndContent==null || typeAndContent.length!=2){
            ctx.channel().close();
        }else{
            switch (ConstantEnum.of(typeAndContent[0])){
                case AUTH:
                    String tokenKey = String.format(RedisKeyConstant.AUTHKEY,typeAndContent[1]);
                    if(tokenKey.equals(redisUtil.getString(tokenKey))){
                    //if(typeAndContent[1].equals("test")){
                         log.info("入站消息：{}",typeAndContent[1]);
                         AuthQueue.removeId(ctx.channel().id().asLongText());
                         NettyConnectionUtil.channelUserMap.put(ctx.channel().id().asLongText(),0L);
                    }else{
                        log.info("权限校验失败 key:{},value:{}",tokenKey,redisUtil.getString(tokenKey));
                    }
                    break;
                case INROOM:
                    log.info("进入直播间wid,{}",typeAndContent[1]);
                    NettyConnectionUtil.userAddInRoom(Long.parseLong(typeAndContent[1]),ctx);
                    break;
                case INLIVEROOM:
                    log.info("进入场次：{}",typeAndContent[1]);
                    NettyConnectionUtil.userAddInLiveRoom(typeAndContent[1],ctx);
                    break;
                case OUTLIVEROOM:
                    log.info("退出场次：{}",typeAndContent[1]);
                    NettyConnectionUtil.userOutLiveRoom(ctx);
                    break;
                case OUTROOM:
                    log.info("退出直播间：{}",typeAndContent[1]);
                    NettyConnectionUtil.userOutRoom(ctx);
                    break;
                case HEART:
                    log.info("心跳检测wid：{}，现存直播间通道数：{}",typeAndContent[1], NettyConnectionUtil.userChannelMap.get(Long.parseLong(typeAndContent[1])));
                    break;
                case TEST:
                    System.err.println(typeAndContent[1]);
                    ctx.writeAndFlush(new TextWebSocketFrame("收到消息:"+typeAndContent[1]+",ack!"));
            }
        }*/
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //String channelId = ctx.channel().id().asLongText();
        log.info("通道激活：{}",ctx);
        /*if(!AuthQueue.authMap.containsKey(channelId)){
            AuthTask authTask = new AuthTask(5L, ctx);
            AuthQueue.authMap.put(channelId,authTask);
            AuthQueue.delayQueue.offer(authTask);
            log.info("delayQueue content:{}", Arrays.toString(AuthQueue.delayQueue.toArray()));
        }*/
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        NettyConnectionUtil.userOutRoom(ctx);
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(idleStateEvent.state() == IdleState.READER_IDLE){
                log.info("通道：{}，30秒未检测到读事件发生删除通道！",ctx);
                //ctx.channel().close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
