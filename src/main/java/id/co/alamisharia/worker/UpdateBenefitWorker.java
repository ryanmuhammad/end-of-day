package id.co.alamisharia.worker;

import id.co.alamisharia.dto.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class UpdateBenefitWorker extends Thread {

    private List<Customer> customers;

    public UpdateBenefitWorker(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        customers.forEach(customer -> {
            log.info("Current Thread name: {}", Thread.currentThread().getName());
            customer.setThreadNo2a(Thread.currentThread().getName());
            customer.setThreadNo2b(Thread.currentThread().getName());

            if (customer.getBalanced() >= 100 && customer.getBalanced() <= 150) {
                customer.setFreeTransfer(5);
            }

            if (customer.getBalanced() > 150) {
                customer.setFreeTransfer(customer.getFreeTransfer() + 25);
            }
        });

        var giveAwayWorker = new GiveAwayWorker(customers);
        giveAwayWorker.start();
    }
}
