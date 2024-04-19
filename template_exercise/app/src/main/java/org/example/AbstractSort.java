package org.example;

public abstract class AbstractSort {
    public void sort(int[] k) {
        int n = k.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(k[j], k[j+1])) {
                    int ppp = k[j];
                    k[j] = k[j + 1];
                    k[j + 1] = ppp;
                }
            }
        }
    }
    abstract boolean compare(int left, int right);
}
