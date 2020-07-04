package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Capinha
 */
public class Task implements Serializable {

    private TaskTypes type;
    private String courseUnit;
    private String description;
    private PriorityLevel priority;
    private LocalDate dateTime;
    private boolean conclude;

    public Task(TaskTypes type, String courseUnit, String description, PriorityLevel priority, LocalDate dateTime, boolean conclude) {
        this.type = (type == null) ? TaskTypes.INDIFFERENT : type;
        this.courseUnit = (courseUnit == null && courseUnit.trim().isEmpty()) ? "n.a." : courseUnit;
        this.description = (description == null && description.trim().isEmpty()) ? "n.a." : description;
        this.priority = (priority == null) ? PriorityLevel.LOW : priority;;
        this.dateTime = (dateTime == null) ? LocalDate.now() : dateTime;
        this.conclude = conclude;
    }

    public TaskTypes getType() {
        return type;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public String getDescription() {
        return description;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public boolean isConclude() {
        return conclude;
    }

    public void setConclude(boolean conclude) {
        this.conclude = conclude;
    }

    public void setType(TaskTypes type) {
        this.type = type;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Task[" + "type:" + type + ", courseUnit:" + courseUnit + ", description:" + description + ", priority:" + priority + ", dateTime:" + dateTime + ", isConclude:" + conclude + ']';
    }

    @Override
    public int hashCode() {
        return (int) type.hashCode() * courseUnit.hashCode() * description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (!Objects.equals(this.courseUnit, other.courseUnit)
                || !Objects.equals(this.description, other.description)
                || (this.type != other.type)) {
            return false;
        }

        return true;
    }

}
