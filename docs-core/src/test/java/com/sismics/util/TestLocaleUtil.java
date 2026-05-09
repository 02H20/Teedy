package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Test of the locale utilities.
 */
public class TestLocaleUtil {
    @Test
    public void getLocaleDefaultsToEnglishForMissingCodeTest() {
        Assert.assertEquals(Locale.ENGLISH, LocaleUtil.getLocale(null));
        Assert.assertEquals(Locale.ENGLISH, LocaleUtil.getLocale(""));
    }

    @Test
    public void getLocaleParsesLanguageOnlyTest() {
        Locale locale = LocaleUtil.getLocale("fr");

        Assert.assertEquals("fr", locale.getLanguage());
        Assert.assertEquals("", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void getLocaleParsesLanguageAndCountryTest() {
        Locale locale = LocaleUtil.getLocale("fr_CA");

        Assert.assertEquals("fr", locale.getLanguage());
        Assert.assertEquals("CA", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void getLocaleParsesLanguageCountryAndVariantTest() {
        Locale locale = LocaleUtil.getLocale("zh_CN_Hans");

        Assert.assertEquals("zh", locale.getLanguage());
        Assert.assertEquals("CN", locale.getCountry());
        Assert.assertEquals("Hans", locale.getVariant());
    }
}
