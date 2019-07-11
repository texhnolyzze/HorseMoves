package horsemoves.service;

/**
 *
 * @author Texhnolyze
 */
public final class MinimumHorseMoves {

    public static final int MAX_DIMENSION = 100000; // width * height
    
    private int computationResult = -1;
    
    public MinimumHorseMoves(int w, int h, String start, String end) {
        if (w <= 0) {
            validationInfo = "Width <= 0.";
            return;
        }
        if (h <= 0) {
            validationInfo = "Height <= 0.";
            return;
        }
        if ((long) w * (long) h > MAX_DIMENSION) {
            validationInfo = "Dimension (width * height) is too big. Max: " + MAX_DIMENSION;
            return;
        }
        int[] startColumnLine = getColumnLine(start);
        if (!isValid(w, h, startColumnLine)) {
            validationInfo = "Start point invalid.";
            return;
        }
        int[] endColumnLine = getColumnLine(end);
        if (!isValid(w, h, endColumnLine)) {
            validationInfo = "End point invalid.";
            return;
        }
        computationResult = MinimumHorseMovesCalculator.compute(w, h, startColumnLine, endColumnLine);
    }
    
    private boolean isValid(int w, int h, int[] columnLineCoords) {
        return columnLineCoords != null && columnLineCoords[0] <= w && 
               columnLineCoords[1] >= 1 && columnLineCoords[1] <= h;
    }
    
    private String validationInfo = "Valid";
    public boolean isValid() {
        return computationResult != -1;
    }
    
    public String getValidationInfo() {
        return validationInfo;
    }
    
    public int getMinimumHorseMoves() {
        if (!isValid())
            throw new IllegalStateException("Invalid input data.");
        return computationResult;
    }
    
    private static int[] getColumnLine(String s) {
        if (s == null)
            return null;
        int column = 0; 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 'A' || c > 'Z') {
                if (c >= '1' && c <= '9') {
                    try {
                        int line = Integer.parseInt(s.substring(i));
                        return new int[] {column, line};
                    } catch (NumberFormatException ex) {break;}
                } else
                    break;
            }
            column = column * 26 + (s.charAt(i) - 'A' + 1);
        }
        return null;
    }
    
}
