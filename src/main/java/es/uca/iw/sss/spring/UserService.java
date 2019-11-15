package es.uca.iw.sss.spring;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserService implements UserDetailsService {

    private User repo;
    private PasswordEncoder passwordEncoder;
    private static UserService instance;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    private final HashMap<Long, User> contacts = new HashMap<>();
    private long nextId = 0;

    private UserService() {
        super();
        this.repo = new User("asdasd", "asdasd", "asdasd", "asdasd", "asdasd", "asdasd");
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return null;
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return false;
            }
        };
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
            instance.ensureTestData();
        }
        return instance;
    }

    /**
     * @return all available Customer objects.
     */
    public synchronized List<User> findAll() {
        return findAll(null);
    }

    /**
     * Finds all Customer's that match given filter.
     *
     * @param stringFilter
     *            filter that returned objects should match or null/empty string
     *            if all objects should be returned.
     * @return list a Customer objects
     */
    public synchronized List<User> findAll(String stringFilter) {
        ArrayList<User> arrayList = new ArrayList<>();
        for (User contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    /**
     * Finds all Customer's that match given filter and limits the resultset.
     *
     * @param stringFilter
     *            filter that returned objects should match or null/empty string
     *            if all objects should be returned.
     * @param start
     *            the index of first result
     * @param maxresults
     *            maximum result count
     * @return list a Customer objects
     */
    public synchronized List<User> findAll(String stringFilter, int start, int maxresults) {
        ArrayList<User> arrayList = new ArrayList<>();
        for (User contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        int end = start + maxresults;
        if (end > arrayList.size()) {
            end = arrayList.size();
        }
        return arrayList.subList(start, end);
    }

    /**
     * @return the amount of all customers in the system
     */
    public synchronized long count() {
        return contacts.size();
    }

    /**
     * Deletes a customer from a system
     *
     * @param value
     *            the Customer to be deleted
     */
    public synchronized void delete(User value) {
        contacts.remove(value.getId());
    }

    /**
     * Persists or updates customer in the system. Also assigns an identifier
     * for new Customer instances.
     *
     * @param entry
     */
    public synchronized void save(User entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (User) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        contacts.put(entry.getId(), entry);
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {
            final String[] names = new String[] { "Gabrielle Patel 499902k Resco", "Brian Robinson 123123j lauasd", "Eduardo Haugen 1237808asd asdgaw",
                    "Koen Johansen asde1e 12fadasd", "Alejandro Macdonald oushd 22323f", "Angel Karlsson asdasd2 adfje3" };
            Random r = new Random(0);
            for (String name : names) {
                String[] split = name.split(" ");
                User c = new User("asdasd", "aasdasd", "asdasd", "asdasdasd", "asdasd", "asdasd");
                c.setFirstName(split[0]);
                c.setLastName(split[1]);
                c.setDni(split[2]);
                c.setUser(split[3]);
                save(c);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}