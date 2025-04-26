package com.keyconsulting.demo.model;
import java.util.Objects;

public class Task {
    
    private String id;
    private String label;
    private String description;
    private boolean completed;

    public Task() {
    }

    public Task(String id, String label, String description, boolean completed) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.completed = completed;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task taskDTO = (Task) o;
        return Objects.equals(id, taskDTO.id) && Objects.equals(label, taskDTO.label) && Objects.equals(description, taskDTO.description) && completed == taskDTO.completed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, completed);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", label='" + getLabel() + "'" +
            ", description='" + getDescription() + "'" +
            ", completed='" + isCompleted() + "'" +
            "}";
    }
    
}
