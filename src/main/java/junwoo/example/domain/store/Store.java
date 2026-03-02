package junwoo.example.domain.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

}
