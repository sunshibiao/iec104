package com.csg.ioms.iec.exception;

/**
 * @Author sun
 */
public class LengthException extends Exception {

    private long desired;//期望的长度
    private long reality;//实际的长度

    public LengthException(long desired, long reality) {
        this.desired = desired;
        this.reality = reality;
    }

    @Override
    public String toString() {
        return "报文长度错误，期望报文长度为" + desired +
                "字节。而真实的报文长度为" + reality +
                "字节";
    }
}
