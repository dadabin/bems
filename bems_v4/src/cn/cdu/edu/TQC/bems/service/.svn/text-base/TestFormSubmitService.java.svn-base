package cn.cdu.edu.TQC.bems.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 * 
 * @author 秦凡鹏
 * @description:本类用来演示怎么用Restful接口处理前台ajax方式提交的表达数据，
 * 对应前台dataCollection.html中的id为rate_water_form的水表频率设置表达。
 *
 */

@Path("/TestFormSubmitService")
public class TestFormSubmitService
{
	@POST
	@Path("rate_water_form")
	@Produces(MediaType.TEXT_PLAIN)
	public String handle_rate_water_form(@FormParam("rate_water") String rate_water)
	{
		System.out.println("the mothed invoked here ============== + " +rate_water);
		return rate_water;
	}
	/*=========================================================*/
	@POST
	@Path("water_basic_form")
	@Produces(MediaType.TEXT_PLAIN)
	public String handle_water_basic_form(
			@FormParam("water_number1") String water_number1,
			@FormParam("water_type1") String water_type1,
			@FormParam("location1") String location1,
			
			@FormParam("water_number2") String water_number2,
			@FormParam("water_type2") String water_type2,
			@FormParam("location2") String location2,
			
			@FormParam("water_number3") String water_number3,
			@FormParam("water_type3") String water_type3,
			@FormParam("location3") String location3,
			
			@FormParam("water_number4") String water_number4,
			@FormParam("water_type4") String water_type4,
			@FormParam("location4") String location4
			)
	
	{
		
		System.out.println("water1: "+water_number1 +" ,"+water_type1+" ,"+location1);
		System.out.println("water2: "+water_number2 +" ,"+water_type2+" ,"+location2);
		System.out.println("water3: "+water_number3 +" ,"+water_type3+" ,"+location3);
		System.out.println("water4: "+water_number4 +" ,"+water_type4+" ,"+location4);
		String msg = "成功";
		return msg;
	}
	
//	String s ;
	@POST
	@Path("save_functionDivs")
//	@Consumes("application/json")
	@Produces(MediaType.TEXT_PLAIN)
	public String save_functionDivs(
			@Context HttpServletResponse servletResponse,
		    @Context HttpServletRequest servletRequest,
			@FormParam("str_detaile") String str_detaile)
	{
		
		servletRequest.getSession().setAttribute("str_detaile", str_detaile);
	
		return "成功";
	}
	
	@POST
	@Path("get_functionDivs")
//	@Consumes("application/json")
	@Produces("application/json") 
	public String get_functionDivs(
			@Context HttpServletResponse servletResponse,
		    @Context HttpServletRequest servletRequest
			)
	{
		String s = (String)servletRequest.getSession().getAttribute("str_detaile");
		System.out.println("get_functionDivs------- s:"+s);
		return s;
		
	}
	
	
}
