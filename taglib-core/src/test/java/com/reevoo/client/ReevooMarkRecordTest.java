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
        ReevooMarkRecord rmr = new ReevooMarkRecord("test_record", 1000, 200, 100);
        assertEquals(rmr.value, "test_record");
        assertEquals(rmr.status, 200);
        assertEquals(rmr.review_count, 100);
    }

    @Test
    public void testExpirationWithPositiveTimeout() {
        ReevooMarkRecord rmr = new ReevooMarkRecord("test_record", 1000, 200, 100);
        assertEquals(rmr.value, "test_record");
        assertTrue(rmr.fresh());
    }

    @Test
    public void testExpirationWithNegativeTimeout() {
        ReevooMarkRecord rmr = new ReevooMarkRecord("test_record", -100, 200, 100);
        assertEquals(rmr.value, "test_record");
        assertTrue(rmr.hasExpired());
    }

    @Test
    public void testCanCreateUsingExistingObject() {
        ReevooMarkRecord rmr_existing = new ReevooMarkRecord("test_record", -100, 200, 100);
        ReevooMarkRecord rmr_new = new ReevooMarkRecord(rmr_existing, 100);
        assertEquals(rmr_new.value, "test_record");
        assertEquals(rmr_new.status, 200);
        assertEquals(rmr_new.review_count, 100);
        assertTrue(rmr_new.fresh());

    }
}
