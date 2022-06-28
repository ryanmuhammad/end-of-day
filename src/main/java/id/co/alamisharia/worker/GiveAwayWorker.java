package id.co.alamisharia.worker;

import id.co.alamisharia.dto.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GiveAwayWorker extends Thread  {

    private List<Customer> customers;

    public GiveAwayWorker(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        customers.forEach(customer -> {
            log.info("Current Thread name: {}", Thread.currentThread().getName());
            customer.setThreadNo3(Thread.currentThread().getName());

            if (customer.getId() <= 100) {
                final var bonus = 10;
                customer.setBalanced(customer.getBalanced() + bonus);
            }
        });

        var csvWriterWorker = new CsvWriterWorker(customers);
        csvWriterWorker.start();
    }
}
