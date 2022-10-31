package querystream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static querystream.QueryReturnStream.*;

class QueryReturnStreamTest {

    private static final boolean TRUE = true;
    private static final boolean FALSE = false;

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String VALUE3 = "value3";
    private static final String VALUE4 = "value4";
    private static final String VALUE5 = "value5";

    @Test
    void test1() {
        String value = ifTrueReturn(TRUE, VALUE1).orElse(VALUE2);
        assertEquals(value, VALUE1);
    }

    @Test
    void test2() {
        String value = ifFalseReturn(FALSE, VALUE1).orElse(VALUE2);
        assertEquals(value, VALUE1);
    }

    @Test
    void test3() {
        String value = ifTrueReturn(FALSE, VALUE1).orElse(VALUE2);
        assertEquals(value, VALUE2);
    }

    @Test
    void test4() {
        String value = ifFalseReturn(TRUE, VALUE1).orElse(VALUE2);
        assertEquals(value, VALUE2);
    }

    @Test
    void test5() {
        String value = ifTrueReturn(TRUE, VALUE1)
                .orElseIf(TRUE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE1);
    }

    @Test
    void test6() {
        String value = ifTrueReturn(FALSE, VALUE1)
                .orElseIf(TRUE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE2);
    }

    @Test
    void test7() {
        String value = ifFalseReturn(FALSE, VALUE1)
                .orElseIf(TRUE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE1);
    }

    @Test
    void test8() {
        String value = ifFalseReturn(TRUE, VALUE1)
                .orElseIf(TRUE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE2);
    }

    @Test
    void test9() {
        String value = ifTrueReturn(FALSE, VALUE1)
                .orElseIf(FALSE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE3);
    }

    @Test
    void test10() {
        String value = ifFalseReturn(TRUE, VALUE1)
                .orElseIf(FALSE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE3);
    }

    @Test
    void test11() {
        String value = ifTrueReturn(FALSE, VALUE1)
                .orElseIf(FALSE, VALUE2)
                .orElseIf(TRUE, VALUE3)
                .orElse(VALUE4);
        assertEquals(value, VALUE3);
    }

    @Test
    void test12() {
        String value = ifTrueReturn(FALSE, VALUE1)
                .orElseIfNot(FALSE, VALUE2)
                .orElse(VALUE3);
        assertEquals(value, VALUE2);
    }

    @Test
    void test13() {
        String value = ifFalseReturn(TRUE, VALUE1)
                .orElseIf(FALSE, VALUE2)
                .orElseIfNot(FALSE, VALUE3)
                .orElse(VALUE4);
        assertEquals(value, VALUE3);
    }

    @Test
    void test14() {
        String value = ifTrueReturn(FALSE, VALUE1)
                .orElseIfNot(TRUE, VALUE2)
                .orElseIf(FALSE, VALUE3)
                .orElseIfNot(FALSE, VALUE4)
                .orElse(VALUE5);

        assertEquals(value, VALUE4);

    }

    @Test
    void test15() {
        String value = ifFalseReturn(TRUE, VALUE1)
                .orElseIf(FALSE, VALUE2)
                .orElseIfNot(TRUE, VALUE3)
                .orElseIf(FALSE, VALUE4)
                .orElse(VALUE5);
        assertEquals(value, VALUE5);
    }

    @Test
    void test16() {
        String value = ifTrueReturn(FALSE, VALUE1)
                .orElseIfNot(TRUE, VALUE2)
                .orElseIfNot(TRUE, VALUE3)
                .orElseIf(TRUE, VALUE4)
                .orElse(VALUE5);
        assertEquals(value, VALUE4);
    }

}