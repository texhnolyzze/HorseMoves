package horsemoves.controller.rest;

import horsemoves.controller.HorseMovesController;
import horsemoves.service.MinimumHorseMoves;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Texhnolyze
 */

@RestController
@RequestMapping("/hourse/rest")
public class HorseMovesRestController implements HorseMovesController {
    
    @GetMapping("/count")
    public ResponseEntity<String> getMinimumHorseMoves(@RequestParam("width") int width, 
                                                       @RequestParam("height") int height,
                                                       @RequestParam("start") String start,
                                                       @RequestParam("end") String end) {
        MinimumHorseMoves moves = new MinimumHorseMoves(width, height, start, end);
        if (!moves.isValid()) {
            return ResponseEntity.badRequest().body(moves.getValidationInfo() + "\n\n" + getUsage());
        }
        return ResponseEntity.ok(Integer.toString(moves.getMinimumHorseMoves()));
    }
    
}
