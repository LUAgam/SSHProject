package io.aomen.guo.web;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public abstract class BaseController {

	public static String fetchAllErrorMessages(BindingResult result) {
		String message = "";
		if (result.hasErrors()) {
			List<ObjectError> ls = result.getAllErrors();

			for (int i = 0; i < ls.size(); i++) {
				message += ls.get(i).getDefaultMessage();
				if (i != ls.size() - 1) {
					message += "<br/>";
				}
			}

		}
		return message;
	}

}
