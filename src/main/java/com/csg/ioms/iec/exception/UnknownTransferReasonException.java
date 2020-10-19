package com.csg.ioms.iec.exception;

/**
 * @Author sun
 */
public class UnknownTransferReasonException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4977251357848627763L;

	public UnknownTransferReasonException() {
        super();
    }

    @Override
    public String toString() {
        return "未定义传送原因。";
    }
}
