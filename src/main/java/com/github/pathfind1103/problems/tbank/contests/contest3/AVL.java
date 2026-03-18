import java.io.*;
import java.util.*;

public class AVL {
    static int[][] adj;
    static boolean isAVL = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());

        adj = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            adj[i][0] = Integer.parseInt(st.nextToken()); // левый
            adj[i][1] = Integer.parseInt(st.nextToken()); // правый
        }

        // Проверка на BST: передаем допустимые границы значений [min, max]
        // Для корня это вся область int, но по условию вершины от 0 до n-1
        if (!checkBST(root, -1, n)) {
            System.out.println(0);
            return;
        }

        // Проверка высот
        checkHeight(root);

        System.out.println(isAVL ? 1 : 0);
    }

    // Рекурсивная проверка свойств BST
    static boolean checkBST(int v, int min, int max) {
        if (v == -1) return true;
        if (v <= min || v >= max) return false;

        return checkBST(adj[v][0], min, v) && checkBST(adj[v][1], v, max);
    }

    // Рекурсивное вычисление высоты и проверка баланса
    static int checkHeight(int v) {
        if (v == -1) return 0;

        int leftH = checkHeight(adj[v][0]);
        int rightH = checkHeight(adj[v][1]);

        if (Math.abs(leftH - rightH) > 1) {
            isAVL = false;
        }

        return Math.max(leftH, rightH) + 1;
    }
}
