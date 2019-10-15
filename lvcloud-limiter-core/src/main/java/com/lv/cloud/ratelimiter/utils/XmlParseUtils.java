package com.lv.cloud.ratelimiter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParseUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlParseUtils.class);
	
	@SuppressWarnings("unchecked")
	public static <T> T parse(String xmlName, Class<T> clazz){
		try {
			// 设置body
			Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
			
			Resource resource=new ClassPathResource(xmlName);
			File file = resource.getFile();
			if(file.exists()){
				return (T) unmarshaller.unmarshal(file);
			}
		} catch (Exception e) {
			LOGGER.error("XmlParseUtils.parse error,{}", e);
		}
		return null;
	}
}
