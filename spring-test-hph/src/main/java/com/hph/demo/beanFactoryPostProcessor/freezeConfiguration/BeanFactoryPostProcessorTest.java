package com.hph.demo.beanFactoryPostProcessor.freezeConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 测试 BeanFactoryPostProcessor 的扩展功能
 * @author hph
 */
@Component
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		AbstractBeanDefinition sb = (AbstractBeanDefinition)beanFactory.getBeanDefinition("abfpp");
		// 通过名称进行自动注入（通过方法名称获取需要注入的依赖，然后通过反射传给set方法参数）
		// sb.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		// 通过类型进行自动注入（通过set方法参数的类型进行注入，和方法名无关）
		// sb.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

		// 测试 beanDefinition 冻结之后，修改 beanDefinition 的属性是否会生效
		beanFactory.freezeConfiguration();
		// 当冻结后，按理来说设置SCOPE_PROTOTYPE不会生效，abfpp 最终会在 singletonObjects容器中，但是其实没在
		// （为啥？因为在创建bean之前，会进行一次merged，删除了mergedDefinitions中对应的mergedBeanDefinition，然后被修改的beanDefinition 被合并到mergedDefinitions）
		sb.setScope(AbstractBeanDefinition.SCOPE_PROTOTYPE);
	}
}
