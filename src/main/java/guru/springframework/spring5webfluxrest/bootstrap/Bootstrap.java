package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Customer;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("##### LOADING DATA ON BOOTSTRAP ######");

        if(categoryRepository.count().block() == 0) {
            //load data
            categoryRepository.save(Category.builder().description("Fruits").build()).block();

            categoryRepository.save(Category.builder().description("Nuts").build()).block();

            categoryRepository.save(Category.builder().description("Meats").build()).block();

            categoryRepository.save(Category.builder().description("Breads").build()).block();

            categoryRepository.save(Category.builder().description("Eggs").build()).block();




            vendorRepository.save(Vendor.builder().firstName("Joe").lastName("Buck").build()).block();

            vendorRepository.save(Vendor.builder().firstName("Michael").lastName("Weston").build()).block();

            vendorRepository.save(Vendor.builder().firstName("Bill").lastName("Xog").build()).block();

            vendorRepository.save(Vendor.builder().firstName("Jessie").lastName("Watter").build()).block();

            vendorRepository.save(Vendor.builder().firstName("Jimmy").lastName("York").build()).block();

        }
        System.out.println("Loaded Categories: " + categoryRepository.count().block());
        System.out.println("Loaded Vendors: " + categoryRepository.count().block());
    }


}
