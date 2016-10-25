package hu.dpc.edu.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by vrg on 25/10/16.
 */
public class BeanReplacerPostProcessor<T> implements BeanPostProcessor {
    private Class<T>typeToReplace;

    private T substitute;

    public BeanReplacerPostProcessor(Class<T> typeToReplace, T substitute) {
        this.typeToReplace = typeToReplace;
        this.substitute = substitute;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (typeToReplace.isInstance(bean)) {
            return substitute;
        } else {
            return bean;
        }
    }
}
