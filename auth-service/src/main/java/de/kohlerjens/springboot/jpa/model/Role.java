package de.kohlerjens.springboot.jpa.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private JwtRole name;
    public Role() {
    }
    public Role(JwtRole name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public JwtRole getName() {
        return name;
    }
    public void setName(JwtRole name) {
        this.name = name;
    }
}