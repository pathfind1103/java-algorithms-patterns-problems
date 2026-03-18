import java.io.*;
import java.util.*;

public class Astrograd {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine().trim());

        // Массив для очереди (размер с запасом под n операций)
        int[] queue = new int[n * 2];
        // Позиция id в массиве queue
        int[] pos = new int[100001];

        int head = n; // Начинаем с середины, чтобы было куда расти
        int tail = n;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 1:
                    int id = Integer.parseInt(st.nextToken());
                    pos[id] = tail;
                    queue[tail++] = id;
                    break;
                case 2:
                    head++; // Первый ушел
                    break;
                case 3:
                    tail--; // Последний ушел
                    break;
                case 4:
                    int q = Integer.parseInt(st.nextToken());
                    out.println(pos[q] - head);
                    break;
                case 5:
                    out.println(queue[head]);
                    break;
            }
        }
        out.flush();
    }
}