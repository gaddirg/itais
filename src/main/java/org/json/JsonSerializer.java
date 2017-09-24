package org.json;

import java.util.List;
import org.itais.domain.Office;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonSerializer
{
   
    public static String getJsonAsString(List<Office> offices)
    {
	JSONObject obj = new JSONObject();
	JSONArray arr = new JSONArray();
	for(Office off : offices)
	{
	    obj.put("name", off.getName());
	    obj.put("invCount", off.getInventories().size());
	    arr.put(obj);
	    obj = new JSONObject();
	}
	return arr.toString();
    }

}