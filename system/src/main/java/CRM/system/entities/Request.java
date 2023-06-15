package CRM.system.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
@Entity

@Table(name = "requests")

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

public class Request {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")

    private Long id;



    @Column(name = "userName", length = 200)

    private String userName;



    @Column(name = "courseName", length = 200)

    private String courseName;



    @Column(name = "commentary")

    private String commentary;



    @Column(name = "phone")

    private String phone;

    @Column(name = "handled")

    private boolean handled;

}