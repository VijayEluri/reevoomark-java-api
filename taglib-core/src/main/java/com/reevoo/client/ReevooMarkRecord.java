package com.reevoo.client;

import java.util.Calendar;
import java.util.Date;

public final class ReevooMarkRecord {
    private final Date expiration_time;
    public final String value;
    public final int status;
    public final int review_count;

    public ReevooMarkRecord(String value, int time_out, int status, int review_count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, time_out);
        expiration_time = cal.getTime();
        this.status = status;
        this.value = value;
        this.review_count = review_count;
    }

    public ReevooMarkRecord(ReevooMarkRecord old, int time_out) {
        this(old.value, time_out, old.status, old.review_count);
    }

    public boolean hasExpired() {
        return new Date().after(expiration_time);
    }

    public boolean fresh() {
        return !hasExpired();
    }

    public Date getExpirationTime() {
        return expiration_time;
    }
}
