package io.aomen.guo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.aomen.guo.util.HttpUtil;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/train")
public class TrainController {

	/**
	 * 获取火车票数据
	
	* Title: getData
	
	* Description: 
	
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getTrainData",method = RequestMethod.GET)
	public JSONArray getData() throws Exception {
		String trainDate = "2018-01-06";
		String fromStation = "SHH";
		String toStation = "DKH";
		JSONArray jsonArray = HttpUtil.getHttp(trainDate, fromStation, toStation);
		return jsonArray;
	}

}
