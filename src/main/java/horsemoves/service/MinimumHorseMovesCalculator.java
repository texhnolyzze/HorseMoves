package horsemoves.service;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Texhnolyze
 */
final class MinimumHorseMovesCalculator {
//                                          x, y        x y
    static int compute(int w, int h, int[] start, int[] end) {
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};  
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
        Queue<TimedIntVec2> q = new LinkedList<>();
        boolean[][] visited = new boolean[h + 1][w + 1];
        visited[start[1]][start[0]] = true;
        q.add(new TimedIntVec2(start, 0));
        while (!q.isEmpty()) {
            TimedIntVec2 v = q.poll();
            if (v.x == end[0] && v.y == end[1]) 
                return v.time;
            int x, y;
            for (int i = 0; i < 8; i++) {  
                x = v.x + dx[i];  
                y = v.y + dy[i];
                if (isInside(x, y, w, h) && !visited[y][x]) {  
                    visited[y][x] = true;  
                    q.add(new TimedIntVec2(x, y, v.time + 1));  
                }  
            }  
        }
        return -1;
    }

    private static boolean isInside(int x, int y, int w, int h) {
        return x >= 1 && x <= w && y >= 1 && y <= h;
    }
    
    private static class TimedIntVec2 {
        int time;
        int x, y;
        TimedIntVec2(int[] coords, int time) {this(coords[0], coords[1], time);}
        TimedIntVec2(int x, int y, int time) {
            this.time = time;
            this.x = x;
            this.y = y;
        }
    }
    

}
