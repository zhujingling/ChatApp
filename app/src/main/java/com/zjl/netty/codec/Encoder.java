package com.zjl.netty.codec;

import java.util.List;

import com.google.gson.Gson;
import com.netty.pkg.Pkg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class Encoder extends MessageToMessageEncoder<Pkg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Pkg pkg, List<Object> out)
			throws Exception {
		// TODO Auto-generated method stub
		// **********编码开始**********
		WebSocketFrame encode = new TextWebSocketFrame(new Gson().toJson(pkg));
		out.add(encode);
		// **********编码结束**********
	}

}
