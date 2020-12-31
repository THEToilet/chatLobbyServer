package jp.ac.shibaurta.it.ie.architecture;

import jp.ac.shibaura.it.ie.architecture.inject.ServiceCollection;
import jp.ac.shibaura.it.ie.architecture.invoke.UseCaseInvokerFactory;
import jp.ac.shibaura.it.ie.architecture.usecases.core.InputData;
import jp.ac.shibaura.it.ie.architecture.usecases.core.UseCase;

public class UseCaseBusBuilder {
  private ServiceCollection services;
  private UseCaseBus bus = new UseCaseBus();

  public UseCaseBusBuilder(ServiceCollection services) {
    this.services = services;
  }

  public <TInputData extends InputData, TInteractor extends UseCase> void register(Class<TInputData> inputDataClazz,
      Class<TInteractor> interactorClazz) {
    services.addTransient(interactorClazz);
    bus.register(inputDataClazz, interactorClazz);
  }

  public UseCaseBus build(UseCaseInvokerFactory invokerFactory) {
    bus.setup(services.buildServiceProvider(), invokerFactory);
    return bus;
  }
}
