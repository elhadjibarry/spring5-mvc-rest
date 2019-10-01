package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomers();
        loadCategories();
        loadVendors();
    }

    private void loadVendors() {

        Vendor vendor1 = Vendor.builder().name("Vendor 1").build();
        vendorRepository.save(vendor1);

        Vendor vendor2 = Vendor.builder().name("Vendor 2").build();
        vendorRepository.save(vendor2);
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
