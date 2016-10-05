package me.oso.yattay.server.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 5 oct. 2016
 */
public class ConfigParser {

	private Map<String, String> values;
	
	public ConfigParser(String file) {
		this.values = new HashMap<String, String>();
		this.parse(file);
	}
	
	private void parse(String file) {
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) != null) {
            	String[] kv = line.split(":");
            	if(kv.length == 2) {
            		values.put(kv[0], kv[1].trim());
            	} else {
                    br.close();
            		throw new RuntimeException("Cannot parse config file");
            	}
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public String getAttribute(String name) {
		return this.values.get(name);
	}
	
}
