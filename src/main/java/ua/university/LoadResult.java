package ua.university;

import java.util.List;

public record LoadResult(List<Payment> payments, int invalidLines) {}
