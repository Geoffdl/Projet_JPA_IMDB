package fr.diginamic.geoff.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    public String myString = "This is a well structured sentence.";

    @Test
    void test_Split_Sentence() {
        String[] splitSentence = StringUtils.stringToArrayOfStrings(myString, " ");
        assertEquals(6, splitSentence.length);
        assertEquals("This", splitSentence[0]);
    }
}