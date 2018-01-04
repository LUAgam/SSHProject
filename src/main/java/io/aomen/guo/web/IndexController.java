package io.aomen.guo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String do_get() {
	    
	    
		return "/account/index";
	}
}
