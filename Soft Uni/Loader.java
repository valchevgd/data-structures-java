package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.*;

public class Loader implements Buffer {

    private List<Entity> elements;

    public Loader() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        elements.add(entity);
    }

    @Override
    public Entity extract(int id) {

        for (Entity element : this.elements) {
            if (element.getId() == id) {
                Entity toReturn = element;
                this.elements.remove(element);
                return toReturn;
            }
        }

        return null;
    }

    @Override
    public Entity find(Entity entity) {

        int index = this.elements.indexOf(entity);

        if (index >= 0) {
            return this.elements.get(index);
        }

        return null;
    }

    @Override
    public boolean contains(Entity entity) {

        return this.elements.indexOf(entity) >= 0;
    }

    @Override
    public int entitiesCount() {
        return this.elements.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {

        if (!this.contains(oldEntity)) {
            throw new IllegalStateException("Entity not found");
        }

        int index = this.elements.indexOf(oldEntity);
        this.elements.set(index, newEntity);

    }

    @Override
    public void swap(Entity first, Entity second) {

        if (!this.contains(first) || !this.contains(second)) {
            throw new IllegalStateException("Entity not found");
        }

        int firstIndex = this.elements.indexOf(first);
        int secondIndex = this.elements.indexOf(second);
        this.elements.set(firstIndex, second);
        this.elements.set(secondIndex, first);
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public Entity[] toArray() {
        Entity[] itemsArray = new Entity[this.elements.size()];
        return this.elements.toArray(itemsArray);
    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {

        List<Entity> result = new ArrayList<>();

        for (Entity element : this.elements) {
            if (isGreaterOrEqual(lowerBound, element.getStatus()) && isLessOrEqual(upperBound, element.getStatus())) {
                result.add(element);
            }
        }

        return result;
    }

    private boolean isLessOrEqual(BaseEntity.Status upperBound, BaseEntity.Status status) {
        return upperBound.compareTo(status) >= 0;
    }

    private boolean isGreaterOrEqual(BaseEntity.Status lowerBound, BaseEntity.Status status) {
        return lowerBound.compareTo(status) <= 0;
    }

    @Override
    public List<Entity> getAll() {
        return  Collections.unmodifiableList(this.elements);
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {

        for (Entity element : this.elements) {
            if (element.getStatus().equals(oldStatus)) {
                element.setStatus(newStatus);
            }
        }
    }

    @Override
    public void removeSold() {
        this.elements.removeIf(element -> element.getStatus().equals("SOLD"));
    }

    @Override
    public Iterator<Entity> iterator() {
        return this.elements.iterator();
    }
}
