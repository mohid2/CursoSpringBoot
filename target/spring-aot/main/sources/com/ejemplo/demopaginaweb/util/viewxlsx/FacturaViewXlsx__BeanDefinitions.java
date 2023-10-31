package com.ejemplo.demopaginaweb.util.viewxlsx;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FacturaViewXlsx}
 */
public class FacturaViewXlsx__BeanDefinitions {
  /**
   * Get the bean definition for 'xlsx'
   */
  public static BeanDefinition getXlsxBeanDefinition() {
    Class<?> beanType = FacturaViewXlsx.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(FacturaViewXlsx::new);
    return beanDefinition;
  }
}
