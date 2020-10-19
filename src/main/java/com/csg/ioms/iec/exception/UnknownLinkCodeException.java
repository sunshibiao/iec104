package com.csg.ioms.iec.exception;

/**
 * @Author sun
 */
public class UnknownLinkCodeException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8260695109983771420L;

	public UnknownLinkCodeException() {
        super();
    }

    @Override
    public String toString() {
        return "未知类型的链路功能码（平衡式）";
    }
}
