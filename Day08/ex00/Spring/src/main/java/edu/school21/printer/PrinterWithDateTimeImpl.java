package edu.school21.printer;

import edu.school21.renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private final Renderer renderer;
    private final LocalDateTime dateTime = LocalDateTime.now();

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.render(dateTime + " :: " + message);
    }
}
