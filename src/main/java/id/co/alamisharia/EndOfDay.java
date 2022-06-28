package id.co.alamisharia;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import id.co.alamisharia.dto.Customer;
import id.co.alamisharia.worker.CalculateAverageBalanceWorker;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EndOfDay {

    public static void main(String[] args) {
        EndOfDay.endOfDay();
    }

    public static void endOfDay() {
        List<Customer> customers = new ArrayList<>();
        try (var fileReader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("Before Eod.csv").toURI()));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build()) {

            int counter = 0;
            String[] lineInArray;
            while ((lineInArray = csvReader.readNext()) != null) {
                var data = lineInArray[0].split(";");
                Customer customer = mapToCustomer(data);
                customers.add(customer);
                counter++;

                if (counter == 25) {
                    var calculateAverageBalanceWorker = new CalculateAverageBalanceWorker(customers);
                    calculateAverageBalanceWorker.start();

                    customers = new ArrayList<>();
                    counter = 0;
                }
            }
        } catch (IOException | URISyntaxException | CsvException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static Customer mapToCustomer(String[] data) {
        var customer = new Customer();
        customer.setId(Integer.valueOf(data[0]));
        customer.setName(data[1]);
        customer.setAge(data[2]);
        customer.setBalanced(Integer.valueOf(data[3]));
        customer.setPreviousBalanced(Integer.valueOf(data[4]));
        customer.setFreeTransfer(Integer.valueOf(data[6]));
        return customer;
    }

    private final void logMemory() {
        log.info("Max Memory: {} Mb", Runtime.getRuntime().maxMemory() / 1048576);
        log.info("Total Memory: {} Mb", Runtime.getRuntime().totalMemory() / 1048576);
        log.info("Free Memory: {} Mb", Runtime.getRuntime().freeMemory() / 1048576);
    }
}
