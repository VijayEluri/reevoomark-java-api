package com.reevoo.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReevooMarkRecordTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreation() {
        ReevooMarkRecord rmr = ReevooMarkRecord.createRecord("test_record", 1000, 200, 100);
        assertEquals(rmr.getValue(), "test_record");
        assertEquals(rmr.getStatus(), 200);
        assertEquals(rmr.getReviewCount(), 100);
    }

    @Test
    public void testExpirationWithPositiveTimeout() {
        ReevooMarkRecord rmr = ReevooMarkRecord.createRecord("test_record", 1000, 200, 100);
        assertEquals(rmr.getValue(), "test_record");
        assertTrue(rmr.fresh());
    }

    @Test
    public void testExpirationWithNegativeTimeout() {
        ReevooMarkRecord rmr = ReevooMarkRecord.createRecord("test_record", -100, 200, 100);
        assertEquals(rmr.getValue(), "test_record");
        assertTrue(rmr.hasExpired());
    }

    @Test
    public void testCanCreateUsingExistingObject() {
        ReevooMarkRecord rmr_existing = ReevooMarkRecord.createRecord("test_record", -100, 200, 100);
        ReevooMarkRecord rmr_new = ReevooMarkRecord.createRecord(rmr_existing, 100, 200);
        assertEquals(rmr_new.getValue(), "test_record");
        assertEquals(rmr_new.getStatus(), 200);
        assertEquals(rmr_new.getReviewCount(), 100);
        assertTrue(rmr_new.fresh());

    }
}
