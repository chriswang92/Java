package json;

import java.io.*;
import java.net.URL;
import java.util.*;
import org.json.*;

public class FileOperator {

	/** Run this main function it will produce all the invalid customers with their 
	 * invalid fields to a local json file from the given json APIs
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		URL jsonFile = new URL("https://backend-challenge-winter-2017.herokuapp.com/customers.json");
		sb= new StringBuffer();
		String outputJson = "src/json/outputJson.json"; 
		setTotalPageAndPerPage(jsonFile);
		//out.println(total);
		factMaxPage = (int)(Math.floor((double)total/(double)per_page));
		for(int i=1;i<=factMaxPage;i++){
			subPageTotal = subPagePre + i; 
			url = new URL(subPageTotal);
			validationParse(url);
			validateCustomers(url);
			
		}
		outputJson(sb,outputJson);
	}

	public static PrintStream out = new PrintStream(System.out);
	static StringBuffer sb = null;
	static String name = null;
	static Boolean required = false; // defaults to false.
	static String type = null;
	static Integer minLength = null;
	static Integer maxLength = null;
	static Integer total = null;
	static Integer per_page=null;
	//static Integer curr_page=null;
	static Integer factMaxPage = null;
	static URL url=null;
	static String subPageTotal=null;
	static String subPagePre="https://backend-challenge-winter-2017.herokuapp.com/customers.json?page=";
	public static JSONObject outObj = new JSONObject();
	
	/* a map stores <k,v> pairs which keys are the name of validationObject and the values are 
	the validationObjects themselves */
	private static Map<String, ValidationObject> map = new HashMap<String, ValidationObject>();
	
	/* a map stores <k,v> pairs which keys are the id of customers and the values are the invalid
	 fields for the each associated customer */
	private static Map<Integer,List<String>> invalidCustomers = new HashMap<Integer,List<String>>();
	
	/* a list stores string values that are invalid fields of a customer */
	private static List<String> invalidFields;
	
	/**
	 * Output the contents in given stringbuffer to a local json file named outputJson
	 * @param sb
	 * @param outputJson
	 * @throws IOException
	 */
	public static void outputJson(StringBuffer sb,String outputJson) throws IOException{
		sb.append("{\n  \"invalid_customers\": [\n    ");
		Set<Integer> set = invalidCustomers.keySet();
		int size = set.size();
		int i = 0;
		for(Integer id : invalidCustomers.keySet()){
			i++;
			//out.println(i);
			List<String> value = invalidCustomers.get(id);
			if(!value.isEmpty()){
				StringBuffer valueSB = new StringBuffer();
				valueSB.append("[");
				int j=0;
				for(String s : value){
					j++;
					valueSB.append("\""+s+"\"");
					if(j<value.size()){
						valueSB.append(", ");
					}
				}
				valueSB.append("]");
				sb.append("{ \"id\": "+id+", \"invalid_fields\": "+valueSB+" }");
				if(i<size-1){
					sb.append(",\n    ");
				}
			}
			
		}
		sb.append("\n  ]\n}");
		out.print(sb);
		
		File file = new File(outputJson);
		Writer write = new FileWriter(file);  
        write.write(sb.toString());  
        write.flush();  
        write.close(); 
	}
	
	/**
	 * reset the static values(name,required,type,minLength,maxLength) for create new ValidationObject
	 */
	public static void reset() {
		name = null;
		required = false; // defaults to false.
		type = null;
		minLength = null;
		maxLength = null;
	}
	
