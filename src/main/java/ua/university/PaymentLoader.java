package ua.university;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PaymentLoader {
    public static LoadResult loadWithStats(Path csv) throws IOException {
        List<Payment> payments = new ArrayList<>();
        int invalidLines = 0;

        try (BufferedReader reader = Files.newBufferedReader(csv)) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (line.isBlank()) {
                    continue;
                }

                try {
                    String[] parts = line.split(",");

                    if (parts.length != 4) {
                        invalidLines++;
                        continue;
                    }

                    String id = parts[0].trim();
                    String email = parts[1].trim();
                    PaymentStatus status = PaymentStatus.valueOf(parts[2].trim());
                    long amount = Long.parseLong(parts[3].trim());

                    payments.add(new Payment(id, email, status, amount));

                } catch (Exception e) {
                    invalidLines++;
                }
            }
        }

        return new LoadResult(payments, invalidLines);
    }
}
