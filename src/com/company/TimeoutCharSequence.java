package com.company;

public class TimeoutCharSequence implements CharSequence{
    private CharSequence charSequence;
    private long timeout;
    private long startTime;

    public TimeoutCharSequence(CharSequence charSequence, long timeout) {
        this.charSequence = charSequence;
        this.timeout = timeout;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public int length() {
        return charSequence.length();
    }

    @Override
    public char charAt(int index) {
        if (System.currentTimeMillis() > startTime + timeout) {
            throw new RegexpTimeoutException();
        }
        return charSequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new TimeoutCharSequence(charSequence.subSequence(start, end), timeout - (System.currentTimeMillis() - startTime));
    }
}
