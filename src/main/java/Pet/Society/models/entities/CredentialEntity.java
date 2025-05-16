package Pet.Society.models.entities;

import Pet.Society.models.enums.Role;
import jakarta.persistence.*;

@Entity
public class CredentialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private ClientEntity client;
    private String username;
    private String password;
    private Role role;


    public CredentialEntity() {
    }

    public CredentialEntity(long id, ClientEntity client, String username, String password, Role role) {
        this.id = id;
        this.client = client;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public CredentialEntity(ClientEntity client, String username, String password, Role role) {
        this.client = client;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
