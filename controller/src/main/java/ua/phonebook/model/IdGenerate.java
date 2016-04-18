package ua.phonebook.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by dexter on 15.04.16.
 */
@MappedSuperclass
public class IdGenerate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public IdGenerate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdGenerate that = (IdGenerate) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
