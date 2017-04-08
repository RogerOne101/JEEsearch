package webone.profile;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "AddressSeq", initialValue = 1, allocationSize = 100, 
                   sequenceName = "AddressSeq")
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressSeq")
  private long id;
  
  private String city;
  
  private String street;
  
  private String postalCode;
  
  @OneToMany
  private Set<Profile> profiles;
}
