package horsemoves.service;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Texhnolyze
 */
final class MinimumHorseMovesCalculator {
    
    private static final int[] DX = {-2, -1, 1, 2, -2, -1, 1, 2};
    private static final int[] DY = {-1, -2, -2, -1, 1, 2, 2, 1};
    
//                                          x, y        x y
    static int compute(int w, int h, int[] start, int[] end) {
        int[] boundingRect = calcBoundingRect(w, h, start, end);
        Queue<TimedIntVec2> q = new LinkedList<>();
        Set<TimedIntVec2> visited = new HashSet<>();
        TimedIntVec2 startAsVec = new TimedIntVec2(start, 0);
        visited.add(startAsVec);
        q.add(startAsVec);
        TimedIntVec2 temp = new TimedIntVec2(0, 0, 0);
        while (!q.isEmpty()) {
            TimedIntVec2 v = q.poll();
            if (v.x == end[0] && v.y == end[1]) 
                return v.time;
            TimedIntVec2 adj;
            for (int i = 0; i < 8; i++) {  
                temp.x = v.x + DX[i];
                temp.y = v.y + DY[i];
                if (temp.isInside(boundingRect) && !visited.contains(temp)) {  
                    adj = new TimedIntVec2(temp.x, temp.y, v.time + 1);
                    visited.add(adj);
                    q.add(adj);  
                }  
            }  
        }
        return -1;
    }
    
    private static int[] calcBoundingRect(int w, int h, int[] start, int[] end) {
        int minX = w, minY = h, maxX = 1, maxY = 1;
        minX = max(1, min(minX, start[0] - 2));
        minX = max(1, min(minX, end[0] - 2));
        minY = max(1, min(minY, start[1] - 2));
        minY = max(1, min(minY, end[1] - 2));
        maxX = min(w, max(maxX, start[0] + 2));
        maxX = min(w, max(maxX, end[0] + 2));
        maxY = min(h, max(maxY, start[1] + 2));
        maxY = min(h, max(maxY, end[1] + 2));
        return new int[] {minX, minY, maxX, maxY};
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

        private boolean isInside(int[] boundingRect) {
            return x >= boundingRect[0] && y >= boundingRect[1] && 
                       x <= boundingRect[2] && y <= boundingRect[3];
        }
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + this.x;
            hash = 53 * hash + this.y;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            final TimedIntVec2 other = (TimedIntVec2) obj; // safe cast
            if (this.x != other.x) return false;
            return this.y == other.y;
        }
        
    }
    

}
