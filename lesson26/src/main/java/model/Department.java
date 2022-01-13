package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private long id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id: %d\nname: %s\n", id, name);
    }
}
