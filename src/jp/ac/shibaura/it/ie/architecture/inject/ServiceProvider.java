package jp.ac.shibaura.it.ie.architecture.inject;

public interface ServiceProvider {
  <T> T getService(Class<T> type);
}
