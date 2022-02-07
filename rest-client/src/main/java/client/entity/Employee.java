package client.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private Long id;
    private String name;
    private Integer salary;
    private long departmentId;
    private Long chiefId;
}
