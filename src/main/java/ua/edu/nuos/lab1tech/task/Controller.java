package ua.edu.nuos.lab1tech.task;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    private double totalSum = 0;
    private int finished = 0;

    @FXML
    private TextField intervals;
    @FXML
    private TextField threads;
    @FXML
    private Label resultLabel;

    @FXML
    private void calculate() {
        final double a = 1.0;
        final double b = 2.0;

        try {
            int n = Integer.parseInt(intervals.getText().trim());
            int numberOfThreads = Integer.parseInt(threads.getText().trim());

            if (n <= 0 || n % 2 != 0) {
                resultLabel.setText("Помилка: n має бути додатним парним числом.");
                return;
            }
            if (numberOfThreads <= 0 || numberOfThreads > n || n % numberOfThreads != 0) {
                resultLabel.setText("Помилка: кількість потоків має бути від 1 до n і ділити n без остачі.");
                return;
            }
            if ((n / numberOfThreads) % 2 != 0) {
                resultLabel.setText("Помилка: кількість розбиттів у кожному потоці має бути парною для методу Сімпсона.");
                return;
            }

            synchronized (this) {
                totalSum = 0;
                finished = 0;
            }

            Function f = new Function();
            long start = System.currentTimeMillis();
            double delta = (b - a) / numberOfThreads;
            int ni = n / numberOfThreads;

            for (int i = 0; i < numberOfThreads; i++) {
                double ai = a + i * delta;
                double bi = (i == numberOfThreads - 1) ? b : ai + delta;
                Thread.startVirtualThread(new RunnableIntegralCalculator(ai, bi, ni, f, this));
            }

            synchronized (this) {
                while (finished < numberOfThreads) {
                    wait();
                }
            }

            long end = System.currentTimeMillis();
            resultLabel.setText("Варіант 6 | Метод Сімпсона\n"
                    + "∫[1;2] (eˣ − 1)/(eˣ + 1) dx\n"
                    + "Час: " + (end - start) + " мс\n"
                    + "Результат: " + totalSum);
        } catch (NumberFormatException e) {
            resultLabel.setText("Помилка: введіть цілі числа для n та кількості потоків.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            resultLabel.setText("Обчислення було перервано.");
        }
    }

    public synchronized void sendResult(double value) {
        totalSum += value;
        finished++;
        notifyAll();
    }
}
