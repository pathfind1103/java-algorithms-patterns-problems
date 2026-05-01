package com.github.pathfind1103.problems.tbank.contests.contest9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class VikaAndTheSegments {
    static class Seg {
        int f, s, e;
        Seg(int f, int s, int e) { this.f = f; this.s = Math.min(s, e); this.e = Math.max(s, e); }
    }

    // Событие для сканирующей прямой
    static class Event implements Comparable<Event> {
        int x, type, y1, y2; // type: -1 (начало H), 0 (вертикальный Query), 1 (конец H)
        Event(int x, int type, int y1, int y2) { this.x = x; this.type = type; this.y1 = y1; this.y2 = y2; }
        public int compareTo(Event o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.type, o.type); // Важен порядок: ADD -> QUERY -> REMOVE
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Seg> hor = new ArrayList<>(), ver = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            if (y1 == y2) hor.add(new Seg(y1, x1, x2));
            else ver.add(new Seg(x1, y1, y2));
        }

        // 1. Объединяем наложенные отрезки (H и V отдельно)
        hor = merge(hor);
        ver = merge(ver);

        long totalAns = 0;
        for (Seg s : hor) totalAns += (s.e - s.s + 1);
        for (Seg s : ver) totalAns += (s.e - s.s + 1);

        // 2. Сжатие координат Y (так как они до 10^9)
        TreeSet<Integer> yCoords = new TreeSet<>();
        for (Seg s : hor) yCoords.add(s.f);
        for (Seg s : ver) { yCoords.add(s.s); yCoords.add(s.e); }

        Map<Integer, Integer> map = new HashMap<>();
        int m = 0;
        for (int y : yCoords) map.put(y, m++);

        // 3. Создаем события
        List<Event> events = new ArrayList<>();
        for (Seg s : hor) {
            events.add(new Event(s.s, -1, s.f, 0)); // Начало горизонтального
            events.add(new Event(s.e, 1, s.f, 0));  // Конец горизонтального
        }
        for (Seg s : ver) {
            events.add(new Event(s.f, 0, s.s, s.e)); // Вертикальный (запрос)
        }
        Collections.sort(events);

        // 4. Сканирующая прямая + дерево отрезков на сумму
        int[] tree = new int[4 * m];
        long intersections = 0;
        for (Event e : events) {
            if (e.type == -1) update(0, 0, m - 1, map.get(e.y1), 1, tree);
            else if (e.type == 1) update(0, 0, m - 1, map.get(e.y1), -1, tree);
            else intersections += query(0, 0, m - 1, map.get(e.y1), map.get(e.y2), tree);
        }

        System.out.println(totalAns - intersections);
    }

    // Объединение отрезков на одной прямой
    static List<Seg> merge(List<Seg> list) {
        if (list.isEmpty()) return list;
        list.sort((a, b) -> a.f != b.f ? Integer.compare(a.f, b.f) : Integer.compare(a.s, b.s));
        List<Seg> res = new ArrayList<>();
        Seg cur = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Seg next = list.get(i);
            if (next.f == cur.f && next.s <= cur.e) cur.e = Math.max(cur.e, next.e);
            else { res.add(cur); cur = next; }
        }
        res.add(cur);
        return res;
    }

    static void update(int v, int tl, int tr, int pos, int val, int[] tree) {
        if (tl == tr) tree[v] += val;
        else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) update(2 * v + 1, tl, tm, pos, val, tree);
            else update(2 * v + 2, tm + 1, tr, pos, val, tree);
            tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
        }
    }

    static int query(int v, int tl, int tr, int l, int r, int[] tree) {
        if (l > r) return 0;
        if (l == tl && r == tr) return tree[v];
        int tm = (tl + tr) / 2;
        return query(2 * v + 1, tl, tm, l, Math.min(r, tm), tree) +
                query(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r, tree);
    }
}



