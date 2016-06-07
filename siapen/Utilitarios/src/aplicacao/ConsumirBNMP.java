package aplicacao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumirBNMP {

	public static void main(String[] args) {
		String line;
		StringBuffer jsonString = new StringBuffer();
		try {

			URL url = new URL("http://www.cnj.jus.br/bnmp/rest/pesquisar");

			// escape the double quotes in json string
			//String payload = "{\"jsonrpc\":\"2.0\",\"method\":\"changeDetail\",\"params\":[{\"id\":11376}],\"id\":2}";
			String payload = "{\"criterio\":{\"orgaoJulgador\":{},\"orgaoJTR\":{},\"parte\":{\"documentos\":[{\"identificacao\":null}]},\"nomesParte\":\"Eduardo Mendes Da Silva\"},\"paginador\":{},\"fonetica\":\"false\",\"ordenacao\":{\"porNome\":false,\"porData\":false}}";

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write(payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		System.out.println(jsonString.toString());

	}

}