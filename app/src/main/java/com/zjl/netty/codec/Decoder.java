package com.zjl.netty.codec;

import com.google.gson.Gson;
import com.netty.pkg.Pkg;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class Decoder extends MessageToMessageDecoder<WebSocketFrame> {

	@Override
	protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		// **********解码开始**********
		String decode=((TextWebSocketFrame)frame).text();
		Pkg pkg=new Gson().fromJson(decode, Pkg.class);
		out.add(pkg);
		//**********解码结束**********
		
	}

}
