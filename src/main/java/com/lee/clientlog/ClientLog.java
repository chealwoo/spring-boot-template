package com.lee.clientlog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

/**
 * Servlet implementation class ClientLog
 */
public class ClientLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	   protected final Log logger = LogFactory.getLog(getClass());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("data");

        try {
            JSONArray jsonArray = new JSONArray(text);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");

            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                // Try block for a given single JSONObject.
                try {
                    String sDate = sdf.format(new Date(Long.parseLong(j.getString("timestamp"))));
                    logger.debug(String.format("%s [%s] %s", sDate, j.getString("logger"), j.getString("message")));
                } catch (Exception err) {
                    logger.error("Error while processing json client log", err);;
                }
            }
        } catch (JSONException e) {
            logger.error("ERROR while creating json from " + text, e);
        } catch (Exception e) {
            logger.error("ERROR while creating json from " + text, e);
        }

	}

}
