package ua.lviv.iot.dblabs.lab6.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(CityLogId.class)
public class CityLog {

    @Id
    @Column(name = "city_id")
    private int id;

    @Column(name = "old_name", length = 45)
    private String oldName;

    @Column(name = "new_name", length = 45)
    private String newName;

    @Column(name = "action", length = 45, nullable = false)
    private String action;

    @Id
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getOldName() {
        return oldName;
    }
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityLog city = (CityLog) o;
        return id == city.id && Objects.equals(oldName, city.oldName) && Objects.equals(newName, city.newName) && Objects.equals(action, city.action) && Objects.equals(timestamp, city.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oldName, newName, action, timestamp);
    }
}
