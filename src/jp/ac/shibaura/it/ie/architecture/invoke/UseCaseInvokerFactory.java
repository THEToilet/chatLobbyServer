package jp.ac.shibaura.it.ie.architecture.invoke;

import jp.ac.shibaura.it.ie.architecture.inject.ServiceProvider;

public interface UseCaseInvokerFactory {
    UseCaseInvoker generate(Class implementClazz, ServiceProvider provider);
}
