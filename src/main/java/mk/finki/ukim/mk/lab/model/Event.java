package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String description;
    private double popularityScore;
    @ManyToOne
    private Location location;

    public Event(String theaterShow, String s, double v,Location location) {
        this.id = (long) (Math.random() * 1000);
        this.name = theaterShow;
        this.description = s;
        this.popularityScore = v;
        this.location = location;
    }
}
