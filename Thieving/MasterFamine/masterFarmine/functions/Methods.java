package masterFamine.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Cakemix
 */
public class Methods {
	
	//@formatter:off
	/**
	 * @author Coma
	 * @author Cakemix
	 * @param id Item ID.
	 * @return price
	 */
	//@formatter:on
	
	public static int getPrice(int id) throws IOException {
		URL url = new URL("http://open.tip.it/json/ge_single_item?item=" + id);
		URLConnection con = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));

		String line = "";
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			line += inputLine;
		}

		in.close(); // Remember to close it kids, cakemix loves memory.

		if (!line.contains("mark_price"))
			return -1;

		line = line.substring(line.indexOf("mark_price\":\"")
				+ "mark_price\":\"".length());
		line = line.substring(0, line.indexOf("\""));

		return Integer.parseInt(line);
	}

}
