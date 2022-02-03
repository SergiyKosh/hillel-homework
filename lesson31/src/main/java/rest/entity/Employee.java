package rest.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer salary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "chief_id")
    private Long chiefId;
}
