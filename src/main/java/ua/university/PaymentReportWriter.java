package ua.university;
import ua.university.Payment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class PaymentReportWriter {

    public static void writeReport(Path out, List<Payment> payments, int invalidLines) throws IOException {

        Path temp = Files.createTempFile(out.getParent(), "report_", ".tmp");

        long paidTotal = 0;
        int newCount = 0;
        int paidCount = 0;
        int failedCount = 0;

        for (Payment p : payments) {
            switch (p.status()) {
                case NEW -> newCount++;
                case PAID -> {
                    paidCount++;
                    paidTotal += p.amountCents();
                }
                case FAILED -> failedCount++;
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {

            writer.write("invalidLines=" + invalidLines);
            writer.newLine();

            writer.write("paidTotalCents=" + paidTotal);
            writer.newLine();

            writer.write("NEW=" + newCount + ", PAID=" + paidCount + ", FAILED=" + failedCount);
            writer.newLine();
        }

        try {
            Files.move(temp, out,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(temp, out, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
