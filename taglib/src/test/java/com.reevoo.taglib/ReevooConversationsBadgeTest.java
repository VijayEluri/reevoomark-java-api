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
        verifyOutput("<reevoo-conversations-badge trkref=\"FOO\" sku=\"ABC123\"></reevoo-conversations-badge>");
    }


    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass(){
        conversationsBadgeTag.setDynamicAttribute("", "variant", "undecorated");;
        processTagLifecycle();
        verifyOutput("<reevoo-conversations-badge trkref=\"FOO\" sku=\"ABC123\" variant=\"undecorated\"></reevoo-conversations-badge>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody(){
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<reevoo-conversations-badge trkref=\"FOO\" sku=\"ABC123\">click here</reevoo-conversations-badge>");
    }
}