	/**
	 *	Catch the data of total_page and per_page from "pagination" jsonObject in the given json,
	 * and store them in the static values in this class
	 * @param json
	 */
	public static void setTotalPageAndPerPage(URL json) {
		// 读取原始json文件并进行操作和输出
		JSONObject jsonObject = null;
		try {
			// JsonReader jsonReader = Json.createReader( new
			// FileReader("src/json/customers.json"));
			BufferedReader br = new BufferedReader(new InputStreamReader(json.openStream()));// 读取原始json文件
			String s = null, ws = null;
			while ((s = br.readLine()) != null) {
				// System.out.println(s);
				try {
					jsonObject = new JSONObject(s);// 创建一个包含原始json串的json对象
					JSONObject pagination = jsonObject.getJSONObject("pagination");
					//curr_page=pagination.getInt("current_page");
					per_page=pagination.getInt("per_page");
					total=pagination.getInt("total");
				}
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			br.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		//return jsonObject;
		
	}
		
	/**
	 * Parse the validations in the given json to several validateObjects,
	 * and store them in a map
	 * @param json
	 */
	public static void validationParse(URL json){
		try {  
			   //JsonReader jsonReader = Json.createReader( new FileReader("src/json/customers.json"));
			  BufferedReader br = new BufferedReader(new InputStreamReader(json.openStream()));// 读取原始json文件  
			  String s = null, ws = null;  
			  while ((s = br.readLine()) != null) {  
			       // System.out.println(s);  
			    try {  
					JSONObject jsonObject = new JSONObject(s);
					JSONArray validationArray = jsonObject
							.getJSONArray("validations");
					
					for (int i = 0; i < validationArray.length(); i++) {
						reset(); // defaults to false.
						JSONObject validationData = validationArray
								.getJSONObject(i);
						//out.println(validationData);
						JSONObject nameObj = null;
						String key = null;
						Iterator keyInterator = validationData.keys();

						key = keyInterator.next().toString();
						nameObj = validationData.getJSONObject(key);
						//out.println("key: " + key + " ,obj content:" + nameObj);
						JSONObject subObj = validationData.getJSONObject(key);
						//out.println("subObj:  " + subObj);
						Iterator subKeyInterator = subObj.keys();
						String subKey = null;
						while (subKeyInterator.hasNext()) {
							subKey = subKeyInterator.next().toString();
							//out.println("Key:[" + key + "]'s subKey : " + subKey);
							if (subKey.equals("length")) {
								//out.println("found length");
								JSONObject lengthObj = subObj.getJSONObject(subKey);
								//out.println("lengthObj: " + lengthObj);
								if (lengthObj.has("min")) {
									//out.println("found minlength: " + lengthObj.get("min"));
									minLength = (Integer) lengthObj.get("min");
									//out.println("set minlength :" + minLength);
								}
								if (lengthObj.has("max")) {
									//out.println("found maxlength: " + lengthObj.get("max"));
									maxLength = (Integer) lengthObj.get("max");
									//out.println("set maxlength :" + maxLength);
								}
							}
							if (subKey.equals("required")) {
								//out.println("found required");
								required = subObj.getBoolean("required");
								//out.println("set required: " + required);
							}
							if (subKey.equals("type")) {
								type = subObj.getString("type");
								//out.println("set type: " + type);
							}
							name = key;

						}
						ValidationObject vo = new ValidationObject(name,
								required, type, minLength, maxLength);
						map.put(name, vo);
						//out.println("-----------" + vo.toString());

						// ValidationObject validationObject = new
						// validationObject(name,required,)
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			  }
					// ws = dataJson.toString();
					// System.out.println(ws);
				
			    
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

	/**
	 * Validate customers in the given json
	 * @param json
	 */
	public static void validateCustomers(URL json){
	   try {  
       	//JsonReader jsonReader = Json.createReader( new FileReader("src/json/customers.json"));
           BufferedReader br = new BufferedReader(new InputStreamReader(json.openStream()));// 读取原始json文件  
           String s = null, ws = null;  
           while ((s = br.readLine()) != null) {  
               // System.out.println(s);  
               try {  
                   JSONObject jsonObject = new JSONObject(s);// 创建一个包含原始json串的json对象 
                   JSONArray customerArray = jsonObject.getJSONArray("customers");
                   for(int i=0;i<customerArray.length();i++){
                	   JSONObject customerObj = customerArray
								.getJSONObject(i);
                	   Integer id = (Integer) customerObj.get("id");
                	   // out.println("customer id:"+customerObj.get("id")+"-----"+customerObj);
                	   Iterator customerObjKeys = customerObj.keys();
                	   invalidFields = new ArrayList<String>();
                	   while(customerObjKeys.hasNext()){
                		   String key = customerObjKeys.next().toString();
                		   String value = customerObj.get(key).toString();
                		   if(map.containsKey(key)){
                			   // out.println("key: "+key +" , value: "+value);
                			   // out.println("map contains key:"+key);
                			   ValidationObject vo = map.get(key);
                			  
                			   //out.println("validation for key:"+key+", is:"+vo.toString());
                			   boolean isInvalid = validate(vo,key,value);
                			   if(isInvalid){
                				  invalidFields.add(key.toString());
                			   }
                			}else{
                				//out.println("no validation for "+key);
                			}
                		   
                		    }
                 	   invalidCustomers.put(id, invalidFields);
                   }
               }catch(Exception e){
            	   e.printStackTrace();
               }
           }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }
	
	/**
	 *  Validate the given key by compare its value with the pre-setted validation 
	 *  values which stored in the validationObject
	 * @param vo
	 * @param key
	 * @param value
	 * @return
	 */
	static boolean validate(ValidationObject vo,String key,String value){
			boolean isInvalid = false;
			// validate length
			//out.println("start validating key: "+key+" , value: "+value);
				if(vo.getLength()!=null){
					
					Integer minLength = vo.getLength().get("minLength");
					Integer maxLength = vo.getLength().get("maxLength");
					 if(minLength!=null){
						  if(value.length() < minLength){
							  isInvalid = true;
						  }
					   }
					 if(maxLength!=null){
						 if(value.length() > maxLength){
							  isInvalid = true;
						  }
					 }
					// out.println("have validated length of "+ key + " , result: "+ isInvalid);
				}
		 //validate type
			   if(vo.getType()!=null){
					
				   if(vo.getType().equals("boolean")){
					   //out.println(" type of "+ key + " is boolean");
					   if(!value.equals("true")&&!value.equals("false")){
						   isInvalid = true;
					   }
				   }
				   if(vo.getType().equals("number")){
					   //out.println(" type of "+ key + " is number" +" before judge , isInvalid? "+isInvalid);
					   if(!value.equals("null")){
						   isInvalid = !isNumeric(value);
					   }else{
						  // out.println("value is null");
					   }
				   }
				   if(vo.getType().equals("string")){
					   //out.println(" type of "+ key + " is string");
				   }
				  // out.println("have validated type of "+ key + " , result: "+ isInvalid);
			   }
			//validate required
			   if(vo.getRequired()==true && value.equals("null")){
				   isInvalid = true;
				   //out.println("have validated required of "+ key + " , result: "+ isInvalid);
			   }
			   //out.println("finished validated  of key: "+ key + " , result: "+ isInvalid);
		
		
		   return isInvalid;
	}
	
	/**
	 * check if the given string is a number
	 * @param str
	 * @return true if it is ,  otherwise false
	 */
	public static boolean isNumeric (String str) {
	    for (int i = str.length()-1; i >= 0;i--) {
	          if (!Character.isDigit(str.charAt(i))) {
	                return false;
	          }
	    }
	    return true;
	}
}
