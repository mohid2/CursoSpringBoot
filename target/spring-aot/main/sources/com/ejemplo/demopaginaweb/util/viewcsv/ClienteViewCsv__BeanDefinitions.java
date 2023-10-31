package com.ejemplo.demopaginaweb.util.viewcsv;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ClienteViewCsv}
 */
public class ClienteViewCsv__BeanDefinitions {
  /**
   * Get the bean definition for 'cliente/listar'
   */
  public static BeanDefinition getClientelistarBeanDefinition() {
    Class<?> beanType = ClienteViewCsv.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(ClienteViewCsv::new);
    return beanDefinition;
  }
}
