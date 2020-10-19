package com.csg.ioms.iec.exception;

/**
 * @Author sun
 */
public class IllegalFormatException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3986445004211566058L;

	public IllegalFormatException() {
        super();
    }

    public String toString() {
        return "非法报文。报文应为16进制数字组成，并且由0x68或者0x10为报文头，0x16为报文尾（104规约无）。";
    }
}
