package ua.university;

public record Payment(String id, String email,PaymentStatus status,double amountCents) {
}
