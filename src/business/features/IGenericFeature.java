package business.features;

public interface IGenericFeature<T, E> {
    void addAndUpdate(T t);

    void remove(E id);

    int findIndexById(E id);


}
