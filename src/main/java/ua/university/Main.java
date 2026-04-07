package ua.university;

import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //task 1
        try {
            Path path = Path.of("F:\\semester2\\practice12\\src\\main\\java\\ua\\university\\payments.csv");

            LoadResult result = PaymentLoader.loadWithStats(path);

            System.out.println("Valid payments: " + result.payments().size());
            System.out.println("Invalid lines: " + result.invalidLines());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            //task 2
            Path path = Path.of("F:\\semester2\\practice12\\src\\main\\java\\ua\\university\\payments.csv");
            Path out = Path.of("F:\\semester2\\practice12\\src\\main\\java\\ua\\university\\report.txt");

            LoadResult result = PaymentLoader.loadWithStats(path);

            PaymentReportWriter.writeReport(
                    out,
                    result.payments(),
                    result.invalidLines()
            );
            //task 3
            Path inbox = Path.of("practical-data", "inbox");
            Path archive = Path.of("practical-data", "archive");

            InboxArchiver.archiveTmpFiles(inbox, archive);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        //task 4
        Path base = Path.of("data");

        Path ok = PathSafety.safeResolve(base, "reports/2025.txt");
        System.out.println(ok);


        //Path bad = PathSafety.safeResolve(base, "../secret.txt");
        //System.out.println(bad);

        //task 5
        try {
            String file = "status.bin";

            StatusFile.initFile(file, 10);

            StatusFile.updateStatus(file, 3, (byte) 1);

            byte value = StatusFile.readStatus(file, 3);

            System.out.println("Status at index 3: " + value);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}