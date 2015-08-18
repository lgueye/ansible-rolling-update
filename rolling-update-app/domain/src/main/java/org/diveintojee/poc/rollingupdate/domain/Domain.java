package org.diveintojee.poc.rollingupdate.domain;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * @author louis.gueye@gmail.com
 */
public class Domain implements Serializable {

    public static final int DESCRIPTION_MAX_SIZE = 200;
    public static final int TITLE_MAX_SIZE = 50;
    public static final int NEW_FIELD_MAX_SIZE = 100;

    private Long id;

    private String title;

    private String description;

    private String newField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    public String getNewField() {
        return newField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domain domain = (Domain) o;
        return Objects.equal(id, domain.id) &&
                Objects.equal(title, domain.title) &&
                Objects.equal(description, domain.description) &&
                Objects.equal(newField, domain.newField);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, description, newField);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("description", description)
                .add("newField", newField)
                .toString();
    }
}
