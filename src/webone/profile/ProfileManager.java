package webone.profile;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ProfileManager {

  @PersistenceContext(name = "WebOne")
  private EntityManager em;

  public Profile register(String login, String passwd) {
    if (profileExists(login)) {
      return null;
    }
    Profile p = new Guest();
    p.setLogin(login);
    p.setPasswd(passwd);
    em.persist(p);
    return p;
  }

  public boolean profileExists(String login) {
    Query q = em.createQuery("Select p.id from Profile p where p.login = :login");
    q.setParameter("login", login);
    @SuppressWarnings("unchecked")
    List<Long> ids = q.getResultList();
    return !ids.isEmpty();
  }
  
  public Profile findProfile(String login) {
    Query q = em.createQuery("Select p from Profile p where p.login = :login");
    q.setParameter("login", login);
    @SuppressWarnings("unchecked")
    List<Profile> profiles = q.getResultList();
    if (profiles.isEmpty()) {
      return null;
    }
    return profiles.get(0);
  }

  public Profile findProfile(String login, String passwd) {
    Profile p = findProfile(login);
    if (p == null) {
      return null;
    }
    return passwd.equals(p.getPasswd()) ? p : null;
  }

}
