package horsemoves.controller;

import horsemoves.service.MinimumHorseMoves;

/**
 *
 * @author Texhnolyze
 */
public interface HorseMovesController {
    
    static final String WIDTH_HEIGHT_FORMAT = "[1-9]([0-9])*";
    static final String START_END_FORMAT = "[A-Z]+[1-9]([0-9])*";
    
    default String getUsage() {
        return "USAGE PARAMS: \n" + 
               "width=" + WIDTH_HEIGHT_FORMAT + "\n" +
               "height=" + WIDTH_HEIGHT_FORMAT + "\n" + 
               "start=" + START_END_FORMAT + "\n" + 
               "end=" + START_END_FORMAT + "\n" +
               "start and end must be within boundaries [width, height].\n" + 
               "max dimension (width * height) = " + MinimumHorseMoves.MAX_DIMENSION;
    }
    
}
