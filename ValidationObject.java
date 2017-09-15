package json;

import java.util.*;

//singleton 
public class ValidationObject {
	
	private String name;
	private Boolean required;
	private String type;
	private Map<String,Integer> length;
	
	public ValidationObject(){
		this.name = null;
		this.required = null;
		this.type = null;
		this.length = new HashMap<String,Integer>();
				
	}
	public ValidationObject(String name,Boolean required,String type,Integer minLength,Integer maxLength){
		
		this.name = name;
		this.required = required;
		this.type = type;
		this.length  = new HashMap<String,Integer>();
		this.length.put("minLength", minLength);
		this.length.put("maxLength", maxLength);
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Map<String, Integer> getLength() {
		return length;
	}


	public void setLength(Map<String, Integer> length) {
		this.length = length;
	}


	
	public String toString(){
		return "name: "+this.getName()+" ,required:"+this.getRequired()+", type:"+this.getType()+", minlength:"+this.getLength().get("minLength")+",maxlength:"+this.getLength().get("maxLength");
	}
	
	public static void main(String[] args){
		List<String> types=new ArrayList<String>();
		types.add("boolean");
		types.add("string");
		types.add("int");
		String type="boolean";
		ValidationObject vo = new ValidationObject("chris",true,type,5,20);
		System.out.println(vo.toString());
	}
}
