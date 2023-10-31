package com.ejemplo.demopaginaweb.util.viewjson;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ClienteViewJson}
 */
public class ClienteViewJson__BeanDefinitions {
  /**
   * Get the bean definition for 'json'
   */
  public static BeanDefinition getJsonBeanDefinition() {
    Class<?> beanType = ClienteViewJson.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(ClienteViewJson::new);
    return beanDefinition;
  }
}
