package com.csg.ioms.iec.server.handler;

import java.util.List;

import com.csg.ioms.iec.core.Decoder104;
import com.csg.ioms.iec.core.Iec104ThreadLocal;
import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.protocol.Analysis;
import com.csg.ioms.iec.utils.ByteUtil;
import com.csg.ioms.iec.utils.Iec104Util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;


/**
 * 解码器
 * @author sun
 *
 */
public class DataDecoder extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] data = new byte[in.readableBytes()];
		in.readBytes(data);
		String str=Analysis.analysis(ByteUtil.byteArrayToHexString(data));
		System.out.println(str);
		short send = Iec104Util.getSend(ByteUtil.getByte(data, 2, 4));
		Iec104ThreadLocal.getControlPool().setAccept(send);
		MessageDetail ruleDetail104 = Decoder104.encoder(data);
		out.add(ruleDetail104);
	}
}
