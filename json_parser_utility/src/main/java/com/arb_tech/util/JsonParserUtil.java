package com.arb_tech.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.arb_tech.vo.ProjectProductVO;
import com.arb_tech.vo.ProjectVO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parse a JSON file & extract a value based on the inputs given
 */
public class JsonParserUtil {

	public static final String CHARSET_ENCODING_ISO_8859_1 = "ISO-8859-1";
	
	public static void main(String[] args) {
		// This is the JSON data that will be read from a JSON file stored in EFS
		String jsonString = 
				"{\r\n" + 
				"   \"projects\":[\r\n" + 
				"      {\r\n" + 
				"         \"name_token\":\"Metro_Happy_QA1\",\r\n" + 
				"         \"product_mapping\":{\r\n" + 
				"            \"HappyPath_L00\":\"HappyPath-L0\",\r\n" + 
				"            \"HappyPath_L001\":\"HappyPath-L01\"\r\n" + 
				"         }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name_token\":\"Metro_Happy_QA2\",\r\n" + 
				"         \"product_mapping\":{\r\n" + 
				"            \"HappyPath_L002\":\"HappyPath-L02\",\r\n" + 
				"            \"HappyPath_L003\":\"HappyPath-L03\"\r\n" + 
				"         }\r\n" + 
				"      }\r\n" + 
				"   ]\r\n" + 
				"}";	
		
		// The 2nd & 3rd inputs are static & hard-coded here, but they will be dynamic runtime inputs
		String productValue = jsonParser(jsonString, "Metro_Happy_QA2", "HappyPath_L002");
		
		if(null != productValue && !productValue.isEmpty()) {
			System.out.println("ProjectKey: Metro_Happy_QA1\tProductKey: HappyPath_L00");
			System.out.println("Result Product Key Token: "+productValue);
		} else {
			System.out.println("Requested value for the given project & product token Not found "
					+ "in the given Project JSON file.");
		}
	}
	
	/**
	 * @param projectFile, the JSON file to be parsed & from which value is to be extracted
	 * @param projectToken, the search token to search from the project array of objects
	 * @param productToken, the search key token, of which value is to be returned as final resultant
	 * @return return the project's -> product's key token's "value" as result 
	 */
	public static String jsonParser(String jsonString, String projectToken, String productKeyToken) {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		ProjectProductVO projectProductVO = null;
		if (null != projectToken && null != productKeyToken && !projectToken.isEmpty() && !productKeyToken.isEmpty()) {
			try {
				projectProductVO = mapper.readValue(new String(jsonString.getBytes(), CHARSET_ENCODING_ISO_8859_1),
						ProjectProductVO.class);
				List<ProjectVO> projectVoList = projectProductVO.getProjects();
				if (null != projectVoList && !projectVoList.isEmpty()) {
					for (ProjectVO projectVO : projectVoList) {
						if (null != projectVO && projectVO.getName_token().equals(projectToken)) {
							if(null != projectVO.getProduct_mapping() && !projectVO.getProduct_mapping().isEmpty()) {
								for (Map.Entry<String, String> entry : projectVO.getProduct_mapping().entrySet()) {
									if (entry.getKey().equalsIgnoreCase(productKeyToken))
									{	result = entry.getValue(); }
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (null == projectToken || projectToken.isEmpty())
				System.out.println("The Project token is Empty/NULL value!");
			else
				System.out.println("The PRODUCT token is Empty/NULL value!");
		}
		return result;
	}
}
