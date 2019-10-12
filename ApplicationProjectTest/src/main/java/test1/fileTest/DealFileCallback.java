package test1.fileTest;

@FunctionalInterface
public interface DealFileCallback<T>{
    void callBack(T t);
}