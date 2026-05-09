package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Test of the message utilities.
 */
public class TestMessageUtil {
    @Test
    public void getMessageFormatsExistingMessageTest() {
        String message = MessageUtil.getMessage(Locale.ENGLISH, "email.template.password_recovery.hello", "Alice");

        Assert.assertEquals("Hello Alice.", message);
    }

    @Test
    public void getMessageReturnsPlaceholderForMissingMessageTest() {
        String message = MessageUtil.getMessage(Locale.ENGLISH, "missing.message.key");

        Assert.assertEquals("**missing.message.key**", message);
    }

    @Test
    public void getMessageReturnsResourceBundleTest() {
        ResourceBundle bundle = MessageUtil.getMessage(Locale.ENGLISH);

        Assert.assertEquals("Please reset your password",
                bundle.getString("email.template.password_recovery.subject"));
    }
}
