import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "images")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.STRING)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    private Imageable entity;  

    @Column(name = "entity_type", insertable = false, updatable = false)
    private String entityType;
}
