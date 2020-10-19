package com.csg.ioms.iec.exception;

/**
 * @Author sun
 */
public class CustomException extends Exception {
    private String content;

    public CustomException(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }

}
