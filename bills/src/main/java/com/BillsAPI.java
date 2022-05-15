package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.Bill;


@WebServlet("/BillsAPI")
public class BillsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	Bill billObj = new Bill();
    /**
     * Default constructor. 
     */
    public BillsAPI() {
        // TODO Auto-generated constructor stub
    	
    	super();
    }

	
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
			
			
			String output =  billObj.insertBill(request.getParameter("lastReading"),
			request.getParameter("presentReading"),
			request.getParameter("units"),
			request.getParameter("amount"));
			response.getWriter().write(output);
			}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
			Map paras = getParasMap(request);
			String output = billObj.updateBill(paras.get("hidBillIDSave").toString(),
			paras.get("lastReading").toString(),
			paras.get("presentReading").toString(),
			paras.get("units").toString(),
			paras.get("amount").toString());
			response.getWriter().write(output);
			}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
			Map paras = getParasMap(request);
			String output = billObj.deleteBill(paras.get("billID").toString());
			response.getWriter().write(output);
			}
	
	
	
	private static Map getParasMap(HttpServletRequest request)
	{
	Map<String, String> map = new HashMap<String, String>();
	try
	{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
	scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	String[] params = queryString.split("&");
	for (String param : params)
	{
		String[] p = param.split("=");
		map.put(p[0], p[1]);
		}
		}
		catch (Exception e)
		{
		}
		return map;
		}
}
