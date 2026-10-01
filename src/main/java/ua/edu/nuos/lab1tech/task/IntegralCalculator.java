package ua.edu.nuos.lab1tech.task;

public class IntegralCalculator {
    private final double a;
    private final double b;
    private final int n;
    private final Function f;

    public IntegralCalculator(double a, double b, int n, Function f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
    }

    public double calculate() {
        double h = (b - a) / n;
        double sum = f.calculate(a) + f.calculate(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += (i % 2 == 0 ? 2.0 : 4.0) * f.calculate(x);
        }

        return sum * h / 3.0;
    }
}
