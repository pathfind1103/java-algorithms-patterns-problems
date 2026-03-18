package com.github.pathfind1103.problems.tbank.contests.contest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Heap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] array = new int[100000];
        array[0] = -1;

        int current = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 0) {
                int value = Integer.parseInt(st.nextToken());

                if (array[current] == -1) {
                    current += 1;
                    array[current] = value;
                    current += 1;
                    continue;
                }

                if (array[current] != -1) {
                    array[current] = value;
                    int tempCurr = current;
                    int dir = tempCurr % 2;

                    if (dir == 1) {
                        while (array[tempCurr] > array[(tempCurr - 1) / 2] && array[(tempCurr - 1) / 2] != -1) {
                            int temp = array[tempCurr];
                            array[tempCurr] = array[(tempCurr - 1) / 2];
                            array[(tempCurr - 1) / 2] = temp;
                            tempCurr = (tempCurr - 1) / 2;
                        }
                    } else {
                        while (array[tempCurr] > array[tempCurr / 2] && array[tempCurr / 2] != -1) {
                            int temp = array[tempCurr];
                            array[tempCurr] = array[tempCurr / 2];
                            array[tempCurr / 2] = temp;
                            tempCurr = tempCurr/ 2;
                        }
                    }

                    current += 1;
                }

            }

            if (command == 1) {
                System.out.println(array[1]);

                current--;
                if (current > 1) {
                    array[1] = array[current];

                    int idx = 1;
                    while (true) {
                        int leftChild = 2 * idx;
                        int rightChild = 2 * idx + 1;
                        int largest = idx;

                        if (leftChild < current && array[leftChild] > array[largest]) {
                            largest = leftChild;
                        }
                        if (rightChild < current && array[rightChild] > array[largest]) {
                            largest = rightChild;
                        }

                        if (largest == idx) break;

                        int temp = array[idx];
                        array[idx] = array[largest];
                        array[largest] = temp;
                        idx = largest;
                    }
                }
            }
        }
    }
}
