package com.hph.demo.annotationBeanDefinitionReader.scanner;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Import(TestImportClass.class)
public class ImportTest {

	public ImportTest() {

		System.out.println("2222222222222222222");
	}
}
