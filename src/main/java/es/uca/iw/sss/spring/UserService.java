package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service

public class UserService implements UserDetailsService {

    private UserRepository repo;
    private PasswordEncoder passwordEncoder;
    private static UserService instance;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    private final HashMap<Long, User> contacts = new HashMap<>();
    private long nextId = 0;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        super();
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static UserService getInstance() {

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



    public void create(User usuario)
    {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repo.save(usuario);
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


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user= repo.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException(s);
        }
        return user;
    }

    public User getEmails(String email) { return repo.findByEmail(email); }

    public User getUsers(User user) { return repo.findByUsername(user); }

    public User getUsers(String instanceUser) { return repo.findByUsername(instanceUser); }

}