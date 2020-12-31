package jp.ac.shibaura.it.ie.architecture;

import jp.ac.shibaura.it.ie.inject.ServiceProvider;
import jp.ac.shibaura.it.ie.invoke.UseCaseInvoker;
import jp.ac.shibaura.it.ie.invoke.UseCaseInvokerFactory;
import jp.ac.shibaura.it.ie.usecases.core.InputData;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import java.util.HashMap;

public class UseCaseBus {
  private final HashMap<Class, Class> handlerTypes = new HashMap<>();
  private final HashMap<Class, UseCaseInvoker> invokers = new HashMap<>();

  private ServiceProvider provider;
  private UseCaseInvokerFactory invokerFactory;

  UseCaseBus() {
  }

  public <TInputData extends InputData<TOutputData>, TOutputData extends OutputData> TOutputData handle(
      TInputData inputData) {
    UseCaseInvoker invoker = getInvoker(inputData);
    return invoker.invoke(inputData);
  }

  void setup(ServiceProvider provider, UseCaseInvokerFactory invokerFactory) {
    this.provider = provider;
    this.invokerFactory = invokerFactory;
  }

  <TInputData, TInteractor> void register(Class<TInputData> inputDataClazz, Class<TInteractor> outputDataClazz) {
    handlerTypes.put(inputDataClazz, outputDataClazz);
  }

  private <TOutputData extends OutputData> UseCaseInvoker getInvoker(InputData<TOutputData> inputData) {
    Class<? extends InputData> inputDataClazz = inputData.getClass();

    UseCaseInvoker invoker = invokers.getOrDefault(inputDataClazz, null);
    if (invoker != null) {
      return invoker;
    }

    Class handlerClazz = handlerTypes.getOrDefault(inputDataClazz, null);
    if (handlerClazz == null) {
      throw new RuntimeException("not registered");
    }

    invoker = invokerFactory.generate(handlerClazz, provider);
    invokers.put(inputDataClazz, invoker);
    return invoker;
  }
}