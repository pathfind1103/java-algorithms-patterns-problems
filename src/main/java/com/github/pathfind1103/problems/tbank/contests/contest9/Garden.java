package com.github.pathfind1103.problems.tbank.contests.contest9;

import java.io.*;
import java.util.*;

public class Garden {
    // Parses coordinate token that can be integer or end with ".5".
    // Returns value multiplied by 2 (scaled coordinate).
    private static long parseScaled2(String s) {
        int dot = s.indexOf('.');
        if (dot < 0) return Long.parseLong(s) * 2L;
        long intPart = Long.parseLong(s.substring(0, dot));
        String frac = s.substring(dot + 1);
        // In this problem coordinates come from centers of integer-grid squares,
        // so the only possible fractional part is 0 or 5 (i.e. .0 or .5).
        long add = (frac.length() > 0 && frac.charAt(0) == '5') ? 1L : 0L;
        return intPart * 2L + add;
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }

        long nextLong() throws IOException {
            String s = next();
            return s == null ? Long.MIN_VALUE : Long.parseLong(s);
        }
    }

    private static final class Segment {
        long end;    // exclusive
        long height;
        Segment(long end, long height) {
            this.end = end;
            this.height = height;
        }
    }

    // "Skyline": partition [0, W2) into segments with constant filled height.
    private static final class Skyline {
        private final long W2;
        private final TreeMap<Long, Segment> segs = new TreeMap<>();
        // height -> sorted set of segment starts having that height
        private final TreeMap<Long, TreeSet<Long>> byHeight = new TreeMap<>();

        Skyline(long W2) {
            this.W2 = W2;
            segs.put(0L, new Segment(W2, 0L));
            byHeight.computeIfAbsent(0L, k -> new TreeSet<>()).add(0L);
        }

        private void addHeightIndex(long h, long start) {
            byHeight.computeIfAbsent(h, k -> new TreeSet<>()).add(start);
        }

        private void removeHeightIndex(long h, long start) {
            TreeSet<Long> set = byHeight.get(h);
            if (set == null) return;
            set.remove(start);
            if (set.isEmpty()) byHeight.remove(h);
        }

        // Ensure there is a segment starting at position x, return its start key.
        private long split(long x) {
            if (x <= 0) return 0;
            if (x >= W2) return W2;
            Map.Entry<Long, Segment> e = segs.floorEntry(x);
            long start = e.getKey();
            if (start == x) return x;
            Segment cur = e.getValue();
            long end = cur.end;
            long h = cur.height;
            // modify existing segment to end at x
            cur.end = x;
            // insert new segment [x, end) with same height
            segs.put(x, new Segment(end, h));
            addHeightIndex(h, x);
            return x;
        }

        // Returns (cx, cy) - leftmost start among globally minimal height segments.
        long[] getMinCorner() {
            Map.Entry<Long, TreeSet<Long>> e = byHeight.firstEntry();
            long cy = e.getKey();
            long cx = e.getValue().first();
            return new long[]{cx, cy};
        }

        // Returns segment containing x (must exist).
        Map.Entry<Long, Segment> segmentAt(long x) {
            return segs.floorEntry(x);
        }

        // Assign interval [l, r) to newHeight, expecting it to be a "raise".
        void assign(long l, long r, long newHeight) {
            if (l >= r) return;
            split(l);
            split(r);

            NavigableMap<Long, Segment> sub = segs.subMap(l, true, r, false);
            ArrayList<Long> keys = new ArrayList<>(sub.keySet());
            for (long k : keys) {
                Segment s = segs.get(k);
                removeHeightIndex(s.height, k);
                segs.remove(k);
            }

            // insert new segment
            segs.put(l, new Segment(r, newHeight));
            addHeightIndex(newHeight, l);

            // merge with previous
            Map.Entry<Long, Segment> prevE = segs.lowerEntry(l);
            if (prevE != null) {
                long ps = prevE.getKey();
                Segment p = prevE.getValue();
                Segment c = segs.get(l);
                if (p.height == c.height && p.end == l) {
                    // extend prev
                    removeHeightIndex(c.height, l);
                    p.end = c.end;
                    segs.remove(l);
                    l = ps;
                }
            }

            // merge with next
            Map.Entry<Long, Segment> curE = segs.floorEntry(l);
            Map.Entry<Long, Segment> nextE = segs.higherEntry(curE.getKey());
            if (nextE != null) {
                long ns = nextE.getKey();
                Segment cur = curE.getValue();
                Segment nxt = nextE.getValue();
                if (cur.height == nxt.height && cur.end == ns) {
                    removeHeightIndex(nxt.height, ns);
                    cur.end = nxt.end;
                    segs.remove(ns);
                }
            }
        }
    }

    private static final class PointRef {
        long x2, y2;
        int id;
        PointRef(long x2, long y2, int id) {
            this.x2 = x2;
            this.y2 = y2;
            this.id = id;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String Ws = fs.next();
        if (Ws == null) return;
        long W = Long.parseLong(Ws);
        long H = Long.parseLong(fs.next());
        int N = (int) Long.parseLong(fs.next());

        long W2 = W * 2L;
        long H2 = H * 2L;

        long[] X2 = new long[N];
        long[] Y2 = new long[N];
        long[] ans = new long[N]; // original side length (integer)

        HashMap<Long, ArrayList<PointRef>> diag = new HashMap<>(N * 2);

        for (int i = 0; i < N; i++) {
            String xs = fs.next();
            String ys = fs.next();
            long x2 = parseScaled2(xs);
            long y2 = parseScaled2(ys);
            X2[i] = x2;
            Y2[i] = y2;
            long d = y2 - x2;
            diag.computeIfAbsent(d, k -> new ArrayList<>()).add(new PointRef(x2, y2, i));
        }

        // sort each diagonal bucket by x increasing (then y just in case)
        for (ArrayList<PointRef> list : diag.values()) {
            list.sort((a, b) -> {
                if (a.x2 != b.x2) return Long.compare(a.x2, b.x2);
                if (a.y2 != b.y2) return Long.compare(a.y2, b.y2);
                return Integer.compare(a.id, b.id);
            });
        }

        HashMap<Long, Integer> ptr = new HashMap<>(diag.size() * 2);
        Skyline sky = new Skyline(W2);

        int done = 0;
        while (done < N) {
            long[] corner = sky.getMinCorner();
            long cx = corner[0];
            long cy = corner[1];
            if (cy >= H2) break; // completely filled

            long d = cy - cx;
            ArrayList<PointRef> list = diag.get(d);
            if (list == null) throw new IllegalStateException("No points on required diagonal");
            int p = ptr.getOrDefault(d, 0);

            // We may need to skip points that belong to already-filled corners (defensive).
            PointRef pr = null;
            long hh = -1;
            long L2side;

            while (true) {
                if (p >= list.size()) throw new IllegalStateException("Diagonal bucket exhausted");
                pr = list.get(p);
                hh = pr.x2 - cx; // half-side in scaled coords = original side length
                if (hh <= 0) { p++; continue; }
                if (pr.y2 - cy != hh) { p++; continue; } // should not happen if diagonal matches
                L2side = hh * 2L; // side length in scaled coords
                // check bounds
                if (cx + L2side > W2 || cy + L2side > H2) { p++; continue; }
                // check skyline is flat on [cx, cx+L)
                Map.Entry<Long, Segment> segE = sky.segmentAt(cx);
                Segment seg = segE.getValue();
                if (seg.height != cy) throw new IllegalStateException("Corner mismatch");
                if (seg.end < cx + L2side) { p++; continue; }
                break;
            }

            ptr.put(d, p + 1);

            // place square
            long newH = cy + L2side;
            sky.assign(cx, cx + L2side, newH);
            ans[pr.id] = hh; // original side length
            done++;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i > 0) out.append(' ');
            out.append(ans[i]);
        }
        out.append('\n');
        System.out.print(out.toString());
    }
}

