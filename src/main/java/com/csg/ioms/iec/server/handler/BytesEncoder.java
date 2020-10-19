package com.csg.ioms.iec.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * 数组编码器
 * @author sun
 *
 */
public class BytesEncoder extends MessageToByteEncoder<byte[]> {

	
	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
		out.writeBytes(msg);
	}

}
