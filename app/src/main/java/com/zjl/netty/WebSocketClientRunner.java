/*
 * Copyright 2014 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zjl.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import com.zjl.netty.codec.Decoder;
import com.zjl.netty.codec.Encoder;

import java.net.URI;

public final class WebSocketClientRunner {

    private final URI uri;

    public WebSocketClientRunner(URI uri) {
        this.uri = uri;
    }

    public void run() throws Exception {
       new Thread(){
           @Override
           public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            final WebSocketClientHandler handler = new WebSocketClientHandler(
                    WebSocketClientHandshakerFactory.newHandshaker(uri,
                            WebSocketVersion.V13, null, false,
                            new DefaultHttpHeaders()));

            final String protocol = uri.getScheme();
            int defaultPort;
            ChannelInitializer<SocketChannel> initializer;

            // Normal WebSocket
            if ("ws".equals(protocol)) {
                initializer = new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("http-codec",
                                new HttpClientCodec());

                        ch.pipeline().addLast("aggregator",
                                new HttpObjectAggregator(65535));

                        ch.pipeline().addLast("decoder", new Decoder());
                        ch.pipeline().addLast("encoder", new Encoder());
                        ch.pipeline().addLast("ws-handler", handler);
                    }
                };

                defaultPort = 80;
                // Secure WebSocket
            } else {
                throw new IllegalArgumentException("Unsupported protocol: "
                        + protocol);
            }
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(initializer);

            int port = uri.getPort();
            if (uri.getPort() == -1) {
                port = defaultPort;
            }
            ChannelFuture channelFuture = b.connect(uri.getHost(), port).sync();
            ChannelManage.getChannelManage().channel = channelFuture.channel();
            handler.handshakeFuture().sync();
            ChannelManage.getChannelManage().channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
           }
       }.start();
    }
}
