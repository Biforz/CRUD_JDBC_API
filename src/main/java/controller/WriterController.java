package controller;

import model.Writer;
import service.WriterService;

import java.util.List;

public class WriterController {
    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterService();
    }

    public List<Writer> getAllWriter() {
        return writerService.getAllWriter();
    }

    public Writer getWriterById(Long id) {
        return writerService.getWriterById(id);
    }

    public void addNewWriter(Writer writer) {
        writerService.addNewWriter(writer);
    }

    public void updateWriter(Long id, Writer writer) {
        writerService.updateWriter(id, writer);
    }

    public void deleteWriterById(Long id) {
        writerService.deleteWriterById(id);
    }
}
