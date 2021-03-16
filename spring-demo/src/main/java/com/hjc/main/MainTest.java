package com.hjc.main;

import com.hjc.controller.TestController;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

public class MainTest {

	public static void main(String[] args) throws IOException {
//		AnnotationConfigApplicationContext ac =
//				new AnnotationConfigApplicationContext();
//
//		ac.register(Appconfig.class);
//		ac.refresh();
//		System.out.println(ac.getBean(Appconfig.class));
		//Test test = ac.getBean(Test.class);
		//test.dosm();

		// 得到 SimpleMetadataReaderFactory 实例，最终调用的是 SimpleAnnotationMetadataReadingVisitor 来读取
		SimpleMetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory();
		MetadataReader metadataReader = readerFactory.getMetadataReader(TestController.class.getName());
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		MultiValueMap<String, Object> allAnnotationAttributes = annotationMetadata.getAllAnnotationAttributes(RequestMapping.class.getName());
		System.out.println(allAnnotationAttributes);
		// AnnotationMetadata 提供了许多的操作，重点关注注解相关的
		Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
		System.out.println("-------------");
		annotationTypes.forEach(type -> System.out.println(type));
		System.out.println("-------------");
		boolean exist2 = annotationMetadata.hasAnnotation(Component.class.getName());
		System.out.println(exist2);

		//如果需要处理属性覆盖，就使用AnnotatedElementUtils，
		// 如果不需要，就使用AnnotationUtils
		Annotation annotatedElementUtils = AnnotatedElementUtils.getMergedAnnotation(TestController.class, Component.class);
		Annotation annotation = AnnotationUtils.getAnnotation(TestController.class, Component.class);
		if (null == annotation) {
			System.out.println("注解不存在！");
			return;
		}
		System.out.println("annotation: " + annotation);

		// 获取 AnnotationAttributes
		AnnotationAttributes annotationAttributes
				= AnnotationUtils.getAnnotationAttributes(TestController.class, annotation);
		System.out.println("AnnotationAttributes: " + annotationAttributes);

		// 获取 annotationAttributeMap
		Map<String, Object> annotationAttributeMap = AnnotationUtils.getAnnotationAttributes(annotation);
		System.out.println("annotationAttributeMap: " + annotationAttributeMap);

		// 获取value的值
		Object value = AnnotationUtils.getValue(annotation, "value");
		System.out.println("value: " + value);
	}
}
