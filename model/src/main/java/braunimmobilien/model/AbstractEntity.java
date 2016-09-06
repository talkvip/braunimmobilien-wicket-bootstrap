package braunimmobilien.model;

public abstract class AbstractEntity implements IEntity {

public boolean isNew() {
        return getId() == null;
    }

    public boolean isPersisted() {
        return !isNew();
    }

}