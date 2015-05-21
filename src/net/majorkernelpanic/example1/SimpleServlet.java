package net.majorkernelpanic.example1;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class SimpleServlet extends HttpServlet
{
     
    private static final long serialVersionUID = -621262173529602L;
     
    /**
     * This method is called when the user enters the URL in the
     * browser. 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {    
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out=resp.getWriter();
        out.println("<html>" +
                "<title>Simple Form</title>" +
                "<body>" +
                "<h2>Knuckle Dragger Design Video Tool Suite</h2>" +
                "<form method='post'>" +
                "Name: <input type='text' name='user_name'/><br/>" +
                "<input type='submit' value='Go'/>" +
                "</form>" +
                "</body>" +
                "</html>");
    }
     
    /**
     * This method is called when the 'Go' button is pressed
     */   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {    
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out=resp.getWriter();
        String username=req.getParameter("user_name");
         
        if(username==null || username.equals(""))
        {
            username="<NO_NAME_SPECIFIED>";
        }
         
        out.println("<html>" +
                "<title>Welcome</title>" +
                "<body>" +
                "<h2>Knuckle Dragger Design Video Tool Suite</h2>" +
                "Welcome " + username + "<br/>" +
                
                //VLC Media Player
                "<OBJECT classid=\"clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921\"" +
                "codebase=\"http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab\"" +
                "width=\"640\" height=\"480\" id=\"vlc\" events=\"True\">" +
                "<param name=\"Src\" value=\"rtsp://cameraipaddress\" />" +
                "<param name=\"ShowDisplay\" value=\"True\" />" +
                "<param name=\"AutoLoop\" value=\"False\" />" +
                "<param name=\"AutoPlay\" value=\"True\" />" +
                "<embed id=\"vlcEmb\"  type=\"application/x-google-vlc-plugin\" version=\"VideoLAN.VLCPlugin.2\" autoplay=\"yes\" loop=\"no\" width=\"640\" height=\"480\"" +
                "target=\"rtsp://192.168.2.88:1234\" ></embed>" +
                "</OBJECT>" +

                
                "</body>" +
                "</html>");
    }
     
}