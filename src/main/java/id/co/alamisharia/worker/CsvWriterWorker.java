package id.co.alamisharia.worker;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import id.co.alamisharia.dto.Customer;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Slf4j
public class CsvWriterWorker extends Thread {

    private List<Customer> customers;

    public CsvWriterWorker(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try (var csvWriter = new CSVWriterBuilder(new FileWriter(new File(ClassLoader.getSystemResource("After Eod.csv").toURI()), true))
                .withSeparator(';')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .build()) {

            log.info("Writing csv to {}", ClassLoader.getSystemResource("After Eod.csv").toURI());
//            csvWriter.writeNext(new String[]{"id", "Nama", "Age", "Balanced", "No 2b Thread-No", "No 3 Thread-No", "Previous Balanced", "Average Balanced", "No 1 Thread-No", "Free Transfer", "No 2a Thread-No"});
            customers.forEach(customer -> {
                log.info("Current Thread name: {}", Thread.currentThread().getName());
                String[] record = customer.toString().split(",");

                csvWriter.writeNext(record);
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
