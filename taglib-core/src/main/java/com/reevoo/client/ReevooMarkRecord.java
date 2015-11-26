package com.reevoo.client;

import java.util.Calendar;
import java.util.Date;

public final class ReevooMarkRecord {

    private Date expirationTime;
    private final String value;
    private final int status;
    private final int reviewCount;
    private int consecutiveFailedAttempts = 0;

    private ReevooMarkRecord(String value, int timeout, int status, int reviewCount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, timeout);
        expirationTime = cal.getTime();
        this.status = status;
        this.value = value;
        this.reviewCount = reviewCount;
    }

    public boolean hasExpired() {
        return new Date().after(expirationTime);
    }

    public boolean fresh() {
        return !hasExpired();
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getValue() {
        return this.value;
    }

    public int getStatus() {
        return this.status;
    }

    public int getReviewCount() {
        return this.reviewCount;
    }

    public int getConsecutiveFailedAttempts() {
        return this.consecutiveFailedAttempts;
    }

    /**
     * Creates a new ReevooMarkRecord initialized with the provided value.
     * @param value
     * @param timeout
     * @param status
     * @param reviewCount
     * @return The new ReevooMarkRecord instance.
     */
    public static ReevooMarkRecord createRecord(String value, int timeout, int status, int reviewCount) {
        return new ReevooMarkRecord(value, timeout, status, reviewCount);
    }

    /**
     * Creates a new ReevooMarkRecord as a clone of the provided one and overriding the provided values
     * @param old
     * @param timeout
     * @param status
     * @return The new ReevooMarkRecord instance.
     */
    public static ReevooMarkRecord createRecord(ReevooMarkRecord old, int timeout, int status) {
        ReevooMarkRecord newRecord;
        if (old != null) {
            newRecord = new ReevooMarkRecord(old.value, timeout, status, old.reviewCount);
        } else {
            newRecord = new ReevooMarkRecord(null, timeout, status, 0);
        }
        if (status >= 400) {
            newRecord.consecutiveFailedAttempts = old != null ? old.consecutiveFailedAttempts + 1 : 1;
        } else {
            newRecord.consecutiveFailedAttempts = 0;
        }
        return newRecord;
    }


}
