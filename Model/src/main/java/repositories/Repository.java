package repositories;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {
    private final List<T> elements = new ArrayList<>();

    public void add(T element) {
        elements.add(element);
    }

    public void delete(T element) {
        elements.remove(element);
    }

    public List<T> getElements() {
        return elements;
    }

}
