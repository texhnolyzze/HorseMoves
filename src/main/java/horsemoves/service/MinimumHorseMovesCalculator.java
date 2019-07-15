package horsemoves.service;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Texhnolyze
 */
final class MinimumHorseMovesCalculator {
    
	private static int to2DArrayHash(int columnIndex, int lineIndex, int columnsNum) {
        return columnIndex + lineIndex * columnsNum;
    }
	
    private static final int[] DX = {-2, -1, 1, 2, -2, -1, 1, 2};
    private static final int[] DY = {-1, -2, -2, -1, 1, 2, 2, 1};
    
//                                          x, y        x y
    static int compute(int w, int h, int[] start, int[] end) {
        int[] boundingRect = calcBoundingRect(w, h, start, end); // minX, minY, maxX, maxY
		int minX = boundingRect[0];
		int minY = boundingRect[1];
		int newWidth = boundingRect[2] - minX + 1;
		int newHeight = boundingRect[3] - minY + 1;
        Queue<TimedIntVec2> q = new LinkedList<>();
        boolean[] visited = new boolean[newWidth * newHeight];
		visited[to2DArrayHash(start[0] - minX, start[1] - minY, newWidth)] = true;
        q.add(new TimedIntVec2(start, 0));
        TimedIntVec2 temp = new TimedIntVec2();
        while (!q.isEmpty()) {
            TimedIntVec2 v = q.poll();
            if (v.x == end[0] && v.y == end[1]) 
                return v.time;
            TimedIntVec2 adj;
            for (int i = 0; i < 8; i++) {  
                temp.x = v.x + DX[i];
                temp.y = v.y + DY[i];
				int arrayHash = to2DArrayHash(temp.x - minX, temp.y - minY, newWidth);
                if (temp.isInside(boundingRect) && !visited[arrayHash]) {  
                    adj = new TimedIntVec2(temp.x, temp.y, v.time + 1);
                    visited[arrayHash] = true;
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
		TimedIntVec2() {}
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
        
    }

}
