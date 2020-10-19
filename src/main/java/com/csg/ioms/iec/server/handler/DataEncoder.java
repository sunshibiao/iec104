package com.csg.ioms.iec.server.handler;


import com.csg.ioms.iec.core.Encoder104;
import com.csg.ioms.iec.core.Iec104ThreadLocal;
import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.utils.ByteUtil;
import com.csg.ioms.iec.utils.Iec104Util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 编码器
 * @author sun
 *
 */
@Slf4j
public class DataEncoder extends MessageToByteEncoder<MessageDetail> {

	
	@Override
	protected void encode(ChannelHandlerContext ctx, MessageDetail msg, ByteBuf out) throws Exception {
		byte[] bytes = Encoder104.encoder(msg);
		short accept = Iec104ThreadLocal.getControlPool().getAccept();
		short send = Iec104ThreadLocal.getControlPool().getSend();
		short terminalAddress = Iec104ThreadLocal.getIec104Conig().getTerminnalAddress();
		// 替换终端地址 发送序号和接收序号
		byte[] terminalAddressBytes = Iec104Util.getTerminalAddressByte(terminalAddress);
		byte[] icontrol = Iec104Util.getIcontrol(accept, send);
		for (int i = 0; i < icontrol.length; i++) {
			bytes[i + 2] = icontrol[i];
		}
		bytes[10] = terminalAddressBytes[0];
		bytes[11] = terminalAddressBytes[1];
		log.info(ByteUtil.byteArrayToHexString(bytes));
		out.writeBytes(bytes);
	}

}
