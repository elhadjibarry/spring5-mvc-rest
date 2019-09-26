package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomers();
        loadCategories();
    }

    private void loadCategories() {
        Category fruits = Category.builder().name("Fruits").build();
        categoryRepository.save(fruits);

        Category dried = Category.builder().name("Dried").build();
        categoryRepository.save(dried);

        Category fresh = Category.builder().name("Fresh").build();
        categoryRepository.save(fresh);

        Category exotic = Category.builder().name("Exotic").build();
        categoryRepository.save(exotic);

        Category nuts = Category.builder().name("Nuts").build();
        categoryRepository.save(nuts);

        System.out.println("Categories loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = Customer.builder().firstName("Michael").lastName("Weston").build();
        customerRepository.save(customer1);

        Customer customer2 = Customer.builder().firstName("Sam").lastName("Axe").build();
        customerRepository.save(customer2);

        System.out.println("Customers loaded = " + customerRepository.count());
    }
}
