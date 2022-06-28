package id.co.alamisharia.worker;

import id.co.alamisharia.dto.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CalculateAverageBalanceWorker extends Thread {

    private List<Customer> customers;

    public CalculateAverageBalanceWorker(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        customers.forEach(customer -> {
            var averageBalanced = (customer.getBalanced() + customer.getPreviousBalanced()) / 2;
            customer.setAverageBalanced(averageBalanced);
            customer.setThreadNo1(Thread.currentThread().getName());
            log.info("Current Thread name: {}", Thread.currentThread().getName());
        });

        var updateBenefitWorker = new UpdateBenefitWorker(customers);
        updateBenefitWorker.start();
    }
}
