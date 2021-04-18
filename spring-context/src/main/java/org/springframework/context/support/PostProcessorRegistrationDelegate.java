/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 *
 * @author Juergen Hoeller
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	/**
	 * 顾名思义，执行 BeanFactoryPostProcessor 所有的实现类
	 * 所有实现类 {
	 *     1、spring 内置的 -- 在这个方法之前它是被封装成了 bd，并且 put 到了 bdmap 中
	 *     2、程序员提供的
	 *     	1）通过扫描的
	 *     	2）通过api提供的
	 *     3、实现了 Ordered 接口的
	 * }
	 * 所有实现类的层级关系{
	 * 	1、直接是实现了 BeanFactoryPostProcessor
	 * 	2、实现了 BeanDefinitionRegistryPostProcessor
	 * }
	 * 注意：下面注释所说的 ‘程序员添加’ 和 ‘程序员自定义注册’ 是两钟方式
	 * @param beanFactory
	 * @param beanFactoryPostProcessors
	 */
	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		// Invoke BeanDefinitionRegistryPostProcessors first, if any.
		// 处理已经处理完成的 BeanFactoryPostProcessor 类名的集合
		Set<String> processedBeans = new HashSet<>();

		if (beanFactory instanceof BeanDefinitionRegistry) {

			// 99% 是进这里的
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

			// processedBeans == regularPostProcessors
			// 存储已经处理过的直接实现 BeanFactoryPostProcessor 的实现的具体对象的集合
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();

			// processedBeans == registryProcessors
			// 存储已经处理过的直接实现 BeanDefinitionPostProcessor 的实现的具体对象的集合
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

			// beanFactoryPostProcessors 只能是两种类型
			// 1、实现了 BeanFactoryPostProcessor 接口的
			// 2、实现了 BeanDefinitionRegistryPostProcessor 接口的
			// 先执行实现了 BeanDefinitionRegistryPostProcessor 接口的
			// 这里先执行程序员自己添加的
			// （beanFactoryPostProcessors是通过参数传过来的，也就是程序员通过BeanFactory 的 addBeanFactoryPostProcessor 方法进行添加的）
			// 为什么先执行程序员添加的？
			// （为什么先执行程序员添加的，而且是实现了BeanDefinitionRegistryPostProcessor 接口的类的postProcessBeanDefinitionRegistry方法？
			//（因为postProcessBeanDefinitionRegistry方法提供一个BeanDefinitionRegistry类型参数，这个参数可以手动注册bean，而不需要进行扫描进行注册））
			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					// 处理程序员手动添加的实现了 BeanDefinitionRegistryPostProcessor 接口的类的 postProcessBeanDefinitionRegistry 方法
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					// 为什么还要保存起来？
					//（因为后面还需要执行父类的postProcessBeanFactory方法）
					registryProcessors.add(registryProcessor);
				}
				else {
					// 如果 postProcessor 是直接实现了 BeanFactoryPostProcessors
					// 为什么实现了 BeanFactoryPostProcessors 接口的不直接执行，而是缓存起来？
					// 因为他们的执行时机不能错乱
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			// 存储的是当前需要执行的 BeanDefinitionRegistryPostProcessor 实现类的集合
			// 每次执行完会清除，防止重复执行
			// 1、直接是实现了 BeanFactoryPostProcessor
			// 2、实现了 BeanDefinitionRegistryPostProcessor
			// 上面两种 BeanFactoryPostProcessor 从源码级别来看它是先执行第二种

			// 正常情况下，这里符合条件的类只有一个，那就是ConfigurationClassPostProcessor，这个类是完成spring的扫描的
			// （为什么说是正常情况下？因为不考虑执行程序员手动添加的实现了 BeanDefinitionRegistryPostProcessor
			// 接口的类的postProcessBeanDefinitionRegistry方法的话，没有地方有对符合这种要求的类进行注册的了，
			// spring也只注册了ConfigurationClassPostProcessor一个）
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			// 从 bdmap 获取 beanDefinition 的 name
			// 从 bdmap 获取 beanDefinition 的 beanClass 类型为 BeanDefinitionRegistryPostProcessor
			// 注意：这里是拿不到程序员通过 addBeanFactoryPostProcessor 方法添加的bean的
			// 所以执行的是 spring 内置的 BeanDefinitionRegistryPostProcessor
			String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					processedBeans.add(ppName);
				}
			}
			// 通过order进行排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 程序员手动添加的 + spring 内置的，实现了BeanDefinitionRegistryPostProcessors接口的类型、
			registryProcessors.addAll(currentRegistryProcessors);
			// 执行 BeanDefinitionRegistryPostProcessor 实现类 的 postProcessBeanDefinition
			// BeanDefinitionRegistryPostProcessor 有很多种类的实现类
			// 这里执行的是 spring 内置的 ConfigurationClassPostProcessor 的 postProcessBeanDefinitionRegistry 方法
			// （因为 spring 内置实现 PriorityOrdered 接口的只有这一个）
			// 完成了 spring 的扫描（扫描的类转为beanDefinition）
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 清空currentRegistryProcessors集合里的数据
			currentRegistryProcessors.clear();

			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			// 再加上扫描出程序员自定义注册的 BeanDefinitionRegistryPostProcessor
			// （注意：这里的是程序员自定义的，并由spring进行注册的，和上面程序员添加的不同）
			// 为什么上面调用getBeanNamesForType拿不到程序员自定义的呢，而这里可以拿到？
			// (2个原因：1、因为上面注册后，在beanDefinitionMap容器中有，然后这里调用getBeanNamesForType就能拿到；
			// 	2、上面调用postProcessBeanDefinitionRegistry方法可能会手动注册一个bean，调用getBeanNamesForType保证拿全）
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				// 判断是否没被处理并且实现了Ordered接口
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
					// 因为这时 ppName所表示的类并没有被实例化，所以需要使用 get 方法进行实例化
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					processedBeans.add(ppName);
				}
			}
			// 更新Ordered进行排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 程序员手动添加的 + spring 内置的 + 程序员自定义注册的，实现了BeanDefinitionRegistryPostProcessors接口的类型、
			registryProcessors.addAll(currentRegistryProcessors);
			// 执行实现 BeanDefinitionRegistryPostProcessor 和 Ordered 接口的 postProcessBeanDefinitionRegistry 方法（程序员自定义注册的 + spring内置的）
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			currentRegistryProcessors.clear();

			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			// 这里为什么用while循坏呢？
			//（因为执行postProcessBeanDefinitionRegistry方法的时候可能会注册新的实现了BeanDefinitionRegistryPostProcessor接口的Bean）

			// 但是为什么上面执行postProcessBeanDefinitionRegistry方法的时候不使用while呢？
			// 因为上面的是有排序的，如果执行的中间新注册的优先级比已经执行了的bean搞的话，那么这就不符合spring的设计了，
			// 		所以spring如果有那种情况的话，会直接在下面进行执行，这也就解释了在实现了Ordered类里注册一个实现了PriorityOrdered类的bean，方法会在下面执行
			boolean reiterate = true;
			while (reiterate) {
				reiterate = false;
				// 获取spring内置 + 程序员自定义注册的实现了BeanDefinitionRegistryPostProcessor接口，并且没有实现PriorityOrdered接口或Ordered 接口的类
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				for (String ppName : postProcessorNames) {
					if (!processedBeans.contains(ppName)) {
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						processedBeans.add(ppName);
						reiterate = true;
					}
				}
				// 因为没有实现PriorityOrdered接口或Ordered 接口，所以这里按注册顺序进行排序
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				registryProcessors.addAll(currentRegistryProcessors);
				// 执行spring内置 + 程序员自定义注册的实现了BeanDefinitionRegistryPostProcessor接口，
				// 并且没有实现PriorityOrdered接口或Ordered 接口的类的 postProcessBeanDefinitionRegistry方法
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				currentRegistryProcessors.clear();
			}
			// 上面代码是执行 BeanFactoryPostProcess 的子类 BeanDefinitionRegistryPostProcessor 的所有实现类
			// 的 postProcessBeanDefinitionRegistry 方法
			// 先执行程序员通过 api 容器进行添加的
			// 然后执行 sprng 内置的 ----> ConfigurationClassPostProcessor 类，完成类的扫描与注册
			// 通过 ConfigurationClassPostProcessor 去完成扫描程序员通过注解、xml 提供的
			// BeanDefinitionRegistryPostProcessor 的所有实现类，并且先执行了 ordered 接口的，
			// 然后执行没有实现 Ordered 接口的

			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.

			// 执行直接实现了 BeanFactoryPostProcessor 接口的类对象
			// registryProcessors：存储的是已经处理完的 BeanDefinitionRegisterPostProcessor
			// 因为一个类一旦实现了 BeanDefinitionRegisterPostProcessor ，那么它一定是一个 BeanFactoryPostProcessor。
			// 需要执行 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法
			// 以下方法先执行实现了 BeanDefinitionRegisterPostProcessor 接口的类，
			// 再执行实现了 BeanFactoryPostProcessor 接口的类
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);

			// 执行程序员通过 api 添加的且实现 BeanFactoryPostProcessor 接口的类
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		}

		else {
			// Invoke factory processors registered with the context instance.
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		// 拿到所有 BeanFactoryPostProcessor 的名字，包括 spring 内置的和程序员自定义注册的
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 存储 BeanFactoryPostProcessor 实现了 PriorityOrdered 接口的对象
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();

		// 存储 BeanFactoryPostProcessor 实现了 PriorityOrdered 接口 bean 的名字
		List<String> orderedPostProcessorNames = new ArrayList<>();

		// 存储 BeanFactoryPostProcessor 没有实现 PriorityOrdered 接口 bean 的名字
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		for (String ppName : postProcessorNames) {
			if (processedBeans.contains(ppName)) {
				// skip - already processed in first phase above
			}
			else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				// 这里为什么不像上面一样直接添加bean呢？
				// （因为上面执行postProcessBeanFactory方法后可能会改变beanDefinition的属性，下面getBean的时候会进行merged，保证属性是最新的）
				orderedPostProcessorNames.add(ppName);
			}
			else {
				// 这里也是
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>();
		for (String postProcessorName : orderedPostProcessorNames) {
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		sortPostProcessors(orderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>();
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		// 清除已缓存的mergedBeanDefinitions，因为上面执行的一系列方法可能会修改原始元数据（也就是beanDefinition），后面需要重新再merged一次
		beanFactory.clearMetadataCache();
	}

	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		List<String> orderedPostProcessorNames = new ArrayList<>();
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		for (String ppName : postProcessorNames) {
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				priorityOrderedPostProcessors.add(pp);
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					internalPostProcessors.add(pp);
				}
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			}
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, register the BeanPostProcessors that implement PriorityOrdered.
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		// Next, register the BeanPostProcessors that implement Ordered.
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>();
		for (String ppName : orderedPostProcessorNames) {
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			orderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		sortPostProcessors(orderedPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>();
		for (String ppName : nonOrderedPostProcessorNames) {
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			nonOrderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		sortPostProcessors(internalPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
