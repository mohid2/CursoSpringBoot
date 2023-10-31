package com.ejemplo.demopaginaweb.config;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Bean definitions for {@link MvcConfig}
 */
public class MvcConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'mvcConfig'
   */
  public static BeanDefinition getMvcConfigBeanDefinition() {
    Class<?> beanType = MvcConfig.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    ConfigurationClassUtils.initializeConfigurationClass(MvcConfig.class);
    beanDefinition.setInstanceSupplier(MvcConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jaxb2Marshaller'.
   */
  private static BeanInstanceSupplier<Jaxb2Marshaller> getJaxbMarshallerInstanceSupplier() {
    return BeanInstanceSupplier.<Jaxb2Marshaller>forFactoryMethod(MvcConfig.class, "jaxb2Marshaller")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MvcConfig.class).jaxb2Marshaller());
  }

  /**
   * Get the bean definition for 'jaxb2Marshaller'
   */
  public static BeanDefinition getJaxbMarshallerBeanDefinition() {
    Class<?> beanType = Jaxb2Marshaller.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getJaxbMarshallerInstanceSupplier());
    return beanDefinition;
  }
}
