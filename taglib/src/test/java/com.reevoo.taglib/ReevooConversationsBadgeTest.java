package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooConversationsBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooConversationsBadge conversationsBadgeTag = new ReevooConversationsBadge();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        conversationsBadgeTag.setTrkref("FOO");
        conversationsBadgeTag.setDynamicAttribute("", "sku", "ABC123");
        setTag(conversationsBadgeTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor()
    {
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark reevoo-conversations\" href=\"//test.reevoo.com/partner/FOO/ABC123\"></a>");
    }


    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass(){
        conversationsBadgeTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark reevoo-conversations undecorated\" href=\"//test.reevoo.com/partner/FOO/ABC123\"></a>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody(){
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark reevoo-conversations\" href=\"//test.reevoo.com/partner/FOO/ABC123\">click here</a>");
    }
}
