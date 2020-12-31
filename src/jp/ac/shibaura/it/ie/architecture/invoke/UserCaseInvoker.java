package jp.ac.shibaura.it.ie.architecture.invoke;

import jp.ac.shibaura.it.ie.usecases.core.InputData;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

public interface UseCaseInvoker {
    public <TOutputData extends OutputData> TOutputData invoke(InputData<TOutputData> inputData);
}
