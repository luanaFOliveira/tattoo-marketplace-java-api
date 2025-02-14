import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.tattoo_marketplace.domain.entities.models.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "tattoo_artists")
public class TattooArtist extends User implements Imageable  {

    @Column(nullable = false)
    private Integer rate;

    @ManyToMany
    @JoinTable(
        name = "artist_categories", 
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id") 
    )
    private Set<Category> categories;

}
