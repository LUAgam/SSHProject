package ao;

import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestTmp {
	/**
	 * @params args
	 * @return void
	 */
	public static void main(String[] args) throws Exception {

		// 运行该代码前需要生成证书文件放到jre的security目录下，具体操作参照
		// http://blog.csdn.net/faye0412/article/details/6883879/

		// 站点库代码参照：https://kyfw.12306.cn/otn/resources/js/framework/station_name.js

		String trainDate = "2018-01-06";
		String fromStation = "SHH";
		String toStation = "DKH";
		getHttp(trainDate, fromStation, toStation);
	}

	/**
	 * @param trainDate
	 *            乘车日期
	 * @param fromStation
	 *            起点站代码
	 * @param toStation
	 *            终点站代码
	 * @return void 无
	 * @author liuyong
	 * @return 
	 * @serialData 2016年11月30日12:55:58
	 */
	public static JSONArray getHttp(String trainDate, String fromStation, String toStation) throws Exception {

		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		URL reqURL = new URL("https://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=" + trainDate
				+ "&leftTicketDTO.from_station=" + fromStation + "&leftTicketDTO.to_station=" + toStation
				+ "&purpose_codes=ADULT"); //
		HttpsURLConnection httpsConn = (HttpsURLConnection) reqURL.openConnection();
		InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());

		String jsonstring = "";

		int respInt = insr.read();
		while (respInt != -1) {

			jsonstring += String.valueOf((char) respInt);
			// System.out.print(((char) respInt));
			respInt = insr.read();

		}
		jsonObject = JSONObject.fromObject(jsonstring);
		// System.out.println(formatJson(jsonstring));

		// System.out.println(jsonObject.get("data"));
		jsonArray = JSONArray.fromObject(jsonObject.get("data"));
		

		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject tmpJsonBoj = jsonArray.getJSONObject(i);
			String flag = tmpJsonBoj.getString("flag");
			JSONObject jsonObject2 = tmpJsonBoj.getJSONObject("map");
			JSONArray jsonArray2 = tmpJsonBoj.getJSONArray("result");
			System.err.println("------------" + jsonObject2.toString()  + flag.toString() +  "-----------------");
			for (Object object : jsonArray2) {
				String[] split = object.toString().split("\\|");
				for (String string : split) {
					System.err.println(string);
				}
				System.err.println("----------------------------------------------------------");
			}
		}
		return jsonArray;
	}

	/**
	 * 格式化json字符串，参照别人的代码，暂时没有用到
	 * 
	 * @param jsonStr
	 * @return
	 * @author
	 * @Date
	 */
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}

	/**
	 * 添加space
	 * 
	 * @param
	 * @param
	 * @author
	 * @Date
	 */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}

}
