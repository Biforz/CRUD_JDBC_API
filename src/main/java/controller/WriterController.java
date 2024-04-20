package controller;

import model.Writer;
import repository.WriterRepository;
import repository.jdbc.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController() {
        this.writerRepository = new JdbcWriterRepositoryImpl();
    }

    public List<Writer> getAllWriter() {
        return writerRepository.showAll();
    }

    public Writer getWriterById(Long id) {
        return writerRepository.showById(id);
    }

    public void addNewWriter(Writer writer) {
        writerRepository.add(writer);
    }

    public void updateWriter(Long id, Writer writer) {
        writerRepository.update(id, writer);
    }

    public void deleteWriterById(Long id) {
        writerRepository.deleteById(id);
    }
}
