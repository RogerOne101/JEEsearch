package webone.profile;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ProfileManager {

	/**
	 * List of current logins for quick access in autocomplete
	 */
	static private List<String> profilesLogins = null;

	@PersistenceContext(name = "WebOne")
	private EntityManager em;

	public Profile register(String login, String passwd, String email) {
		if (profileExists(login)) {
			return null;
		}
		Profile p = new Guest();
		p.setLogin(login);
		p.setPasswd(passwd);
		p.setEmail(email);
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

	/**
	 * Fetch all profiles from database
	 * 
	 * @return list of profiles
	 */
	public List<Profile> getAllProfiles() {
		Query q = em.createQuery("Select p from Profile p");
		@SuppressWarnings("unchecked")
		List<Profile> profiles = q.getResultList();
		if (profiles.isEmpty()) {
			return null;
		}
		return profiles;
	}

	/*
	 * Updates local list "profilesLogins" with current list of logins of all
	 * profiles
	 */
	public void updateLoginList() {
		List<Profile> profiles = getAllProfiles();
		ArrayList<String> logins = new ArrayList<String>();

		for (Profile profile : profiles) {
			logins.add(profile.getLogin());
		}

		profilesLogins = logins;
	}

	/*
	 * Gets list of all logins. If list is used for first time -
	 * updateLoginList() is called
	 */
	public List<String> getAllLogins() {
		if (profilesLogins == null)
			updateLoginList();
		return profilesLogins;
	}

}
