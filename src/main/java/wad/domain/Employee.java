
package wad.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Employee extends AbstractPersistable<Long> implements Comparable <Employee> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Pattern(regexp = "^[^<]*$", message ="Kenttä ei saa sisältää '<'-merkkiä")
    @NotEmpty(message="Ei saa olla tyhjä!")
    @Length(max = 30, message="Pituus ei saa olla yli 30 merkkiä!")
    private String forename;
    
    @Pattern(regexp = "^[^<]*$", message ="Kenttä ei saa sisältää '<'-merkkiä")
    @NotEmpty(message="Ei saa olla tyhjä!")
    @Length(max = 30, message="Pituus ei saa olla yli 30 merkkiä!")
    private String surname;
    
    @Pattern(regexp = "^[^<]*$", message ="Kenttä ei saa sisältää '<'-merkkiä")
    private String address;
    
    @Pattern(regexp = "^[^<]*$", message ="Kenttä ei saa sisältää '<'-merkkiä")
    @NotEmpty(message="Ei saa olla tyhjä!")
    @Length(max = 10, message="Pituus ei saa olla yli 10 merkkiä!")
    private String username;
    
    
    private String password;
    
    @Pattern(regexp = "^[^<]*$", message ="Kenttä ei saa sisältää '<'-merkkiä")
    private String phoneNumber;
    
    @Pattern(regexp = "^[^<]*$", message ="Kenttä ei saa sisältää '<'-merkkiä")
    @Email(message="Sähköpostiosoite muodossa: example@something.com")
    private String email;
    
    @ElementCollection
    @CollectionTable(name="qualifications")
    private List<String> qualifications;
    
    @NotEmpty(message="Valitse käyttöoikeudet!")
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="userroles")
    private List<String> userRoles;
    
    
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
    
    

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getQualifications() {
        return qualifications;
    }
    

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
        
    }

   
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public int compareTo(Employee o) {
        return this.id.compareTo(o.id);
    }

    public Long getId() {
        return id;
    }

    
    
    
    
}
