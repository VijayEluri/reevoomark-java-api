package com.reevoo.taglib;

import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooConversationSeriesBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooConversationSeriesBadge conversationSeriesBadgeTag = new ReevooConversationSeriesBadge();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        conversationSeriesBadgeTag.setTrkref("FOO");
        conversationSeriesBadgeTag.setSku("ABC123");
        setTag(conversationSeriesBadgeTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor()
    {
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark reevoo-conversations\" href=\"http://mark.reevoo.com/partner/FOO/series:ABC123\"></a>");
    }


    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass(){
        conversationSeriesBadgeTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark reevoo-conversations undecorated\" href=\"http://mark.reevoo.com/partner/FOO/series:ABC123\"></a>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody(){
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark reevoo-conversations\" href=\"http://mark.reevoo.com/partner/FOO/series:ABC123\">click here</a>");
    }
}
