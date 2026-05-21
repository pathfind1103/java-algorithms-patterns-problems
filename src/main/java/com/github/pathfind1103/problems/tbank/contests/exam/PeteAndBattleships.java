package com.github.pathfind1103.problems.tbank.contests.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PeteAndBattleships {
    static int n;
    static int m;
    static char[][] field;
    static int[] queue;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        field = new char[n][m];
        queue = new int[n * m];

        for (int i = 0; i < n; i++) {
            field[i] = br.readLine().toCharArray();
        }

        int whole = 0;
        int damaged = 0;
        int destroyed = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] != '.') {
                    int type = bfs(i, j);

                    if (type == 1) {
                        whole++;
                    } else if (type == 2) {
                        damaged++;
                    } else {
                        destroyed++;
                    }
                }
            }
        }

        System.out.println(whole + " " + damaged + " " + destroyed);
    }

    public static int bfs(int startX, int startY) {
        int head = 0;
        int tail = 0;

        queue[tail++] = startX * m + startY;
        char start = field[startX][startY];
        field[startX][startY] = '.';

        boolean hasWhole = start == '#';
        boolean hasHit = start == 'X';

        while (head < tail) {
            int current = queue[head++];
            int x = current / m;
            int y = current % m;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (field[nx][ny] == '.') continue;

                if (field[nx][ny] == '#') {
                    hasWhole = true;
                } else {
                    hasHit = true;
                }

                field[nx][ny] = '.';
                queue[tail++] = nx * m + ny;
            }
        }

        if (hasWhole && !hasHit) {
            return 1;
        }

        if (hasWhole && hasHit) {
            return 2;
        }

        return 3;
    }
}
