package hu.dpc.edu.web;

import org.mockito.Mockito;

/**
 * Created by vrg on 25/10/16.
 */
public class MockingPostProcessor<T> extends BeanReplacerPostProcessor<T> {
    public MockingPostProcessor(Class<T> typeToReplace) {
        super(typeToReplace, Mockito.mock(typeToReplace));
    }
}
