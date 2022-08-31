package br.com.youtube.Project.Customer.persistence.entity;
import javax.persistence.*;
import java.util.function.Function;

@Entity
@Table(name = "costumer")
public class Customer {
    //creation attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    public <R> R map(Function<Customer, R> func){
        return func.apply(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
