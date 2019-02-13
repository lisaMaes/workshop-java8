package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonMapper<T>  {
        T map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> mapAccount(List<Person> personList, PersonMapper<Account> mapper) {
        // TODO implémenter la méthode
    	
    	List<Account> allAccounts = new ArrayList<Account>();
    	
    	for(Person p: personList){
    		
    		allAccounts.add(mapper.map(p));
    	}    	
        return allAccounts;
    }
    // end::map[]
    
    private List<String> mapString(List<Person> personList, PersonMapper<String> mapper){
    	
    	List<String> nameList = new ArrayList<String>();
    	
    	for(Person p: personList){
    		
    		nameList.add(mapper.map(p));
    	}
    	return nameList;
    }

    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        



        
        List<Account> result = mapAccount(personList, p -> {
        	Account account = new Account();
			account.setOwner(p);
			account.setBalance(100);	
			return account;
        });

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        
        List<String> result = mapString(personList, p -> {
        	String name = p.getFirstname(); 
        	return name;
        });

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
