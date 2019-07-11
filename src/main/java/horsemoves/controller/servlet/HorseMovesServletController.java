package horsemoves.controller.servlet;

import horsemoves.controller.HorseMovesController;
import horsemoves.service.MinimumHorseMoves;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Texhnolyze
 */
public class HorseMovesServletController extends HttpServlet implements HorseMovesController {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String w, h;
        String s, e;
        w = getParam(req, resp, "width", WIDTH_HEIGHT_FORMAT);
        if (w != null) {
            h = getParam(req, resp, "height", WIDTH_HEIGHT_FORMAT);
            if (h != null) {
                s = getParam(req, resp, "start", START_END_FORMAT);
                if (s != null) {
                    e = getParam(req, resp, "end", START_END_FORMAT);
                    if (e != null) {
                        MinimumHorseMoves mhm = new MinimumHorseMoves(Integer.parseInt(w), Integer.parseInt(h), s, e);
                        if (mhm.isValid()) {
                            resp.setStatus(HttpServletResponse.SC_OK);
                            resp.getOutputStream().write(Integer.toString(mhm.getMinimumHorseMoves()).getBytes(Charset.forName("UTF-8")));
                        } else {
                            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            resp.getOutputStream().write((mhm.getValidationInfo() + "\n\n" + getUsage()).getBytes(Charset.forName("UTF-8")));
                        }
                    }
                }
            }
        }
    }
    
    private String getParam(HttpServletRequest req, HttpServletResponse resp, String paramName, String paramFormat) throws IOException {
        String temp = req.getParameter(paramName);
        if (temp != null) {
            if (!temp.matches(paramFormat)) 
                sendInvalidParam(paramName, resp);
            else 
                return temp;
        } else
            sendParamNotSet(resp);
        return null;
    }
    
    private void sendInvalidParam(String param, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getOutputStream().write(("Invalid " + param + ".\n" + getUsage()).getBytes(Charset.forName("UTF-8")));
    }
    
    private void sendParamNotSet(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getOutputStream().write(("Please set all required params first.\n" + getUsage()).getBytes(Charset.forName("UTF-8")));
    }
    
}
