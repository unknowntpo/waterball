package org.example;

import org.junit.jupiter.api.Test;

public class AbstractSortTest {
    @Test
    public void testClass1AndClass2() {
        Class1 c1 = new Class1();
        int[] array = new int[]{5, 3, 2, 1, 4};
        //        c1.p1(array);
        c1.sort(array);

        Class2 c2 = new Class2();
        //        c2.p2(array);
        c2.sort(array);
    }
}
