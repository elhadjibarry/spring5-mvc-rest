package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

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

        System.out.println("Data loaded = " + categoryRepository.count());
    }
}
